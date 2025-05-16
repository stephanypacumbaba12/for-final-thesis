package com.example.levelup

import android.app.Dialog
import android.content.Context
import android.graphics.*
import android.speech.tts.TextToSpeech
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.os.Handler
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.math.sqrt

class TracingView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var correctPaint = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.STROKE
        strokeWidth = 15f
        isAntiAlias = true
    }

    private var wrongPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 25f
        isAntiAlias = true
    }

    private var currentPaint = wrongPaint

    private var guidePaint = Paint().apply {
        color = Color.GRAY
        style = Paint.Style.STROKE
        strokeWidth = 10f
        isAntiAlias = true
        pathEffect = DashPathEffect(floatArrayOf(20f, 15f), 0f)
    }

    private var path = Path()
    private var guidePath = Path()
    private var scaledGuidePath = Path()

    private var scaleFactorX = 1f
    private var scaleFactorY = 1f
    private var offsetX = 0f
    private var offsetY = 0f

    private val originalWidth = 700f
    private val originalHeight = 600f

    private lateinit var username: String
    private lateinit var subject: String
    private var level: Int = 3
    private lateinit var traceItem: String
    private var textToSpeech: TextToSpeech? = null

    private var lastTraceSuccess = false
    private var lastFeedbackIndex = 0

    private var submitButton: Button? = null

    fun isTraceSuccessful(): Boolean {
        return lastTraceSuccess
    }

    fun setTraceInfo(username: String, subject: String, level: Int, traceItem: String, context: Context) {
        this.username = username
        this.subject = subject
        this.level = level
        this.traceItem = traceItem

        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech?.language = Locale.US
                textToSpeech?.setPitch(1.2f)
                textToSpeech?.setSpeechRate(1.0f)
            }
        }
    }

    fun setSubmitButton(button: Button) {
        this.submitButton = button
        button.isEnabled = false // Start disabled
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        scaleFactorX = w / originalWidth
        scaleFactorY = h / originalHeight
        offsetX = (w - originalWidth * scaleFactorX) / 2
        offsetY = (h - originalHeight * scaleFactorY) / 2
        updateGuidePath()
    }

    fun setPath(path: Path) {
        this.guidePath = path
        updateGuidePath()
    }

    private fun updateGuidePath() {
        scaledGuidePath.reset()
        if (guidePath.isEmpty) return

        val bounds = RectF()
        guidePath.computeBounds(bounds, true)

        val matrix = Matrix().apply {
            val centerX = bounds.centerX()
            val centerY = bounds.centerY()
            postTranslate(-centerX, -centerY)
            postScale(scaleFactorX, scaleFactorY)
            postTranslate(width / 2f, height / 2f)
        }

        guidePath.transform(matrix, scaledGuidePath)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(scaledGuidePath, guidePaint)
        canvas.drawPath(path, currentPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
                invalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
                invalidate()
                checkRealTimeProgress()
            }
            MotionEvent.ACTION_UP -> {
                invalidate()
            }
        }
        return true
    }

    private fun checkRealTimeProgress() {
        val guideMeasure = PathMeasure(scaledGuidePath, false)
        val guideLength = guideMeasure.length

        if (guideLength == 0f) return

        val numPoints = 100
        val guidePoints = ArrayList<PointF>()
        val sampled = FloatArray(2)

        for (i in 0..numPoints) {
            val fraction = i / numPoints.toFloat()
            guideMeasure.getPosTan(guideLength * fraction, sampled, null)
            guidePoints.add(PointF(sampled[0], sampled[1]))
        }

        var hitCount = 0

        val traceBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val traceCanvas = Canvas(traceBitmap)
        traceCanvas.drawPath(path, correctPaint)

        for (point in guidePoints) {
            val x = point.x.toInt().coerceIn(0, traceBitmap.width - 1)
            val y = point.y.toInt().coerceIn(0, traceBitmap.height - 1)

            val pixel = traceBitmap.getPixel(x, y)
            if (pixel != Color.TRANSPARENT) {
                hitCount++
            }
        }

        val coverage = hitCount.toFloat() / numPoints

        submitButton?.isEnabled = (coverage >= 1.0f)
    }

    fun submitTracing() {
        if (path.isEmpty) {
            Toast.makeText(context, "Please trace first before submitting.", Toast.LENGTH_SHORT).show()
            speakText("Please trace first before submitting.")
            return
        }
        checkTrace()
    }

    private fun checkTrace() {
        val guideMeasure = PathMeasure(scaledGuidePath, false)
        val guideLength = guideMeasure.length

        if (guideLength == 0f) {
            showFeedback("Guide path is empty", false)
            return
        }

        val numPoints = 100
        val guidePoints = ArrayList<PointF>()
        val sampled = FloatArray(2)

        for (i in 0..numPoints) {
            val fraction = i / numPoints.toFloat()
            guideMeasure.getPosTan(guideLength * fraction, sampled, null)
            guidePoints.add(PointF(sampled[0], sampled[1]))
        }

        var hitCount = 0

        val traceBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val traceCanvas = Canvas(traceBitmap)
        traceCanvas.drawPath(path, correctPaint)

        for (point in guidePoints) {
            val x = point.x.toInt().coerceIn(0, traceBitmap.width - 1)
            val y = point.y.toInt().coerceIn(0, traceBitmap.height - 1)

            val pixel = traceBitmap.getPixel(x, y)
            if (pixel != Color.TRANSPARENT) {
                hitCount++
            }
        }

        val coverage = hitCount.toFloat() / numPoints

        if (coverage >= 1.0f) {
            currentPaint = correctPaint
            showAnswerFeedback(true)
            saveTraceResultToDatabase(true)
        } else {
            currentPaint = wrongPaint
            showAnswerFeedback(false)
            saveTraceResultToDatabase(false)
        }

        invalidate()
    }

    private fun showAnswerFeedback(isCorrect: Boolean) {
        val dialog = Dialog(context)
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_feedback, null)

        val gifImageView: ImageView = dialogView.findViewById(R.id.gif_image)
        val feedbackText: TextView = dialogView.findViewById(R.id.feedback_text)

        if (isCorrect) {
            gifImageView.setImageResource(R.drawable.correct)
            val correctResponses = listOf("Awesome!", "Excellent!", "Fantastic!", "Great Job!", "Amazing!", "Well Done!")
            lastFeedbackIndex = (lastFeedbackIndex + 1) % correctResponses.size
            val randomFeedback = correctResponses[lastFeedbackIndex]
            val fullFeedback = "Correct! $randomFeedback"
            feedbackText.text = fullFeedback
            speakText(fullFeedback)

            (context as? Tracing)?.onTraceSuccess()
        } else {
            gifImageView.setImageResource(R.drawable.wrong)
            feedbackText.text = "Try again! You can do it!"
            speakText("Try again! You can do it!")
        }

        dialog.setContentView(dialogView)
        dialog.setCancelable(false)
        dialog.show()

        Handler().postDelayed({
            dialog.dismiss()
        }, 3000)

        // Disable submit button after feedback
        submitButton?.isEnabled = false
    }

    private fun showFeedback(message: String, isSuccess: Boolean) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        speakText(message)
    }

    private fun saveTraceResultToDatabase(isSuccess: Boolean) {
        lastTraceSuccess = isSuccess

        if (!::username.isInitialized || !::subject.isInitialized) return

        val database = FirebaseDatabase.getInstance().reference
        val traceRef = database.child("Users")
            .child(username)
            .child("subjects")
            .child(subject)
            .child("level_$level")
            .child("traces")

        val traceData = hashMapOf(
            "item" to traceItem,
            "success" to isSuccess,
            "timestamp" to System.currentTimeMillis()
        )

        traceRef.push().setValue(traceData)
            .addOnSuccessListener {
                Log.d("TracingView", "Trace result saved successfully")
            }
            .addOnFailureListener { e ->
                Log.e("TracingView", "Failed to save trace result", e)
            }
    }

    private fun speakText(text: String) {
        textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    fun clearTracing() {
        path.reset()
        currentPaint = wrongPaint
        submitButton?.isEnabled = false // <-- Important fix added here
        invalidate()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        textToSpeech?.stop()
        textToSpeech?.shutdown()
    }
}

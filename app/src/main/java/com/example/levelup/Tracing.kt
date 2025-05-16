package com.example.levelup

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import java.util.Locale

class Tracing : AppCompatActivity() {

    private lateinit var tracingView: TracingView
    private lateinit var numberImage: ImageView
    private lateinit var eraseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView



    private var currentIndex = 0
    private var score = 0
    private var attempt = 0

    private var numberTracingData: QuestionAnswer_number4.Level3TracingData? = null
    private var letterTracingData: QuestionAnswer_letter4.Level3TracingData? = null
    private var shapeTracingData: QuestionAnswer_shape4.Level3TracingData? = null
    private var numberfiveTracingData: QuestionAnswer_number5.Level3TracingData? = null
    private var shapefiveTracingData: QuestionAnswer_shape5.Level3TracingData? = null
    private var letterfiveTracingData: QuestionAnswer_letter5.Level3TracingData? = null
    private var lettersixTracingData: QuestionAnswer_letter6.Level3TracingData? = null
    private var numbersixTracingData: QuestionAnswer_number6.Level3TracingData? = null
    private var shapesixTracingData: QuestionAnswer_shape6.Level3TracingData? = null

    private lateinit var username: String
    private lateinit var subject: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tracing)

        tracingView = findViewById(R.id.tracingView)
        numberImage = findViewById(R.id.numberImage)
        eraseButton = findViewById(R.id.eraseButton)
        nextButton = findViewById(R.id.nextButton)
        questionTextView = findViewById(R.id.questionText)

        val submitButton = findViewById<Button>(R.id.submitButton)

// i-connect ang Submit button sa TracingView
        tracingView.setSubmitButton(submitButton)

// kung gusto mo na mag-trigger ng action pag-click ng submitButton:
        submitButton.setOnClickListener {
            tracingView.submitTracing()
        }



        username = intent.getStringExtra("username") ?: run {
            Toast.makeText(this, "Error: No username found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        subject = intent.getStringExtra("subject") ?: run {
            Toast.makeText(this, "Error: No subject found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Load the appropriate tracing data based on subject
        when (subject.lowercase(Locale.getDefault())) {
            "number" -> {
                numberTracingData = QuestionAnswer_number4.getLevel3TracingData()
                if (numberTracingData == null) {
                    Toast.makeText(this, "Error: No number tracing data found", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            "letters" -> {
                letterTracingData = QuestionAnswer_letter4.getLevel3TracingData()
                if (letterTracingData == null) {
                    Toast.makeText(this, "Error: No letter tracing data found", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            "shapes" -> {
                shapeTracingData = QuestionAnswer_shape4.getLevel3TracingData()
                if (shapeTracingData == null) {
                    Toast.makeText(this, "Error: No shape tracing data found", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            "numbers5" -> {
                numberfiveTracingData = QuestionAnswer_number5.getLevel3TracingData()
                if (numberfiveTracingData == null) {
                    Toast.makeText(this, "Error: No numbers5 tracing data found", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            "shape5" -> {
                shapefiveTracingData = QuestionAnswer_shape5.getLevel3TracingData()
                if (shapefiveTracingData == null) {
                    Toast.makeText(this, "Error: No shape5 tracing data found", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            "letter5" -> {
                letterfiveTracingData = QuestionAnswer_letter5.getLevel3TracingData()
                if (letterfiveTracingData == null) {
                    Toast.makeText(this, "Error: No letter5 tracing data found", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            "letter6" -> {
                lettersixTracingData = QuestionAnswer_letter6.getLevel3TracingData()
                if (lettersixTracingData == null) {
                    Toast.makeText(this, "Error: No letter6 tracing data found", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            "number6" -> {
                numbersixTracingData = QuestionAnswer_number6.getLevel3TracingData()
                if (numbersixTracingData == null) {
                    Toast.makeText(this, "Error: No number6 tracing data found", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            "shape6" -> {
                shapesixTracingData = QuestionAnswer_shape6.getLevel3TracingData()
                if (shapesixTracingData == null) {
                    Toast.makeText(this, "Error: No shape6 tracing data found", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            else -> {
                Toast.makeText(this, "Error: Invalid subject", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        updateUI()

        eraseButton.setOnClickListener {
            tracingView.clearTracing()
        }

        nextButton.setOnClickListener {
            Toast.makeText(this, "Complete the trace to move forward!", Toast.LENGTH_SHORT).show()
        }
    }

    fun onTraceSuccess() {
        score++
        currentIndex++

        when (subject.lowercase(Locale.getDefault())) {
            "number" -> {
                numberTracingData?.let {
                    if (currentIndex < it.questionTexts.size) {
                        updateUI()
                        tracingView.clearTracing()
                    } else {
                        attempt++
                        saveScoreToFirebase()
                    }
                }
            }
            "letters" -> {
                letterTracingData?.let {
                    if (currentIndex < it.questionTexts.size) {
                        updateUI()
                        tracingView.clearTracing()
                    } else {
                        attempt++
                        saveScoreToFirebase()
                    }
                }
            }
            "shapes" -> {
                shapeTracingData?.let {
                    if (currentIndex < it.questionTexts.size) {
                        updateUI()
                        tracingView.clearTracing()
                    } else {
                        attempt++
                        saveScoreToFirebase()
                    }
                }
            }
            "numbers5" -> {
                numberfiveTracingData?.let {
                    if (currentIndex < it.questionTexts.size) {
                        updateUI()
                        tracingView.clearTracing()
                    } else {
                        attempt++
                        saveScoreToFirebase()
                    }
                }
            }
            "shape5" -> {
                shapefiveTracingData?.let {
                    if (currentIndex < it.questionTexts.size) {
                        updateUI()
                        tracingView.clearTracing()
                    } else {
                        attempt++
                        saveScoreToFirebase()
                    }
                }
            }
            "letter5" -> {
                letterfiveTracingData?.let {
                    if (currentIndex < it.questionTexts.size) {
                        updateUI()
                        tracingView.clearTracing()
                    } else {
                        attempt++
                        saveScoreToFirebase()
                    }
                }
            }
            "letter6" -> {
                lettersixTracingData?.let {
                    if (currentIndex < it.questionTexts.size) {
                        updateUI()
                        tracingView.clearTracing()
                    } else {
                        attempt++
                        saveScoreToFirebase()
                    }
                }
            }
            "number6" -> {
                numbersixTracingData?.let {
                    if (currentIndex < it.questionTexts.size) {
                        updateUI()
                        tracingView.clearTracing()
                    } else {
                        attempt++
                        saveScoreToFirebase()
                    }
                }
            }
            "shape6" -> {
                shapesixTracingData?.let {
                    if (currentIndex < it.questionTexts.size) {
                        updateUI()
                        tracingView.clearTracing()
                    } else {
                        attempt++
                        saveScoreToFirebase()
                    }
                }
            }
        }
    }

    private fun updateUI() {
        when (subject.lowercase(Locale.getDefault())) {
            "number" -> {
                numberTracingData?.let { data ->
                    if (currentIndex < data.numberImages.size) {
                        numberImage.setImageResource(data.numberImages[currentIndex])
                        questionTextView.text = data.questionTexts[currentIndex]
                        tracingView.setPath(data.numberPaths[currentIndex])

                        val traceItem = "number_${currentIndex + 1}"
                        tracingView.setTraceInfo(username, subject, 3, traceItem, this)
                    }
                }
            }
            "letters" -> {
                letterTracingData?.let { data ->
                    if (currentIndex < data.numberImages.size) {
                        numberImage.setImageResource(data.numberImages[currentIndex])
                        questionTextView.text = data.questionTexts[currentIndex]
                        tracingView.setPath(data.numberPaths[currentIndex])

                        val traceItem = "letter_${data.questionTexts[currentIndex].split(" ").last().replace("?", "")}"
                        tracingView.setTraceInfo(username, subject, 3, traceItem, this)
                    }
                }
            }
            "shapes" -> {
                shapeTracingData?.let { data ->
                    if (currentIndex < data.numberImages.size) {
                        numberImage.setImageResource(data.numberImages[currentIndex])
                        questionTextView.text = data.questionTexts[currentIndex]
                        tracingView.setPath(data.numberPaths[currentIndex])

                        val traceItem = "shape_${data.questionTexts[currentIndex].split(" ").last().replace("?", "")}"
                        tracingView.setTraceInfo(username, subject, 3, traceItem, this)
                    }
                }
            }
            "number5" -> {
                numberfiveTracingData?.let { data ->
                    if (currentIndex < data.numberImages.size) {
                        numberImage.setImageResource(data.numberImages[currentIndex])
                        questionTextView.text = data.questionTexts[currentIndex]
                        tracingView.setPath(data.numberPaths[currentIndex])

                        val traceItem = "number5_${data.questionTexts[currentIndex].split(" ").last().replace("?", "")}"
                        tracingView.setTraceInfo(username, subject, 3, traceItem, this)
                    }
                }
            }
            "shape5" -> {
                shapefiveTracingData?.let { data ->
                    if (currentIndex < data.numberImages.size) {
                        numberImage.setImageResource(data.numberImages[currentIndex])
                        questionTextView.text = data.questionTexts[currentIndex]
                        tracingView.setPath(data.numberPaths[currentIndex])

                        val traceItem = "shape5_${data.questionTexts[currentIndex].split(" ").last().replace("?", "")}"
                        tracingView.setTraceInfo(username, subject, 3, traceItem, this)
                    }
                }
            }
            "letter5" -> {
                letterfiveTracingData?.let { data ->
                    if (currentIndex < data.numberImages.size) {
                        numberImage.setImageResource(data.numberImages[currentIndex])
                        questionTextView.text = data.questionTexts[currentIndex]
                        tracingView.setPath(data.numberPaths[currentIndex])

                        val traceItem = "letter5_${data.questionTexts[currentIndex].split(" ").last().replace("?", "")}"
                        tracingView.setTraceInfo(username, subject, 3, traceItem, this)
                    }
                }
            }
            "letter6" -> {
                lettersixTracingData?.let { data ->
                    if (currentIndex < data.numberImages.size) {
                        numberImage.setImageResource(data.numberImages[currentIndex])
                        questionTextView.text = data.questionTexts[currentIndex]
                        tracingView.setPath(data.numberPaths[currentIndex])

                        val traceItem = "letter6_${data.questionTexts[currentIndex].split(" ").last().replace("?", "")}"
                        tracingView.setTraceInfo(username, subject, 3, traceItem, this)
                    }
                }
            }
            "number6" -> {
                numbersixTracingData?.let { data ->
                    if (currentIndex < data.numberImages.size) {
                        numberImage.setImageResource(data.numberImages[currentIndex])
                        questionTextView.text = data.questionTexts[currentIndex]
                        tracingView.setPath(data.numberPaths[currentIndex])

                        val traceItem = "number6_${data.questionTexts[currentIndex].split(" ").last().replace("?", "")}"
                        tracingView.setTraceInfo(username, subject, 3, traceItem, this)
                    }
                }
            }
            "shape6" -> {
                shapesixTracingData?.let { data ->
                    if (currentIndex < data.numberImages.size) {
                        numberImage.setImageResource(data.numberImages[currentIndex])
                        questionTextView.text = data.questionTexts[currentIndex]
                        tracingView.setPath(data.numberPaths[currentIndex])

                        val traceItem = "shape6_${data.questionTexts[currentIndex].split(" ").last().replace("?", "")}"
                        tracingView.setTraceInfo(username, subject, 3, traceItem, this)
                    }
                }
            }
        }
    }

    private fun saveScoreToFirebase() {
        val database = FirebaseDatabase.getInstance().reference
        val userRef = database.child("Users")
            .child(username)
            .child("subjects")
            .child(subject)
            .child("level_3")

        userRef.child("attempt").get().addOnSuccessListener { snapshot ->
            val currentAttempt = snapshot.getValue(Int::class.java) ?: 0
            val newAttempt = currentAttempt + 1

            val data = mapOf(
                "score" to score,
                "attempt" to newAttempt
            )

            userRef.updateChildren(data).addOnSuccessListener {
                Toast.makeText(this, "Tracing completed! Score saved.", Toast.LENGTH_SHORT).show()
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to save score", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to retrieve attempt count", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        // Disable back button
    }
}
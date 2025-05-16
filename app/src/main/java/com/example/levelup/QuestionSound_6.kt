package com.example.levelup

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class QuestionSound_6 : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var imageView: ImageView
    private lateinit var recordButton: Button
    private lateinit var recordedText: TextView
    private var speechRecognizer: SpeechRecognizer? = null
    private lateinit var database: DatabaseReference
    private lateinit var username: String
    private lateinit var subject: String
    private lateinit var textToSpeech: TextToSpeech

    private var currentQuestionIndex = 0
    private var currentLevel: Int = 1
    private var attempt: Int = 0
    private var score: Int = 0
    private lateinit var levelData: QuestionAnswer_sound6.LevelData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forsound)

        imageView = findViewById(R.id.image_view)
        recordButton = findViewById(R.id.record_button)
        recordedText = findViewById(R.id.tvRecordedText)

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        speechRecognizer?.setRecognitionListener(recognitionListener)
        database = FirebaseDatabase.getInstance().reference

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        speechRecognizer?.setRecognitionListener(recognitionListener)
        textToSpeech = TextToSpeech(this, this) // Initialize TTS
        database = FirebaseDatabase.getInstance().reference

        username = intent.getStringExtra("username") ?: "Unknown User"
        subject = intent.getStringExtra("subject") ?: "Sound"
        currentLevel = intent.getIntExtra("level", 1)

        Log.d("QuestionSound", "User: $username, Subject: $subject, Level: $currentLevel")

        levelData = QuestionAnswer_sound6.getLevelData(currentLevel) ?: run {
            Log.e("QuestionSound", "Level data for level $currentLevel not found")
            Toast.makeText(this, "Level data not found", Toast.LENGTH_SHORT).show()
            return
        }

        loadQuestion()

        recordButton.setOnClickListener {
            startRecording()
        }

    }

    // ✅ Load Question + Speak it aloud
    private fun loadQuestion() {
        if (currentQuestionIndex < levelData.questions.size) {
            imageView.setImageResource(levelData.questions[currentQuestionIndex])
            val questionText = levelData.questionTexts[currentQuestionIndex]
            recordedText.text = questionText
            speakText(questionText)  // 🔊 Speak the question
        } else {
            Toast.makeText(this, "You've completed all questions!", Toast.LENGTH_LONG).show()
            saveScoreToFirebase()
        }
    }

    private fun startRecording() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 1)
            return
        }

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Please say the answer")
        }

        recordedText.text = "Listening..."
        speechRecognizer?.startListening(intent)
    }

    private val recognitionListener = object : RecognitionListener {
        override fun onReadyForSpeech(params: Bundle) {
            Log.d("SpeechRecognizer", "Ready for speech")
            recordedText.text = "Listening..."
        }

        override fun onBeginningOfSpeech() {}
        override fun onRmsChanged(rmsdB: Float) {}
        override fun onBufferReceived(buffer: ByteArray) {}
        override fun onEndOfSpeech() {}

        override fun onError(error: Int) {
            Log.d("SpeechRecognizer", "Error: $error")

            // 🔹 Prevent error message from showing immediately on first attempt
            if (recordedText.text != "Listening...") {
                recordedText.text = "Error occurred. Please try again."
            }

            CoroutineScope(Dispatchers.Main).launch {
                delay(1000) // Small delay before retrying
                startRecording()
            }
        }

        override fun onResults(results: Bundle) {
            val recognizedText = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            val text = recognizedText?.get(0)?.lowercase(Locale.ROOT) ?: "No speech recognized"
            val correctAnswer = levelData.correctAnswers[currentQuestionIndex]

            if (text == correctAnswer.lowercase(Locale.ROOT)) {
                recordedText.text = "Correct! It is $correctAnswer."
                speakText("Correct! It is $correctAnswer.") // 🔊 Speak response
                score++

                CoroutineScope(Dispatchers.Main).launch {
                    delay(2000)
                    currentQuestionIndex++
                    if (currentQuestionIndex < levelData.questions.size) {
                        loadQuestion()
                    } else {
                        saveScoreToFirebase()
                    }
                }
            } else {
                recordedText.text = "Incorrect. You said: $text. Try again."
                speakText("Incorrect. You said: $text. Try again.") // 🔊 Speak response
            }
        }

        override fun onPartialResults(partialResults: Bundle) {}
        override fun onEvent(eventType: Int, params: Bundle) {}
    }

    private fun saveScoreToFirebase() {
        val userRef = database.child("Users").child(username)
        val subjectRef = userRef.child("subjects").child(subject).child("level_$currentLevel")

        subjectRef.child("attempt").get().addOnSuccessListener { snapshot ->
            attempt = snapshot.getValue(Int::class.java) ?: 0
            attempt++

            val levelData = mapOf(
                "score" to score,
                "attempt" to attempt
            )

            subjectRef.updateChildren(levelData)
                .addOnSuccessListener {
                    Log.d("saveScore", "Score and attempt saved successfully! Attempt: $attempt")
                    Toast.makeText(this, "Level $currentLevel completed!", Toast.LENGTH_SHORT).show()

                    // ✅ If Level 1 is completed, move to Level Selection Page
                    if (currentLevel == 1) {
                        Log.d("saveScore", "Redirecting to Level Selection Page")
                        CoroutineScope(Dispatchers.Main).launch {
                            delay(1000) // Small delay for better UX
                            val intent = Intent(this@QuestionSound_6, Sound_AgeSix::class.java)
                            intent.putExtra("username", username)
                            intent.putExtra("subject", subject)
                            startActivity(intent)
                            finish() // Ensures this activity does not stay in the back stack
                        }
                    } else {
                        finish()
                    }
                }
                .addOnFailureListener {
                    Log.e("saveScore", "Failed to save score")
                    Toast.makeText(this, "Failed to save score", Toast.LENGTH_SHORT).show()
                }
        }.addOnFailureListener {
            Log.e("saveScore", "Failed to get current attempt")
            Toast.makeText(this, "Failed to get current attempt", Toast.LENGTH_SHORT).show()
        }
    }

    // ✅ Text-to-Speech Function
    private fun speakText(text: String) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    // ✅ TTS Initialization
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech.language = Locale.US
        } else {
            Log.e("TTS", "Initialization failed")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer?.destroy()
        textToSpeech.stop()
        textToSpeech.shutdown()
    }

    override fun onBackPressed() {
        // Disable back button
    }
}

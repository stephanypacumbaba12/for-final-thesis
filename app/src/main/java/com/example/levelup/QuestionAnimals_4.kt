package com.example.levelup

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.Locale

class QuestionAnimals_4 : AppCompatActivity() {

    private var lastFeedbackIndex = -1 // Keep track of last used feedback index
    private lateinit var levelData: QuestionAnswer_animals4.LevelData
    private var currentQuestionIndex = 0
    private var currentLevel: Int = 1
    private var selectedAnswer: String? = null
    private var score: Int = 0
    private var attempt: Int = 0
    private lateinit var database: DatabaseReference
    private lateinit var textToSpeech: TextToSpeech

    private lateinit var answerButtons: List<Button>
    private lateinit var submitButton: Button
    private lateinit var username: String
    private lateinit var subject: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_questions)

        username = intent.getStringExtra("username") ?: "Unknown User"
        subject = intent.getStringExtra("subject") ?: "Animals"
        currentLevel = intent.getIntExtra("level", 1)

        // Log the current level to ensure it's correct
        Log.d("QuestionAnimals", "Current Level: $currentLevel")

        database = FirebaseDatabase.getInstance().reference

        // Get level data for the current level
        levelData = QuestionAnswer_animals4.getLevelData(currentLevel)
            ?: run {
                Log.e("QuestionAnimals", "Level data for level $currentLevel not found")
                Toast.makeText(this, "Level data not found", Toast.LENGTH_SHORT).show()
                return
            }

        submitButton = findViewById(R.id.btn_Submit)
        submitButton.isEnabled = false
        submitButton.setBackgroundColor(Color.LTGRAY)

        answerButtons = listOf(
            findViewById(R.id.ans_a),
            findViewById(R.id.ans_b),
            findViewById(R.id.ans_c),
            findViewById(R.id.ans_d)
        )

        textToSpeech = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.language = Locale.US
                textToSpeech.setPitch(1.0f)
                textToSpeech.setSpeechRate(1.0f)
                loadQuestion()
            }
        }

        submitButton.setOnClickListener {
            if (selectedAnswer != null) {
                attempt++
                showAnswerFeedback(selectedAnswer == levelData.correctAnswers[currentQuestionIndex])
            } else {
                Toast.makeText(this, "Please select an answer before submitting.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadQuestion() {
        if (currentQuestionIndex < levelData.questions.size) {
            selectedAnswer = null
            submitButton.isEnabled = false
            submitButton.setBackgroundColor(Color.GRAY)

            val questionImage = levelData.questions[currentQuestionIndex]
            val questionText = levelData.questionTexts[currentQuestionIndex]
            val choices = levelData.choices[currentQuestionIndex]

            Log.d("QuestionAnimals", "Loading question at index: $currentQuestionIndex")
            Log.d("QuestionAnimals", "Question Text: $questionText")
            Log.d("QuestionAnimals", "Choices: ${choices.joinToString(", ")}")

            findViewById<ImageView>(R.id.question_image).setImageResource(questionImage)
            findViewById<TextView>(R.id.question).text = questionText
            speakText(questionText)

            for ((index, button) in answerButtons.withIndex()) {
                button.text = choices[index]
                button.setBackgroundColor(Color.GRAY)
                button.setOnClickListener {
                    selectAnswer(button.text.toString(), button)
                }
            }
        } else {
            Toast.makeText(this, "You've completed all questions!", Toast.LENGTH_SHORT).show()
            saveScoreToFirebase()
        }
    }

    private fun selectAnswer(answer: String, selectedButton: Button) {
        selectedAnswer = answer
        submitButton.isEnabled = true
        submitButton.setBackgroundColor(Color.GREEN)

        for (button in answerButtons) {
            button.setBackgroundColor(Color.GRAY)
        }
        selectedButton.setBackgroundColor(Color.parseColor("#87CEEB"))
        speakText("You selected $answer")
    }

    private fun showAnswerFeedback(isCorrect: Boolean) {
        val dialog = Dialog(this)
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_feedback, null)

        val gifImageView: ImageView = dialogView.findViewById(R.id.gif_image)
        val feedbackText: TextView = dialogView.findViewById(R.id.feedback_text)

        if (isCorrect) {
            gifImageView.setImageResource(R.drawable.correct)

            // List of random encouraging words (cycled)
            val correctResponses = listOf("Awesome!", "Excellent!", "Fantastic!", "Great Job!", "Amazing!", "Well Done!")

            // Get the next feedback in order
            lastFeedbackIndex = (lastFeedbackIndex + 1) % correctResponses.size
            val randomFeedback = correctResponses[lastFeedbackIndex]

            val fullFeedback = "Correct! $randomFeedback" // Always starts with "Correct!"
            feedbackText.text = fullFeedback
            speakText(fullFeedback) // Speak the full feedback
            score++
        } else {
            gifImageView.setImageResource(R.drawable.wrong)
            val correctAnswer = levelData.correctAnswers[currentQuestionIndex]
            feedbackText.text = "Wrong! The correct answer is $correctAnswer"
            speakText("Wrong! The correct answer is $correctAnswer")
        }

        dialog.setContentView(dialogView)
        dialog.setCancelable(false)
        dialog.show()

        Handler().postDelayed({
            dialog.dismiss()
            goToNextQuestion()
        }, 3000)
    }

    private fun speakText(text: String) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun goToNextQuestion() {
        if (currentQuestionIndex < levelData.questions.size - 1) {
            currentQuestionIndex++
            loadQuestion()
        } else {
            Toast.makeText(this, "You've finished Level $currentLevel!", Toast.LENGTH_SHORT).show()
            saveScoreToFirebase()
            finish()
        }
    }

    private fun saveScoreToFirebase() {

        // Reference under "Users"
        val userRef = database.child("Users").child(username)

        // Log the subject to ensure it's valid
        Log.d("saveScore", "Subject: $subject")

        // Ensure the subject is correctly retrieved (not "selected")
        val subjectRef = userRef.child("subjects").child(subject).child("level_$currentLevel")

        // Log the reference path
        Log.d("saveScore", "Subject Ref: ${subjectRef.path}")

        // Get current attempt from Firebase to increment it
        subjectRef.child("attempt").get().addOnSuccessListener { snapshot ->
            val currentAttempt = snapshot.getValue(Int::class.java) ?: 0 // Default to 0 if no value exists
            val newAttempt = currentAttempt + 1 // Increment attempt count

            // Log the current attempt and new attempt
            Log.d("saveScore", "Current attempt: $currentAttempt, New attempt: $newAttempt")

            val levelData = mapOf(
                "score" to score,       // Save the score
                "attempt" to newAttempt // Increment and save the attempt count
            )

            // Update Firebase with the new score and incremented attempt
            subjectRef.updateChildren(levelData)
                .addOnSuccessListener {
                    // Success message
                    Log.d("saveScore", "Score and attempt saved successfully!")
                    Toast.makeText(this, "Level $currentLevel completed!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    // Failure message
                    Log.e("saveScore", "Failed to save score")
                    Toast.makeText(this, "Failed to save score", Toast.LENGTH_SHORT).show()
                }
        }.addOnFailureListener {
            // Log failure to retrieve attempt
            Log.e("saveScore", "Failed to get current attempt")
            Toast.makeText(this, "Failed to get current attempt", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onBackPressed() {
        // Disable back button
    }
}

package com.example.levelup

import android.app.Dialog
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class QuestionVegetable_4 : AppCompatActivity() {

    private var lastFeedbackIndex = -1
    private lateinit var levelData: QuestionAnswer_vegetable4.LevelData
    private var currentQuestionIndex = 0
    private var currentLevel: Int = 1
    private var selectedAnswer: String? = null
    private var score: Int = 0
    private var attempt: Int = 0
    private lateinit var database: DatabaseReference
    private var mediaPlayer: MediaPlayer? = null

    private lateinit var answerButtons: List<Button>
    private lateinit var submitButton: Button
    private lateinit var username: String
    private lateinit var subject: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_questions)

        username = intent.getStringExtra("username") ?: "Unknown User"
        subject = intent.getStringExtra("subject") ?: "Vegetables"
        currentLevel = intent.getIntExtra("level", 1)

        Log.d("QuestionVegetable", "Current Level: $currentLevel")

        database = FirebaseDatabase.getInstance().reference

        levelData = QuestionAnswer_vegetable4.getLevelData(currentLevel)
            ?: run {
                Log.e("QuestionVegetable", "Level data for level $currentLevel not found")
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

        loadQuestion()

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
            val audioResId = levelData.audioResources[currentQuestionIndex]

            Log.d("QuestionVegetable", "Loading question at index: $currentQuestionIndex")
            Log.d("QuestionVegetable", "Question Text: $questionText")
            Log.d("QuestionVegetable", "Choices: ${choices.joinToString(", ")}")

            findViewById<ImageView>(R.id.question_image).setImageResource(questionImage)
            findViewById<TextView>(R.id.question).text = questionText

            if (audioResId != 0) {
                playAudio(audioResId)
            }

            for ((index, button) in answerButtons.withIndex()) {
                button.text = choices[index]
                button.setBackgroundColor(Color.GRAY)
                button.setOnClickListener {
                    selectAnswer(button.text.toString(), button, index)
                }
            }
        } else {
            Toast.makeText(this, "You've completed all questions!", Toast.LENGTH_SHORT).show()
            saveScoreToFirebase()
        }
    }

    private fun playAudio(audioResId: Int) {
        try {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(this, audioResId)
            mediaPlayer?.start()
            mediaPlayer?.setOnCompletionListener {
                mediaPlayer?.release()
                mediaPlayer = null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error playing audio", Toast.LENGTH_SHORT).show()
        }
    }

    private fun selectAnswer(answer: String, selectedButton: Button, choiceIndex: Int) {
        selectedAnswer = answer
        submitButton.isEnabled = true
        submitButton.setBackgroundColor(Color.GREEN)

        for (button in answerButtons) {
            button.setBackgroundColor(Color.GRAY)
        }
        selectedButton.setBackgroundColor(Color.parseColor("#87CEEB"))

        // Play the audio corresponding to the selected answer
        val answerAudioResId = levelData.answerAudioResources[currentQuestionIndex][choiceIndex]
        if (answerAudioResId != 0) {
            playAudio(answerAudioResId)
        }
    }

    private fun showAnswerFeedback(isCorrect: Boolean) {
        val dialog = Dialog(this)
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_feedback, null)

        val gifImageView: ImageView = dialogView.findViewById(R.id.gif_image)
        val feedbackText: TextView = dialogView.findViewById(R.id.feedback_text)

        // Play the appropriate voice feedback based on the current question
        val audioResId = if (isCorrect) {
            levelData.correctFeedbackAudio[currentQuestionIndex] // Play "tama.m4a"
        } else {
            levelData.wrongFeedbackAudio[currentQuestionIndex] // Play the specific "mali_*.m4a" for the current question
        }
        playAudio(audioResId)

        if (isCorrect) {
            gifImageView.setImageResource(R.drawable.correct)
            val correctResponses = listOf("Imong gipili tama!")
            lastFeedbackIndex = (lastFeedbackIndex + 1) % correctResponses.size
            val randomFeedback = correctResponses[lastFeedbackIndex]
            val fullFeedback = randomFeedback
            feedbackText.text = fullFeedback
            score++
        } else {
            gifImageView.setImageResource(R.drawable.wrong)
            val correctAnswer = levelData.correctAnswers[currentQuestionIndex]
            feedbackText.text = "Mali! ang sakto nga tubag $correctAnswer"
        }

        dialog.setContentView(dialogView)
        dialog.setCancelable(false)
        dialog.show()

        Handler().postDelayed({
            dialog.dismiss()
            goToNextQuestion()
        }, 5000)
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
        val userRef = database.child("Users").child(username)
        Log.d("saveScore", "Subject: $subject")
        val subjectRef = userRef.child("subjects").child(subject).child("level_$currentLevel")

        subjectRef.child("attempt").get().addOnSuccessListener { snapshot ->
            val currentAttempt = snapshot.getValue(Int::class.java) ?: 0
            val newAttempt = currentAttempt + 1
            Log.d("saveScore", "Current attempt: $currentAttempt, New attempt: $newAttempt")

            val levelData = mapOf(
                "score" to score,
                "attempt" to newAttempt
            )

            subjectRef.updateChildren(levelData)
                .addOnSuccessListener {
                    Log.d("saveScore", "Score and attempt saved successfully!")
                    Toast.makeText(this, "Level $currentLevel completed!", Toast.LENGTH_SHORT).show()
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

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onBackPressed() {
        // Disable back button
    }
}
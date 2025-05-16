package com.example.levelup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import pl.droidsonroids.gif.GifImageView

class Letter_AgeFour : AppCompatActivity() {

    private val selectedItems = arrayListOf<Int>() // Declare selectedItems

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.for_level)


        val usernameTextView = findViewById<TextView>(R.id.textView)
        val subjectNameTextView = findViewById<TextView>(R.id.subjectName)
        val levelOne = findViewById<TextView>(R.id.LvlOneAgefour)
        val levelTwo = findViewById<TextView>(R.id.LvlTwoAgefour)
        val levelThree = findViewById<TextView>(R.id.LvlThreeAgefour)
        val levelHome = findViewById<GifImageView>(R.id.level_home) // Home button

        val starsLevelOne = arrayOf(
            findViewById<ImageView>(R.id.star1_level1),
            findViewById<ImageView>(R.id.star2_level1),
            findViewById<ImageView>(R.id.star3_level1),
            findViewById<ImageView>(R.id.star4_level1),
            findViewById<ImageView>(R.id.star5_level1),
            findViewById<ImageView>(R.id.star6_level1)
        )

        val starsLevelTwo = arrayOf(
            findViewById<ImageView>(R.id.star1_level2),
            findViewById<ImageView>(R.id.star2_level2),
            findViewById<ImageView>(R.id.star3_level2),
            findViewById<ImageView>(R.id.star4_level2),
            findViewById<ImageView>(R.id.star5_level2),
            findViewById<ImageView>(R.id.star6_level2)
        )

        val starsLevelThree = arrayOf(
            findViewById<ImageView>(R.id.star1_level3),
            findViewById<ImageView>(R.id.star2_level3),
            findViewById<ImageView>(R.id.star3_level3),
            findViewById<ImageView>(R.id.star4_level3),
            findViewById<ImageView>(R.id.star5_level3),
            findViewById<ImageView>(R.id.star6_level3)
        )

        val attemptLevelOne = findViewById<TextView>(R.id.attemptLevelOne)
        val attemptLevelTwo = findViewById<TextView>(R.id.attemptLevelTwo)
        val attemptLevelThree = findViewById<TextView>(R.id.attemptLevelThree)

        // Retrieve username and subject from intent
        val username = intent.getStringExtra("username")
        val subject = intent.getStringExtra("subject")

        if (username != null && subject != null) {
            usernameTextView.text = "Keep leveling up, $username!"

            // Update the subject name in the TextView
            subjectNameTextView.text = subject // Set the subject name in the TextView
        } else {
            Toast.makeText(this, "Username or subject not provided!", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Fetch user data from Firebase
        val database = FirebaseDatabase.getInstance()
        val userRef = database.getReference("Users/$username/subjects/$subject")

        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val levelOneScore = snapshot.child("level_1/score").getValue(Int::class.java) ?: 0
                    val levelOneAttempts = snapshot.child("level_1/attempt").getValue(Int::class.java) ?: 0
                    val levelTwoScore = snapshot.child("level_2/score").getValue(Int::class.java) ?: 0
                    val levelTwoAttempts = snapshot.child("level_2/attempt").getValue(Int::class.java) ?: 0
                    val levelTwoUnlocked = snapshot.child("level_2/unlocked").getValue(Boolean::class.java) ?: false
                    val levelThreeScore = snapshot.child("level_3/score").getValue(Int::class.java) ?: 0
                    val levelThreeAttempts = snapshot.child("level_3/attempt").getValue(Int::class.java) ?: 0
                    val levelThreeUnlocked = snapshot.child("level_3/unlocked").getValue(Boolean::class.java) ?: false

                    updateStars(starsLevelOne, levelOneScore)
                    updateStars(starsLevelTwo, levelTwoScore)
                    updateStars(starsLevelThree, levelThreeScore)

                    attemptLevelOne.text = "Attempts: $levelOneAttempts"
                    attemptLevelTwo.text = "Attempts: $levelTwoAttempts"
                    attemptLevelThree.text = "Attempts: $levelThreeAttempts"

                    if (levelOneScore > 2 && !levelTwoUnlocked) {
                        userRef.child("level_2/unlocked").setValue(true)
                    }
                    if (levelTwoScore > 3 && !levelThreeUnlocked) {
                        userRef.child("level_3/unlocked").setValue(true)
                    }

                    enableLevel(levelOne, levelOneScore)
                    enableLevel(levelTwo, levelTwoScore, levelTwoUnlocked)
                    enableLevel(levelThree, levelThreeScore, levelThreeUnlocked)

                    levelOne.setOnClickListener { handleLevelClick(1, username, subject, levelOneScore) }
                    if (levelTwoUnlocked) {
                        levelTwo.setOnClickListener { handleLevelClick(2, username, subject, levelTwoScore) }
                    }
                    if (levelThreeUnlocked) {
                        levelThree.setOnClickListener { handleLevelClick(3, username, subject, levelThreeScore) }
                    }
                } else {
                    Toast.makeText(this@Letter_AgeFour, "User data not found for $subject", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Letter_AgeFour, "Failed to fetch data", Toast.LENGTH_SHORT).show()
            }
        })

        // Home button functionality
        levelHome.setOnClickListener {
            val homeIntent = Intent(this, Home::class.java)
            homeIntent.putExtra("username", username)  // Pass the username
            homeIntent.putIntegerArrayListExtra("selectedItems", ArrayList(selectedItems)) // Pass selected subjects
            homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(homeIntent)
            finish()
        }
    }

    private fun updateStars(stars: Array<ImageView>, score: Int) {
        for (i in stars.indices) {
            stars[i].setImageResource(if (i < score) R.drawable.star else R.drawable.transstar)
        }
    }

    private fun enableLevel(level: TextView, score: Int, isEnabled: Boolean = true) {
        if (score >= 6) {
            level.isEnabled = false
            level.alpha = 0.5f
        } else {
            level.isEnabled = isEnabled
            level.alpha = if (isEnabled) 1.0f else 0.5f
        }
    }

    private fun handleLevelClick(level: Int, username: String?, subject: String?, score: Int) {
        if (score < 6) {
            if (level == 3) {
                // Navigate to tracing activity for Level 3
                val intent = Intent(this, Tracing::class.java)
                intent.putExtra("username", username)
                intent.putExtra("subject", subject)
                startActivity(intent)
            } else {
                navigateToQuiz(level, username, subject)
            }
        }
    }

    private fun navigateToQuiz(level: Int, username: String?, subject: String?) {
        val intent = Intent(this, QuestionLetter_4::class.java)
        intent.putExtra("level", level)
        intent.putExtra("username", username)
        intent.putExtra("subject", subject)
        startActivity(intent)
    }
    override fun onBackPressed() {
        // Disable back button
    }
}
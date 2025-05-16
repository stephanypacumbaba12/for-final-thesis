package com.example.levelup

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import pl.droidsonroids.gif.GifImageView
import com.google.firebase.database.FirebaseDatabase

class Logout5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        val username = intent.getStringExtra("username") ?: "User"
        val textLogout = findViewById<TextView>(R.id.text_logout)
        textLogout.text = "Goodbye, $username"

        val logoutButton = findViewById<GifImageView>(R.id.logout)
        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            // Clear login state
            val sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear() // Removes all stored data
            editor.apply()

            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, Login_Page::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        val backButton = findViewById<GifImageView>(R.id.back)
        backButton.setOnClickListener {
            fetchUserData(username)
        }
    }

    private fun fetchUserData(username: String) {
        val userRef = FirebaseDatabase.getInstance().getReference("Users/$username")

        userRef.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val selectedSubjects = arrayListOf<Int>()
                snapshot.child("subjects").children.forEach { child ->
                    when (child.key) {
                        "Number" -> selectedSubjects.add(R.drawable.number5)
                        "Letters" -> selectedSubjects.add(R.drawable.letter5)
                        "Color" -> selectedSubjects.add(R.drawable.color5)
                        "Shapes" -> selectedSubjects.add(R.drawable.shape5)
                        "Mathematics" -> selectedSubjects.add(R.drawable.math5)
                        "Science" -> selectedSubjects.add(R.drawable.science5)
                    }
                }

                val age = snapshot.child("age").value?.toString() ?: "Unknown"
                val gender = snapshot.child("gender").value?.toString() ?: "Unknown"
                val profileImageUrl = snapshot.child("profileImage").value?.toString() ?: ""

                val homeIntent = Intent(this, Home5::class.java).apply {
                    putExtra("username", username)
                    putExtra("age", age)
                    putExtra("gender", gender)
                    putExtra("profileImage", profileImageUrl)
                    putIntegerArrayListExtra("selectedItems", selectedSubjects)
                }
                startActivity(homeIntent)
                finish()
            } else {
                Toast.makeText(this, "User data not found!", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Error fetching user data", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onBackPressed() {}
}

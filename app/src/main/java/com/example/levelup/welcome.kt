package com.example.levelup

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class welcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.welcome)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Delay before checking login status
        Handler(Looper.getMainLooper()).postDelayed({
            checkLoginStatus()  // ✅ this will handle whether to go to Home or Login
        }, 12100) // 12.1 seconds delay
    }


    private fun checkLoginStatus() {
        val sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            val username = sharedPreferences.getString("username", "") ?: ""

            val databaseRef = FirebaseDatabase.getInstance().getReference("Users/$username")
            databaseRef.get().addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val age = snapshot.child("age").value?.toString() ?: "Unknown"
                    val gender = snapshot.child("gender").value?.toString() ?: "Unknown"
                    val profileImageUrl = snapshot.child("profileImage").value?.toString() ?: ""

                    // Determine which activity to go to based on age
                    val targetActivity = when (age) {
                        "4" -> Home::class.java
                        "5" -> Home5::class.java
                        "6" -> Home6::class.java
                        else -> Login_Page::class.java  // If no valid age, return to login
                    }

                    val intent = Intent(this, targetActivity).apply {
                        putExtra("username", username)
                        putExtra("age", age)
                        putExtra("gender", gender)
                        putExtra("profileImage", profileImageUrl)
                    }

                    startActivity(intent)
                    finish() // Finish Picker activity to remove it from the back stack
                } else {
                    Toast.makeText(this, "User data not found!", Toast.LENGTH_SHORT).show()
                    redirectToLogin()
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Error fetching user data", Toast.LENGTH_SHORT).show()
                redirectToLogin()
            }
        } else {
            redirectToLogin()
        }
    }

    private fun redirectToLogin() {
        startActivity(Intent(this, Login_Page::class.java))
        finish()
    }


    override fun onBackPressed() {
        // Disable back button
    }
}

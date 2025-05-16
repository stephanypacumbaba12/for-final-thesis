package com.example.levelup

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import pl.droidsonroids.gif.GifImageView

class NewAge4 : AppCompatActivity() {

    private var selectedAge: Int? = null
    private val database = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newage)
        Log.d("NewAge4", "onCreate started, layout set")

        // Initialize views
        val age5Button = findViewById<Button>(R.id.button)
        val age6Button = findViewById<Button>(R.id.button2)
        val changeButton = findViewById<Button>(R.id.button3)
        val backButton = findViewById<GifImageView>(R.id.back)

        // Get username from intent
        val username = intent.getStringExtra("username") ?: ""
        Log.d("NewAge4", "Username: '$username'")
        if (username.isEmpty()) {
            Log.e("NewAge4", "Username is empty or null, activity will terminate")
            Toast.makeText(this, "Invalid username.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Handle back button click
        backButton.setOnClickListener {
            fetchUserData(username)
        }

        // Age selection logic
        age5Button.setOnClickListener {
            selectedAge = 5
            Log.d("NewAge4", "Age 5 selected")
            Toast.makeText(this, "Age 5 selected", Toast.LENGTH_SHORT).show()
        }

        age6Button.setOnClickListener {
            selectedAge = 6
            Log.d("NewAge4", "Age 6 selected")
            Toast.makeText(this, "Age 6 selected", Toast.LENGTH_SHORT).show()
        }

        // Handle age change confirmation
        changeButton.setOnClickListener {
            Log.d("NewAge4", "Change button clicked, selectedAge: $selectedAge")
            if (selectedAge == null) {
                Toast.makeText(this@NewAge4, "Please select an age first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            AlertDialog.Builder(this@NewAge4)
                .setTitle("Warning")
                .setMessage("Changing your age will erase your previous data, including likes, and you will only be able to log in with the new age.")
                .setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                    Log.d("NewAge4", "User confirmed age change to $selectedAge")
                    clearUserDataAndRedirect(username, selectedAge!!)
                }
                .setNegativeButton("No") { dialog: DialogInterface, _: Int ->
                    dialog.dismiss()
                    Log.d("NewAge4", "User cancelled age change")
                }
                .show()
        }
    }

    private fun clearUserDataAndRedirect(username: String, newAge: Int) {
        Log.d("NewAge4", "Starting clearUserDataAndRedirect for username: $username, newAge: $newAge")
        val userRef = database.getReference("Users").child(username)

        // Update age and clear relevant data
        val updates = hashMapOf<String, Any?>(
            "age" to newAge.toString(),
            "exp" to 0,
            "subjects" to null,
            "likes" to null // Clear likes for new age
        )

        userRef.updateChildren(updates).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("NewAge4", "Successfully cleared data and updated age to $newAge")
                Toast.makeText(this, "Age updated to $newAge. Redirecting...", Toast.LENGTH_SHORT).show()
                // Update SharedPreferences to reflect new age
                updateSharedPreferencesAge(username, newAge.toString())
                redirectToHome(username, newAge)
            } else {
                Log.e("NewAge4", "Failed to clear data: ${task.exception?.message}")
                Toast.makeText(this, "Failed to update age.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateSharedPreferencesAge(username: String, newAge: String) {
        val sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("selectedAge", newAge)
        editor.apply()
    }

    private fun redirectToHome(username: String, age: Int) {
        val intent = when (age) {
            4 -> Intent(this, Home::class.java)
            5 -> Intent(this, Home5::class.java)
            6 -> Intent(this, Home6::class.java)
            else -> Intent(this, Home::class.java) // Default to Home
        }
        intent.putExtra("username", username)
        startActivity(intent)
        finish()
    }
    private fun fetchUserData(username: String) {
        // Implement your logic to fetch or navigate back using the username
        Log.d("NewAge4", "Back button clicked. Fetching user data for: $username")
        // Example: Redirecting back to a home screen
        val intent = Intent(this, Home::class.java)
        intent.putExtra("username", username)
        startActivity(intent)
        finish()
    }
}
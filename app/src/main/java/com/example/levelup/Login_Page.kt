package com.example.levelup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.ContentLoadingProgressBar
import com.example.levelup.databinding.ActivityLoginPageBinding
import com.google.firebase.database.*

class Login_Page : AppCompatActivity() {
    private var binding: ActivityLoginPageBinding? = null
    private var database: DatabaseReference? = null
    private var selectedGender: String? = null
    private var selectedAge: String? = null
    private val Exp = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if user is already logged in
        val sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        val savedUsername = sharedPreferences.getString("username", null)

        if (isLoggedIn && savedUsername != null) {
            navigateToAgeSpecificActivity(
                savedUsername,
                sharedPreferences.getString("selectedAge", null)
            )
            return
        }

        // Inflate layout
        binding = ActivityLoginPageBinding.inflate(LayoutInflater.from(this))
        setContentView(binding!!.root)

        database = FirebaseDatabase.getInstance().getReference("Users")

        // Gender selection
        binding!!.boyButton.setOnClickListener {
            selectedGender = "Boy"
            binding!!.boyButton.alpha = 1.0f
            binding!!.girlButton.alpha = 0.5f
        }

        binding!!.girlButton.setOnClickListener {
            selectedGender = "Girl"
            binding!!.girlButton.alpha = 1.0f
            binding!!.boyButton.alpha = 0.5f
        }

        // Age selection
        val fourButton = findViewById<ImageButton>(R.id.four)
        val fiveButton = findViewById<ImageButton>(R.id.five)
        val sixButton = findViewById<ImageButton>(R.id.six)

        val ageClickListener = View.OnClickListener { view: View ->
            selectedAge = view.tag.toString()
            fourButton.alpha = 0.5f
            fiveButton.alpha = 0.5f
            sixButton.alpha = 0.5f
            (view as ImageButton).alpha = 1.0f
        }

        fourButton.setOnClickListener(ageClickListener)
        fourButton.tag = "4"
        fiveButton.setOnClickListener(ageClickListener)
        fiveButton.tag = "5"
        sixButton.setOnClickListener(ageClickListener)
        sixButton.tag = "6"

        // Submit button
        binding!!.submit.setOnClickListener {
            val username = binding!!.username.text.toString().trim()
            if (username.isEmpty()) {
                Toast.makeText(this, "Please enter a username.", Toast.LENGTH_SHORT).show()
            } else if (selectedGender == null) {
                Toast.makeText(this, "Please select a gender.", Toast.LENGTH_SHORT).show()
            } else if (selectedAge == null) {
                Toast.makeText(this, "Please select an age.", Toast.LENGTH_SHORT).show()
            } else {
                checkUserInDatabase(username)
            }
        }
    }

    private fun checkUserInDatabase(username: String) {
        val progressBar = ContentLoadingProgressBar(this)
        progressBar.show()

        database!!.child(username).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progressBar.hide()
                if (snapshot.exists()) {
                    // Existing user: Validate age
                    val currentAge = snapshot.child("age").getValue(String::class.java)
                    if (currentAge != selectedAge) {
                        // Age mismatch: Reject login
                        AlertDialog.Builder(this@Login_Page)
                            .setTitle("Age Mismatch")
                            .setMessage(
                                "This account is registered with age $currentAge. " +
                                        "To use age $selectedAge, please create a new account with a different username."
                            )
                            .setPositiveButton("OK") { _, _ ->
                                binding!!.username.text.clear()
                                selectedAge = null
                                binding!!.four.alpha = 0.5f
                                binding!!.five.alpha = 0.5f
                                binding!!.six.alpha = 0.5f
                            }
                            .setNegativeButton("Cancel", null)
                            .show()
                        return
                    }

                    // Valid age: Proceed with login
                    var savedExp = 0
                    if (snapshot.child("exp").value != null) {
                        savedExp = snapshot.child("exp").getValue(Int::class.java)!!
                    }

                    val updates: MutableMap<String, Any?> = HashMap()
                    updates["gender"] = selectedGender // Update gender if changed

                    database!!.child(username).updateChildren(updates)
                        .addOnSuccessListener {
                            Toast.makeText(
                                this@Login_Page,
                                "Welcome back, $username!",
                                Toast.LENGTH_SHORT
                            ).show()
                            saveLoginState(username, selectedAge, selectedGender, savedExp)
                            navigateToAgeSpecificActivity(username, selectedAge)
                        }.addOnFailureListener { error ->
                            Toast.makeText(
                                this@Login_Page,
                                "Failed to update user info: ${error.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                } else {
                    // New user: Proceed to registration
                    Toast.makeText(
                        this@Login_Page,
                        "New user detected. Proceeding to setup.",
                        Toast.LENGTH_SHORT
                    ).show()
                    saveNewUser(username)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                progressBar.hide()
                Toast.makeText(this@Login_Page, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun saveNewUser(username: String) {
        val user = User(username, selectedAge, selectedGender, 0)

        val loading = ContentLoadingProgressBar(this)
        loading.show()
        binding!!.submit.isEnabled = false

        database!!.child(username).setValue(user).addOnSuccessListener {
            loading.hide()
            Toast.makeText(
                this@Login_Page,
                "Successfully registered and logged in.",
                Toast.LENGTH_SHORT
            ).show()
            saveLoginState(username, selectedAge, selectedGender, 0)
            navigateToAgeSpecificPicker(username)
        }.addOnFailureListener { error ->
            loading.hide()
            binding!!.submit.isEnabled = true
            Toast.makeText(
                this@Login_Page,
                "Failed to save user: ${error.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun navigateToAgeSpecificPicker(username: String) {
        val intent = when (selectedAge) {
            "4" -> Intent(this, Picker::class.java)
            "5" -> Intent(this, Picker5::class.java)
            "6" -> Intent(this, Picker6::class.java)
            else -> return
        }
        intent.putExtra("username", username)
        startActivity(intent)
        finish()
    }

    private fun navigateToAgeSpecificActivity(username: String, age: String?) {
        val intent = when (age) {
            "4" -> Intent(this, Home::class.java)
            "5" -> Intent(this, Home5::class.java)
            "6" -> Intent(this, Home6::class.java)
            else -> return
        }
        intent.putExtra("username", username)
        Log.d("Login_Page", "Launching Home with username: '$username'")
        startActivity(intent)
        finish()
    }

    private fun saveLoginState(username: String, age: String?, gender: String?, exp: Int) {
        val sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", true)
        editor.putString("username", username)
        editor.putString("selectedAge", age)
        editor.putString("selectedGender", gender)
        editor.putInt("exp", exp)
        editor.apply()
    }

    override fun onBackPressed() {
        // Disabled
    }
}
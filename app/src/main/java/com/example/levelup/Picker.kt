package com.example.levelup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import pl.droidsonroids.gif.GifImageView

class Picker : AppCompatActivity() {

    private val selectedItems =
        mutableListOf<Int>() // Store newly selected items as drawable resource IDs
    private val maxSelection = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picker)

        // Get previously selected items from intent
        val previouslySelectedItems =
            intent.getIntegerArrayListExtra("selectedItems") ?: arrayListOf()
        val username = intent.getStringExtra("username") ?: ""

        // Define all available items
        val items = mapOf(
            R.id.number_Button to R.drawable.number,
            R.id.letters_Button to R.drawable.let,
            R.id.color_Button to R.drawable.color,
            R.id.shapes_Button to R.drawable.sha,
            R.id.animals_Button to R.drawable.animals,
            R.id.vegetable_Button to R.drawable.vegetable
        )

        // Find the Go Home button as a GifImageView
        val goHomeButton = findViewById<GifImageView>(R.id.goHomeButton)
        goHomeButton.visibility = GifImageView.GONE

        items.forEach { (buttonId, drawableId) ->
            val button = findViewById<GifImageView>(buttonId)

            if (drawableId in previouslySelectedItems) {
                // Make unavailable items transparent
                button.alpha = 0.3f // Reduce visibility
                button.isEnabled = false // Disable button click
            } else {
                // Set up selectable items
                button.setImageResource(drawableId)

                button.setOnClickListener {
                    if (selectedItems.contains(drawableId)) {
                        // Deselect the item
                        selectedItems.remove(drawableId)
                        button.setBackgroundResource(0) // Remove background
                    } else if (selectedItems.size < maxSelection) {
                        // Select the item
                        selectedItems.add(drawableId)
                        button.setBackgroundResource(R.drawable.selected_background) // Add a colored background
                    } else {
                        // Maximum selection reached
                        Toast.makeText(
                            this,
                            "You can select up to $maxSelection items",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        // Submit button to return the selected items
        findViewById<Button>(R.id.submitButton).setOnClickListener {
            if (selectedItems.isEmpty()) {
                Toast.makeText(this, "Please select at least one Subject", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, Home::class.java).apply {
                    putIntegerArrayListExtra(
                        "selectedItems",
                        ArrayList(previouslySelectedItems + selectedItems)
                    )
                    putExtra("username", username)
                }
                startActivity(intent)

                // Save selected subjects to Firebase
                storeSelectedSubjectsInFirebase(username, previouslySelectedItems + selectedItems)
            }
        }

        // Show the Go Home button when returning to the Picker after submitting the selection
        if (previouslySelectedItems.isNotEmpty()) {
            goHomeButton.visibility = GifImageView.VISIBLE
        }

        // Go Home button functionality
        goHomeButton.setOnClickListener {
            val intent = Intent(this, Home::class.java).apply {
                putIntegerArrayListExtra("selectedItems", ArrayList(previouslySelectedItems))
                putExtra("username", username)
            }
            startActivity(intent)
        }
    }

    private fun storeSelectedSubjectsInFirebase(username: String, selectedSubjects: List<Int>) {
        val database = FirebaseDatabase.getInstance()
        val userRef = database.getReference("Users/$username/subjects")

        // Map drawable IDs to subject names
        val newSubjects = selectedSubjects.mapNotNull { drawableId ->
            when (drawableId) {
                R.drawable.number -> "Number"
                R.drawable.let -> "Letters"
                R.drawable.color -> "Color"
                R.drawable.sha -> "Shapes"
                R.drawable.animals -> "Animals"
                R.drawable.vegetable -> "Vegetable"
                else -> null
            }
        }

        userRef.get().addOnSuccessListener { snapshot ->
            val existingSubjects = mutableSetOf<String>()

            // Collect existing subjects if any
            snapshot.children.forEach { child ->
                val subject = child.key
                if (subject != null) existingSubjects.add(subject)
            }

            // Add only new subjects that are not already in Firebase
            val subjectsToAdd = newSubjects.filter { it !in existingSubjects }

            // Update Firebase with new subjects
            subjectsToAdd.forEach { subject ->
                userRef.child(subject).setValue("selected")
            }

            Toast.makeText(this, "Subjects updated successfully!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Error updating subjects in Firebase", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onBackPressed() {
        // Disable back button
    }
}
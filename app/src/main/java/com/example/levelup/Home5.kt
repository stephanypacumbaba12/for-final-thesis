package com.example.levelup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.PopupWindow
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.database.*
import pl.droidsonroids.gif.GifImageView

class Home5 : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private val selectedItems = arrayListOf<Int>()
    private lateinit var textView2: TextView
    private lateinit var database: DatabaseReference

    private val levelTextViews by lazy {
        listOf(
            R.id.levelProgressText,
            R.id.levelProgressText1,
            R.id.levelProgressText2,
            R.id.levelProgressText3,
            R.id.levelProgressText4,
            R.id.levelProgressText5
        ).map { findViewById<TextView>(it) }
    }

    private val quizCompleteTextViews by lazy {
        listOf(
            R.id.quizCompleteText,
            R.id.quizCompleteText1,
            R.id.quizCompleteText2,
            R.id.quizCompleteText3,
            R.id.quizCompleteText4,
            R.id.quizCompleteText5
        ).map { findViewById<TextView>(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home5)

        // Initialize views
        progressBar = findViewById(R.id.progressBar)
        textView2 = findViewById(R.id.textView)

        // Retrieve data from Intent
        val username = intent.getStringExtra("username")
        val receivedItems = intent.getIntegerArrayListExtra("selectedItems") ?: arrayListOf()
        selectedItems.addAll(receivedItems)

        if (username != null) {
            textView2.text = username
        } else {
            Toast.makeText(this, "Username not provided!", Toast.LENGTH_SHORT).show()
        }

        // Restore selected subjects
        restoreSelectedSubjects(receivedItems)

        // Setup placeholders for selected items
        setupSelectedItems()

        // Menu button logic
        val menuButton = findViewById<GifImageView>(R.id.menu)
        menuButton.setOnClickListener { showMenuPopup(findViewById(R.id.main)) }

        username?.let { fetchUserDetails(it) }

        val profileButton = findViewById<GifImageView>(R.id.profile)
        profileButton.setOnClickListener {
            val intent = Intent(this, Logout5::class.java).apply {
                putExtra("username", username)
                putExtra("selectedItems", selectedItems)
                putExtra("age", intent.getStringExtra("age"))
                putExtra("gender", intent.getStringExtra("gender"))
                putExtra("profileImage", intent.getStringExtra("profileImage"))
            }
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val username = intent.getStringExtra("username") ?: "defaultUser"
        updateLevelAndProgress(username)
    }

    private fun updateLevelAndProgress(username: String) {
        val userRef = FirebaseDatabase.getInstance().getReference("Users/$username")

        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) return

                val subjects = snapshot.child("subjects")
                var totalExp = 0
                var selectedSubjectCount = 0
                var subjectIndex = 0

                for (subjectSnapshot in subjects.children) {
                    val score1 = subjectSnapshot.child("level_1/score").getValue(Int::class.java) ?: 0
                    val score2 = subjectSnapshot.child("level_2/score").getValue(Int::class.java) ?: 0
                    val score3 = subjectSnapshot.child("level_3/score").getValue(Int::class.java) ?: 0

                    totalExp += score1 + score2 + score3
                    selectedSubjectCount++

                    if (subjectIndex < quizCompleteTextViews.size) {
                        checkQuizCompletion(score1, score2, score3, subjectIndex)

                        var unlockedLevels = 1
                        if (score1 > 2) unlockedLevels = 2
                        if (score2 > 3) unlockedLevels = 3

                        val levelText = "Level: $unlockedLevels/3"
                        levelTextViews[subjectIndex].text = levelText
                    }

                    subjectIndex++
                }

                val maxExp = selectedSubjectCount * 18
                val percentage = if (maxExp > 0) (totalExp.toFloat() / maxExp) * 100 else 0f

                userRef.child("exp").setValue(totalExp)

                findViewById<TextView>(R.id.percentageLabel).text = "${percentage.toInt()}%"
                progressBar.max = maxExp
                progressBar.progress = totalExp
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error fetching user data: ${error.message}")
            }
        })
    }

    private fun checkQuizCompletion(score1: Int, score2: Int, score3: Int, index: Int) {
        val isComplete = (score1 == 6 && score2 == 6 && score3 == 6)
        if (index < quizCompleteTextViews.size) {
            quizCompleteTextViews[index].visibility = if (isComplete) View.VISIBLE else View.GONE
        }
    }

    private fun restoreSelectedSubjects(selectedItemsList: List<Int>) {
        if (selectedItems.isEmpty()) { // Only add if it's empty to avoid duplicates
            selectedItems.addAll(selectedItemsList)
        }
        setupSelectedItems() // Update UI
    }

    private fun setupSelectedItems() {
        val imageViews = listOf(
            findViewById<GifImageView>(R.id.image_view_1),
            findViewById<GifImageView>(R.id.image_view_2),
            findViewById<GifImageView>(R.id.image_view_3),
            findViewById<GifImageView>(R.id.image_view_4),
            findViewById<GifImageView>(R.id.image_view_5),
            findViewById<GifImageView>(R.id.image_view_6)
        )

        for (i in selectedItems.indices) {
            imageViews[i].apply {
                setImageResource(selectedItems[i])
                visibility = View.VISIBLE
                setOnClickListener { navigateToActivity(selectedItems[i]) }
            }
        }
    }

    private fun navigateToActivity(selectedItem: Int) {
        val username = intent.getStringExtra("username") ?: "defaultUser"
        val intent = when (selectedItem) {
            R.drawable.number5 -> Intent(this, Number5_vid::class.java).apply { putExtra("subject", "Number") }
            R.drawable.letter5 -> Intent(this, Letter5_vid::class.java).apply { putExtra("subject", "Letters") }
            R.drawable.color5 -> Intent(this, Color5_vid::class.java).apply { putExtra("subject", "Color") }
            R.drawable.shape5 -> Intent(this, Shape5_vid::class.java).apply { putExtra("subject", "Shapes") }
            R.drawable.math5 -> Intent(this, Math5_vid::class.java).apply { putExtra("subject", "Mathematics") }
            R.drawable.science5 -> Intent(this, Science5_vid::class.java).apply { putExtra("subject", "Science") }
            else -> null
        }
        intent?.putExtra("username", username)
        intent?.let { startActivity(it) }
    }

    private fun fetchUserDetails(username: String) {
        database = FirebaseDatabase.getInstance().getReference("Users").child(username)

        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val age = snapshot.child("age").getValue(String::class.java) ?: "Unknown"
                    val gender = snapshot.child("gender").getValue(String::class.java) ?: "Unknown"
                    val profileImage = snapshot.child("profileImage").getValue(String::class.java) ?: ""

                    val selectedSubjects = arrayListOf<Int>()

                    val subjectsSnapshot = snapshot.child("subjects")
                    subjectsSnapshot.children.forEach { subject ->
                        when (subject.key) {
                            "Number" -> selectedSubjects.add(R.drawable.number5)
                            "Letters" -> selectedSubjects.add(R.drawable.letter5)
                            "Color" -> selectedSubjects.add(R.drawable.color5)
                            "Shapes" -> selectedSubjects.add(R.drawable.shape5)
                            "Mathematics" -> selectedSubjects.add(R.drawable.math5)
                            "Science" -> selectedSubjects.add(R.drawable.science5)
                        }
                    }

                    intent.putExtra("age", age)
                    intent.putExtra("gender", gender)
                    intent.putExtra("profileImage", profileImage)
                    intent.putIntegerArrayListExtra("selectedItems", selectedSubjects)

                    // 🛠 Restore the subjects after fetching from Firebase
                    restoreSelectedSubjects(selectedSubjects)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error fetching user details: ${error.message}")
            }
        })
    }

    private fun showMenuPopup(anchor: View) {
        val inflater = LayoutInflater.from(this)
        val popupView = inflater.inflate(R.layout.menu_popup5, null)

        val popupWindow = PopupWindow(
            popupView,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            true
        )

        val pickerOption = popupView.findViewById<Button>(R.id.picker_option)
        pickerOption.setOnClickListener {
            popupWindow.dismiss()
            val username = intent.getStringExtra("username") ?: "defaultUser"

            val userRef = FirebaseDatabase.getInstance().getReference("Users/$username/subjects")
            userRef.get().addOnSuccessListener { snapshot ->
                val currentSubjects = arrayListOf<Int>()

                snapshot.children.forEach { child ->
                    when (child.key) {
                        "Number" -> currentSubjects.add(R.drawable.number5)
                        "Letters" -> currentSubjects.add(R.drawable.letter5)
                        "Color" -> currentSubjects.add(R.drawable.color5)
                        "Shapes" -> currentSubjects.add(R.drawable.shape5)
                        "Mathematics" -> currentSubjects.add(R.drawable.math5)
                        "Science" -> currentSubjects.add(R.drawable.science5)
                    }
                }

                val intent = Intent(this, Picker5::class.java).apply {
                    putIntegerArrayListExtra("selectedItems", currentSubjects)
                    putExtra("username", username)
                }
                startActivity(intent)
            }
        }



        val username = intent.getStringExtra("username") ?: "Username Not Provided"
        val databaseRef =
            FirebaseDatabase.getInstance().getReference("Users").child(username).child("subjects")
        val selectedSubjects = arrayListOf<Int>() //

        fun checkAndFetchSubject(subject: String, activityClass: Class<*>) {
            databaseRef.child(subject).get().addOnSuccessListener { snapshot ->
                if (snapshot.exists() && snapshot.value != null) {
                    if (snapshot.value is Boolean && snapshot.value == false) {
                        Toast.makeText(this, "This subject is not selected yet", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val intent = Intent(this, activityClass).apply {
                            putExtra("username", username)
                            putExtra("subject", subject)
                            putIntegerArrayListExtra("selectedItems", selectedSubjects)

                        }
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this, "This subject is not selected yet", Toast.LENGTH_SHORT)
                        .show()
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Error fetching subject data", Toast.LENGTH_SHORT).show()
            }
        }

        // Button Click Listeners
        popupView.findViewById<Button>(R.id.Number5_option).setOnClickListener {
            checkAndFetchSubject("Number", Number_AgeFive::class.java)
        }

        popupView.findViewById<Button>(R.id.Letter5_option).setOnClickListener {
            checkAndFetchSubject("Letters", Letter_AgeFive::class.java)
        }

        popupView.findViewById<Button>(R.id.Shapes5_option).setOnClickListener {
            checkAndFetchSubject("Shapes", Shape_AgeFive::class.java)
        }

        popupView.findViewById<Button>(R.id.Colors5_option).setOnClickListener {
            checkAndFetchSubject("Color", Color_AgeFive::class.java)
        }

        popupView.findViewById<Button>(R.id.Mathematics5_option).setOnClickListener {
            checkAndFetchSubject("Mathematics", Math_AgeFive::class.java)
        }

        popupView.findViewById<Button>(R.id.Science5_option).setOnClickListener {
            checkAndFetchSubject("Science", Science_AgeFive::class.java)
        }

        // RESET AGE BUTTON — Clears session and returns to Login_Page
        popupView.findViewById<Button>(R.id.btnResetAge5).setOnClickListener {
            val sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)
            val username = intent.getStringExtra("username") ?: sharedPreferences.getString("username", "") ?: ""
            Log.d("Home", "Username before clearing preferences: '$username'")

            if (username.isEmpty()) {
                Log.e("Home", "Username is empty, redirecting to Login_Page")
                Toast.makeText(this, "Username not found. Please log in again.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Login_Page::class.java)
                startActivity(intent)
                finish()
                return@setOnClickListener
            }

            // Clear SharedPreferences but preserve username
            val editor = sharedPreferences.edit()
            editor.remove("isLoggedIn")
            editor.remove("selectedAge")
            editor.remove("selectedGender")
            editor.remove("Exp")
            editor.putString("username", username) // Preserve username
            editor.apply()

            Log.d("Home", "SharedPreferences updated, preserved username: '$username'")

            val intent = Intent(this, NewAge5::class.java).apply {
                putExtra("username", username)
            }
            startActivity(intent)
            finish()
        }

        popupWindow.elevation = 10f
        popupWindow.showAsDropDown(anchor, 0, 16)
    }

    override fun onBackPressed() {
        // Disable back button
    }
}

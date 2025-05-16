package com.example.levelup

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.VideoView
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import pl.droidsonroids.gif.GifImageView

class Number1_vid : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var doneButton: Button
    private lateinit var toggleFullscreenButton: Button
    private lateinit var exitFullScreenButton: FloatingActionButton
    private var isFullScreen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_video)

        // Retrieve passed data
        val username = intent.getStringExtra("username") ?: "No Username"
        val subject = intent.getStringExtra("subject") ?: "No Subject"

        // Log the data for debugging
        println("Username: $username, Subject: $subject")

        // Initialize UI components
        videoView = findViewById(R.id.video)
        doneButton = findViewById(R.id.done_button)
        toggleFullscreenButton = findViewById(R.id.toggle_fullscreen)
        exitFullScreenButton = findViewById(R.id.exit_fullscreen_button)
        val back: GifImageView = findViewById(R.id.back)

        // Set up the MediaController for the VideoView
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        mediaController.setMediaPlayer(videoView)
        videoView.setMediaController(mediaController)

        // Set the video URI
        val videoUri = Uri.parse("android.resource://${packageName}/${R.raw.number}")
        videoView.setVideoURI(videoUri)
        videoView.start()

        doneButton.visibility = View.GONE

        // Show done button after video completes
        videoView.setOnCompletionListener {
            doneButton.visibility = View.VISIBLE
            toggleFullscreenButton.visibility = View.GONE
        }

        // Navigate to another activity when the done button is clicked
        doneButton.setOnClickListener {
            val intent = Intent(this, Number_AgeFour::class.java).apply {
                putExtra("username", username)
                putExtra("subject", subject)
            }
            startActivity(intent)
            finish()
        }

        toggleFullscreenButton.setOnClickListener {
            toggleFullScreenMode()
        }

        exitFullScreenButton.setOnClickListener {
            toggleFullScreenMode()
        }

        back.setOnClickListener {
            finish()
        }
    }

    private fun toggleFullScreenMode() {
        if (isFullScreen) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE

            val params = videoView.layoutParams
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT
            videoView.layoutParams = params

            toggleFullscreenButton.visibility = View.VISIBLE
            exitFullScreenButton.visibility = View.GONE

            isFullScreen = false
        } else {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )

            val params = videoView.layoutParams
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.MATCH_PARENT
            videoView.layoutParams = params

            toggleFullscreenButton.visibility = View.GONE
            exitFullScreenButton.visibility = View.VISIBLE

            isFullScreen = true
        }
    }
    override fun onBackPressed() {
        // Disable back button
    }
}

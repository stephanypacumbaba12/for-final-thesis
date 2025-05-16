package com.example.levelup

import android.graphics.Path

object QuestionAnswer_letter4 {

    // Data for questions grouped by level
    val levelData = mapOf(

        1 to LevelData(
            questions = arrayOf(
                R.drawable.tiger,
                R.drawable.cat,
                R.drawable.pig,
                R.drawable.man,
                R.drawable.apple,
                R.drawable.guitar,
            ),
            questionTexts = arrayOf(
                "What letter does this picture start with?",
                "Can you guess the first letter of this picture?",
                "What’s the first letter of this picture?",
                "Look! What letter starts this picture?",
                "What letter comes first in this picture?",
                "Can you find the first letter in this picture?"
            ),
            choices = arrayOf(
                arrayOf("A", "T", "U", "I"),
                arrayOf("C", "X", "B", "T"),
                arrayOf("R", "W", "L", "P"),
                arrayOf("K", "P", "M", "R"),
                arrayOf("I", "M", "L", "A"),
                arrayOf("T", "G", "E", "U")
            ),
            correctAnswers = arrayOf("T", "C", "P", "M", "A", "G")
        ),
        2 to LevelData(

            questions = arrayOf(
                R.drawable.alliga,
                R.drawable.rocket,
                R.drawable.umbrella,
                R.drawable.hamster,
                R.drawable.iguana,
                R.drawable.nut
            ),
            questionTexts = arrayOf(
                "What picture do you see in your phone?",
                "Can you tell me what you see in this picture?",
                "What object do you think this is, can you guess?",
                "Do you recognize the picture, what it is?",
                "What is the picture do you see above?",
                "Can you tell me what you see in your screen?"
            ),
            choices = arrayOf(
                arrayOf("Man", "Guitar", "Tiger", "Alligator"),
                arrayOf("Rocket", "Pig", "Hamster", "Apple"),
                arrayOf("Tiger", "Cat", "Umbrella", "Queen"),
                arrayOf("Nest", "Hamster", "Alligator", "Egg"),
                arrayOf("Apple", "Cat", "Pig", "Iguana"),
                arrayOf("Nut", "Hamster", "Iguana ", "Tiger")
            ),
            correctAnswers = arrayOf("Alligator", "Rocket", "Umbrella", "Hamster", "Iguana", "Nut")
        ),

        3 to Level3TracingData(
            numberImages = arrayOf(
                R.drawable.a, R.drawable.d, R.drawable.f,
                R.drawable.l, R.drawable.h, R.drawable.i
            ),
            questionTexts = arrayOf(
                "Can you trace the letter A?",
                "Can you trace the letter D?",
                "Can you trace the letter F?",
                "Can you trace the letter L?",
                "Can you trace the letter H",
                "Can you trace the letter I?"

            ),
            numberPaths = arrayOf(

                // Letter A
                Path().apply {
                    // Step 1: Left diagonal of A (from top to bottom-left)
                    moveTo(400f, 200f)         // Top of A
                    lineTo(300f, 500f)         // Bottom left

                    // Step 2: Right diagonal of A (from top to bottom-right)
                    moveTo(400f, 200f)         // Top of A again
                    lineTo(500f, 500f)         // Bottom right

                    // Step 3: Crossbar of A (horizontal middle)
                    moveTo(340f, 360f)         // Left side of crossbar
                    lineTo(460f, 360f)         // Right side of crossbar
                    close()
                },
                // Letter D
                Path().apply {
                    moveTo(350f, 300f)
                    lineTo(350f, 500f)

                    quadTo(500f, 500f, 500f, 400f)
                    quadTo(500f, 300f, 350f, 300f)

                    close() // Isarado ang path para mabuo ang letrang D
                },
                // Letter F
                Path().apply {
                    moveTo(350f, 300f)     // Start at top left
                    lineTo(350f, 500f)     // Vertical down
                    moveTo(350f, 300f)     // Lift back to top
                    lineTo(450f, 300f)     // Top horizontal
                    moveTo(350f, 400f)     // Lift to middle
                    lineTo(420f, 400f)     // Middle horizontal (shorter)
                    close()
                },
                // Letter L
                Path().apply {
                    moveTo(350f, 300f)
                    lineTo(350f, 500f)
                    lineTo(450f, 500f)
                },
                // Letter H
                Path().apply {
                    moveTo(350f, 300f)     // Left vertical (top)
                    lineTo(350f, 500f)     // Left vertical (bottom)
                    moveTo(450f, 300f)     // Right vertical (top)
                    lineTo(450f, 500f)     // Right vertical (bottom)
                    moveTo(350f, 400f)     // Middle bar (left)
                    lineTo(450f, 400f)     // Middle bar (right)
                },
                // Letter I
                Path().apply {
                    moveTo(350f, 300f)     // Top bar (left)
                    lineTo(450f, 300f)     // Top bar (right)
                    moveTo(400f, 300f)     // Vertical (top)
                    lineTo(400f, 500f)     // Vertical (bottom)
                    moveTo(350f, 500f)     // Bottom bar (left)
                    lineTo(450f, 500f)     // Bottom bar (right)
                    close()
                }
            ),
            letterImages = arrayOf(), // Empty arrays since we're not using letters in this version
            letterPaths = arrayOf()
        )
    )

    /**
     * Helper function to get data for a specific level.
     * @param level The level number to fetch data for.
     * @return [LevelData] containing questions, texts, choices, and answers for the given level.
     */
    fun getLevelData(level: Int): LevelData? {
        return levelData[level] as? LevelData
    }

    fun getLevel3TracingData(): Level3TracingData? {
        return levelData[3] as? Level3TracingData
    }

    // Data class to store questions, texts, choices, and correct answers per level
    data class LevelData(
        val questions: Array<Int>,           // Array of drawable resource IDs
        val questionTexts: Array<String>,    // Array of question texts
        val choices: Array<Array<String>>,   // Array of choices for each question
        val correctAnswers: Array<String>    // Array of correct answers
    )

    data class Level3TracingData(
        val numberImages: Array<Int>,
        val questionTexts: Array<String>,
        val numberPaths: Array<Path>,
        val letterImages: Array<Int>,
        val letterPaths: Array<Path>
    )
}
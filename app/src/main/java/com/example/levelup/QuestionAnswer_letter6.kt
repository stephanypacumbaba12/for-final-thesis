package com.example.levelup

import android.graphics.Path

object QuestionAnswer_letter6 {

    // Data for questions grouped by level
    val levelData = mapOf(

        1 to LevelData(
            questions = arrayOf(
                R.drawable.jet,
                R.drawable.drum,
                R.drawable.owl,
                R.drawable.lion2,
                R.drawable.boat,
                R.drawable.car
            ),
            questionTexts = arrayOf(
                "Based on the video you just watched, can you tell me what is in the picture above?",
                "After watching the video, what do you think is in the picture that is above?",
                "Look at the picture above and think about the video you watched. What do you think is in that picture?",
                "Now that you've watched the video, what do you think is in the picture above?",
                "Based on the video you just saw, what is in the picture above?",
                "Think about the video you watched, and look at the picture above. What do you think is in that picture?"
            ),
            choices = arrayOf(
                arrayOf("Jet", "Queen", "Apple", "Boat"),
                arrayOf("Fish", "Car", "Drum", "Moon"),
                arrayOf("Xylophone", "Owl", "Key", "Egg"),
                arrayOf("Grass", "Hen", "Nest", "Lion"),
                arrayOf("Paint", "River", "Boat", "Sun"),
                arrayOf("Car", "Train", "Umbrella", "Violin")
            ),
            correctAnswers = arrayOf("Jet", "Drum", "Owl", "Lion", "Boat", "Car")
        ),

        2 to LevelData(

            questions = arrayOf(
                R.drawable.umbrella1,
                R.drawable.nest1,
                R.drawable.fish1,
                R.drawable.train,
                R.drawable.whale,
                R.drawable.queen
            ),
            questionTexts = arrayOf(
                "What picture do you see? What is its first letter? _mbrella",
                "What do you see in the picture? What is its first letter? _est",
                "Can you guess what this is? What is its first letter? _ish",
                "Do you know what this picture shows? What is its first letter? _rain",
                "What picture do you see? What is its first letter? _hale",
                "What do you see on your screen? What is its first letter? _ueen"
            ),
            choices = arrayOf(
                arrayOf("M", "G", "U", "A"),
                arrayOf("N", "P", "H", "V"),
                arrayOf("T", "C", "U", "F"),
                arrayOf("N", "T", "O", "E"),
                arrayOf("A", "X", "W", "I"),
                arrayOf("N", "H", "I ", "Q")
            ),
            correctAnswers = arrayOf("U", "N", "F", "T", "W", "Q")
        ),
        3 to Level3TracingData(
            numberImages = arrayOf(
                R.drawable.r, R.drawable.s, R.drawable.t,
                R.drawable.u, R.drawable.v, R.drawable.w
            ),
            questionTexts = arrayOf(
                "Can you trace the letter R",
                "Can you trace the letter S?",
                "Can you trace the letter T?",
                "Can you trace the letter U?",
                "Can you trace the letter V",
                "Can you trace the letter W?"



            ),
            numberPaths = arrayOf(
                // Letter R
                Path().apply {
                    moveTo(350f, 500f)
                    lineTo(350f, 300f)
                    lineTo(450f, 300f)
                    lineTo(450f, 400f)
                    lineTo(350f, 400f)
                    moveTo(350f, 400f)
                    lineTo(450f, 500f)
                },

                // Letter S
                Path().apply {
                    moveTo(450f, 300f)
                    quadTo(350f, 300f, 350f, 400f)
                    quadTo(350f, 500f, 450f, 500f)
                },

                // Letter T
                Path().apply {
                    moveTo(300f, 300f)
                    lineTo(500f, 300f)
                    moveTo(400f, 300f)
                    lineTo(400f, 500f)
                },

                // Letter U
                Path().apply {
                    moveTo(350f, 300f)
                    lineTo(350f, 450f)
                    quadTo(350f, 500f, 400f, 500f)
                    quadTo(450f, 500f, 450f, 450f)
                    lineTo(450f, 300f)
                },

                // Letter V
                Path().apply {
                    moveTo(350f, 300f)
                    lineTo(400f, 500f)
                    lineTo(450f, 300f)
                },

                // Letter W
                Path().apply {
                    moveTo(300f, 300f)
                    lineTo(350f, 500f)
                    lineTo(400f, 350f)
                    lineTo(450f, 500f)
                    lineTo(500f, 300f)
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
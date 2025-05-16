package com.example.levelup

import android.graphics.Path

object QuestionAnswer_shape4 {

    // Data for questions grouped by level
    val levelData = mapOf(
        1 to LevelData(
            questions = arrayOf(
                R.drawable.diamon,
                R.drawable.heart,
                R.drawable.square,
                R.drawable.triangel,
                R.drawable.star2,
                R.drawable.oval
            ),
            questionTexts = arrayOf(
                "Can you tell me what shape this is?",
                "Which shape is this?",
                "Do you know what shape this is?",
                "What shape do you see?",
                "Can you guess the shape?",
                "What shape do you think this is?"
            ),
            choices = arrayOf(
                arrayOf("Heart", "Oval", "Circle", "Diamond"),
                arrayOf("Diamond", "Star", "Heart", "Oval"),
                arrayOf("Oval", "Triangle","Square", "Star"),
                arrayOf("Heart", "Oval", "Triangle", "Diamond"),
                arrayOf("Circle", "Star", "Diamond", "Heart"),
                arrayOf("Star", "Diamond", "Oval", "Circle")
            ),
            correctAnswers = arrayOf("Diamond", "Heart", "Square", "Triangle", "Star", "Oval")
        ),
        2 to LevelData(
            questions = arrayOf(
                R.drawable.circle,
                R.drawable.rectangle,
                R.drawable.star2,
                R.drawable.oval,
                R.drawable.heart,
                R.drawable.square
            ),
            questionTexts = arrayOf(
                "Take a look! What letter begins the name of this shape? _ircle",
                "Which letter is the first in the name of this shape? _ectangle",
                "Can you find the first letter of this shape's name? _tar",
                "hat letter does this shape's name start with? _val",
                "Can you guess the first letter of this shape's name? _eart",
                "What’s the first letter in this shape's name? _quare"
            ),
            choices = arrayOf(
                arrayOf("S", "C", "F", "N"),
                arrayOf("F", "T", "E", "R"),
                arrayOf("T", "S", "F", "E"),
                arrayOf("O", "S", "T", "E"),
                arrayOf("E", "T", "H", "F"),
                arrayOf("O", "S", "E", "T")
            ),
            correctAnswers = arrayOf("C", "R", "S", "O", "H", "S")
        ),

        3 to Level3TracingData(
            numberImages = arrayOf(
                R.drawable.square, R.drawable.oval, R.drawable.rectangle,
                R.drawable.heart, R.drawable.star2, R.drawable.diamon
            ),
            questionTexts = arrayOf(
                "Can you trace the Shape Square?",
                "Can you trace the Shape oval?",
                "Can you trace the Shape rectangle?",
                "Can you trace the Shape heart?",
                "Can you trace the Shape star?",
                "Can you trace the Shape diamond?"


            ),
            numberPaths = arrayOf(
                // 1. Square
                Path().apply {
                    moveTo(300f, 300f)
                    lineTo(500f, 300f)
                    lineTo(500f, 500f)
                    lineTo(300f, 500f)
                    close()
                },

                // 2. Oval (manual tracing version)
                Path().apply {
                    moveTo(400f, 250f)
                    quadTo(500f, 250f, 500f, 400f)  // Right curve
                    quadTo(500f, 550f, 400f, 550f)  // Bottom curve
                    quadTo(300f, 550f, 300f, 400f)  // Left curve
                    quadTo(300f, 250f, 400f, 250f)  // Top curve
                },

                // 3. Rectangle
                Path().apply {
                    moveTo(300f, 250f)
                    lineTo(500f, 250f)
                    lineTo(500f, 550f)
                    lineTo(300f, 550f)
                    close()
                },

                // 4. Heart
                Path().apply {
                    moveTo(400f, 500f)
                    cubicTo(250f, 400f, 250f, 250f, 400f, 300f)
                    cubicTo(550f, 250f, 550f, 400f, 400f, 500f)
                    close()
                },

                // 5. Star (5-pointed)
                Path().apply {
                    moveTo(400f, 250f)
                    lineTo(430f, 350f)
                    lineTo(530f, 350f)
                    lineTo(450f, 420f)
                    lineTo(480f, 520f)
                    lineTo(400f, 450f)
                    lineTo(320f, 520f)
                    lineTo(350f, 420f)
                    lineTo(270f, 350f)
                    lineTo(370f, 350f)
                    close()
                },

                // 6. Diamond
                Path().apply {
                    moveTo(400f, 250f)
                    lineTo(500f, 400f)
                    lineTo(400f, 550f)
                    lineTo(300f, 400f)
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
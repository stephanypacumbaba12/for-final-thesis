package com.example.levelup

import android.graphics.Path

object QuestionAnswer_shape5 {

    // Data for questions grouped by level
    val levelData = mapOf(

        1 to LevelData(
            questions = arrayOf(
                R.drawable.star2,
                R.drawable.heart,
                R.drawable.square,
                R.drawable.triangel,
                R.drawable.circle,
                R.drawable.rectangle,
            ),
            questionTexts = arrayOf(
                "Can you tell me what shape this is? Is it a circle or a square?",
                "Look at this! What shape do you see?",
                "Can you guess the shape? Is it a circle or something else?",
                "What shape do you see here? Is it a rectangle or something else?",
                "What shape is this? Is it a heart, square, or triangle?",
                "Look closely! What shape is this? Is it star or Oval?"
            ),
            choices = arrayOf(
                arrayOf("Square", "Oval", "Circle", "Star"),
                arrayOf("Diamond", "Star", "Heart", "Oval"),
                arrayOf("Circle", "Triangle","Square", "Star"),
                arrayOf("Heart", "Rectangle", "Triangle", "Diamond"),
                arrayOf("Circle", "Star", "Square", "Heart"),
                arrayOf("Star", "Rectangle", "Oval", "Circle")
            ),
            correctAnswers = arrayOf("Star", "Heart", "Square", "Triangle", "Circle", "Rectangle")
        ),

        2 to LevelData(

            questions = arrayOf(
                R.drawable.oval,
                R.drawable.diamon,
                R.drawable.triangel,
                R.drawable.rectangle,
                R.drawable.heart,
                R.drawable.square
            ),
            questionTexts = arrayOf(
                "Look at this! What shape do you see? Can you tell me the first letter of the shape above? _val",
                "Check the picture! What shape is this? What is the first letter of its name? _iamon",
                "What shape do you see in the image above? Can you guess its first letter? _riangle",
                "Look closely! What shape is in the picture? Can you tell me the first letter? _ectangle",
                "Can you name the shape in the picture? What is its first letter? _eart",
                "Check the picture! What shape is this? What is the first letter of its name? _quare"
            ),
            choices = arrayOf(
                arrayOf("S", "O", "C", "D"),
                arrayOf("D", "S", "H", "O"),
                arrayOf("C", "T","S", "O"),
                arrayOf("H", "D", "T", "R"),
                arrayOf("D", "H", "S", "C"),
                arrayOf("S", "R", "O", "C")
            ),
            correctAnswers = arrayOf("O", "D", "T", "R", "H", "S")
        ),

        3 to Level3TracingData(
            numberImages = arrayOf(
                R.drawable.circle, R.drawable.star2, R.drawable.diamon,
                R.drawable.oval, R.drawable.triangle1, R.drawable.square
            ),
            questionTexts = arrayOf(
                "Can you trace the Shape Circle?",
                "Can you trace the Shape star?",
                "Can you trace the Shape Diamond?",
                "Can you trace the Shape Oval?",
                "Can you trace the Shape Triangle?",
                "Can you trace the Shape square?"



            ),
            numberPaths = arrayOf(

                Path().apply {
                    moveTo(400f, 250f)  // Start at top center
                    // Draw circle using quadTo (quadratic Bezier curves)
                    quadTo(500f, 250f, 500f, 400f)  // Right curve
                    quadTo(500f, 550f, 400f, 550f)  // Bottom curve
                    quadTo(300f, 550f, 300f, 400f)  // Left curve
                    quadTo(300f, 250f, 400f, 250f)  // Top curve (close the circle)
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
                },

                // 2. Oval (manual tracing version)
                Path().apply {
                    moveTo(400f, 250f)
                    quadTo(500f, 250f, 500f, 400f)  // Right curve
                    quadTo(500f, 550f, 400f, 550f)  // Bottom curve
                    quadTo(300f, 550f, 300f, 400f)  // Left curve
                    quadTo(300f, 250f, 400f, 250f)  // Top curve
                },

                // Triangle tracing path (similar style to your square example)
                Path().apply {
                    moveTo(400f, 300f)    // Start at top center (apex)
                    lineTo(500f, 500f)    // Line to bottom right
                    lineTo(300f, 500f)    // Line to bottom left
                    close()               // Close path back to apex
                },

                // 1. Square
                Path().apply {
                    moveTo(300f, 300f)
                    lineTo(500f, 300f)
                    lineTo(500f, 500f)
                    lineTo(300f, 500f)
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
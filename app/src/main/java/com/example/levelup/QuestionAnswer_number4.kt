package com.example.levelup

import android.graphics.Path

object QuestionAnswer_number4 {

    // Data for questions grouped by level
    val levelData = mapOf(

        1 to LevelData(
            questions = arrayOf(
                R.drawable.ten,
                R.drawable.two,
                R.drawable.five1,
                R.drawable.nine,
                R.drawable.seven,
                R.drawable.one,
            ),
            questionTexts = arrayOf(
                "What number do you see in this picture?",
                "What number is this?",
                "What is this number?",
                "What number is this picture showing?",
                "What number do you see in this picture?",
                "What number is in this picture? Can you find it?"
            ),
            choices = arrayOf(
                arrayOf("Seven", "Three", "Eleven", "Ten"),
                arrayOf("Four", "Two", "Five", "One"),
                arrayOf("Three", "Six", "Five", "Eight"),
                arrayOf("Nine", "Ten", "Two", "One"),
                arrayOf("Eight", "Three", "Seven", "Five"),
                arrayOf("One", "Six", "Eight", "Two")
            ),
            correctAnswers = arrayOf("Ten", "Two", "Five", "Nine", "Seven", "One")
        ),
        2 to LevelData(

            questions = arrayOf(
                R.drawable.three,
                R.drawable.four1,
                R.drawable.six1,
                R.drawable.eight,
                R.drawable.ten,
                R.drawable.seven
            ),
            questionTexts = arrayOf(
                "What letter does the name of this number start with? _hree",
                "Can you guess the first letter of the name of this number? _our",
                "What’s the first letter in the name of this number? _ix",
                "Look! What letter begins the name of this number? _ight",
                "Which letter comes first in the name of this number? _en",
                "Can you find the first letter of the name of this number? _even"
            ),
            choices = arrayOf(
                arrayOf("S", "T", "F", "N"),
                arrayOf("F", "T", "E", "O"),
                arrayOf("T", "S", "F", "E"),
                arrayOf("N", "S", "T", "E"),
                arrayOf("E", "T", "S", "F"),
                arrayOf("O", "S", "E", "T")
            ),
            correctAnswers = arrayOf("T", "F", "S", "E", "T", "S")
        ),

        3 to Level3TracingData(
            numberImages = arrayOf(
                R.drawable.one, R.drawable.four1, R.drawable.five1,
                R.drawable.three, R.drawable.seven, R.drawable.two
            ),
            questionTexts = arrayOf(
                "Can you trace the number 1?",
                "Can you trace the number 4?",
                "Can you trace the number 5?",
                "Can you trace the number 3?",
                "Can you trace the number 7?",
                "Can you trace the number 2?"
            ),
            numberPaths = arrayOf(
                Path().apply { moveTo(350f, 300f); lineTo(350f, 500f) }, // 1
                // Path for number 4
                Path().apply {
                    moveTo(450f, 200f)     // Start at top right
                    lineTo(300f, 400f)     // Diagonal down to middle left
                    lineTo(500f, 400f)     // Horizontal line to right
                    moveTo(450f, 200f)     // Lift and move to start of vertical stroke
                    lineTo(450f, 600f)     // Vertical line straight down


}, // 4
                Path().apply {

                        moveTo(400f, 300f)
                        lineTo(300f, 300f)
                        lineTo(300f, 400f)
                        lineTo(385f, 390f)
                        cubicTo(430f, 400f, 450f, 480f, 305f, 470f)

                },
                        // 5
                // Path for number 3
                Path().apply {
                    moveTo(250f, 250f)
                    cubicTo(450f, 180f, 470f, 280f, 400f, 300f)
                    cubicTo(300f, 350f, 300f, 310f, 390f, 350f)
                    cubicTo(470f, 370f, 450f, 480f, 250f, 430f)
                },
                Path().apply {
                    moveTo(300f, 300f)    // Top-left
                    lineTo(500f, 300f)    // Top-right (horizontal stroke)
                    lineTo(400f, 500f)    // Diagonal down to bottom-center
                },
                //7

                Path().apply {
                    moveTo(300f, 300f)
                    cubicTo(380f, 300f, 420f, 320f, 400f, 350f)  // Top curve
                    cubicTo(380f, 380f, 320f, 450f, 300f, 500f)  // Descending curve
                    cubicTo(350f, 480f, 400f, 470f, 400f, 450f)  // Tail flick
                }
                //two
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
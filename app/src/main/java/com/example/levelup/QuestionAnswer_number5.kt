package com.example.levelup

import android.graphics.Path

object QuestionAnswer_number5 {

    // Data for questions grouped by level
    val levelData = mapOf(

        1 to LevelData(
            questions = arrayOf(
                R.drawable.two,
                R.drawable.four1,
                R.drawable.six1,
                R.drawable.three,
                R.drawable.ten,
                R.drawable.eight,
            ),
            questionTexts = arrayOf(
                "Can you find the number that is called Two?",
                "Which number is Four? Can you pick it?",
                "Find the number Six and show it to me.",
                "Can you point to the number Three from the list?",
                "Which number is Ten? Can you choose it?",
                "Can you pick the number Eight from these numbers?"
            ),
            choices = arrayOf(
                arrayOf("10", "2", "4", "3"),
                arrayOf("4", "8", "6", "4"),
                arrayOf("5", "9", "6", "2"),
                arrayOf("9", "3", "1", "12"),
                arrayOf("11", "2", "6", "10"),
                arrayOf("1", "8", "5", "7")
            ),
            correctAnswers = arrayOf("2", "4", "6", "3", "10", "8")
        ),

        2 to LevelData(

            questions = arrayOf(
                R.drawable.thriteen,
                R.drawable.fourteen,
                R.drawable.seven,
                R.drawable.one,
                R.drawable.nine,
                R.drawable.twelve
            ),
            questionTexts = arrayOf(
                "Base on the picture you saw above, what number is it? Can you pick the correct answer from the choices?",
                "Can you look at the picture and tell me what number it is? Choose the right answer.",
                "What number do you see in the picture above? Can you find the correct choice?",
                "Look at the picture! What number do you think that is? Pick the right answer.",
                "Can you guess the number shown in the picture above? Select the correct answer.",
                "From the picture above, what number do you see? Choose the correct answer from the choices."
            ),
            choices = arrayOf(
                arrayOf("Twenty", "Ten", "Thirteen", "Eleven"),
                arrayOf("Fourteen", "two", "Sixteen", "Four"),
                arrayOf("Fifteen", "Five", "Eight", "Seven"),
                arrayOf("Two", "One", "Three", "Nine"),
                arrayOf("Nine", "Seventeen", "Ten", "Nineteen"),
                arrayOf("Six", "Eleven", "Twelve", "Eighteen")
            ),
            correctAnswers = arrayOf("Thirteen", "Fourteen", "Seven", "One", "Nine", "Twelve")
        ),

        3 to Level3TracingData(
            numberImages = arrayOf(
                R.drawable.ten, R.drawable.seven, R.drawable.eight,
                R.drawable.nine, R.drawable.eleven, R.drawable.twelve
            ),
            questionTexts = arrayOf(
                "Can you trace the number 10?",
                "Can you trace the number 7?",
                "Can you trace the number 8?",
                "Can you trace the number 9?",
                "Can you trace the number 11?",
                "Can you trace the number 12?"
            ),
            numberPaths = arrayOf(
                Path().apply {
                    // Number 1
                    moveTo(350f, 300f)
                    lineTo(350f, 500f)

                    // Number 0
                    moveTo(450f, 300f)
                    quadTo(550f, 300f, 550f, 400f)  // Right curve
                    quadTo(550f, 500f, 450f, 500f)  // Bottom curve
                    quadTo(350f, 500f, 350f, 400f)  // Left curve
                    quadTo(350f, 300f, 450f, 300f)  // Top curve
                }, // 10

                Path().apply {
                    moveTo(300f, 300f)    // Top-left
                    lineTo(500f, 300f)    // Top-right (horizontal stroke)
                    lineTo(400f, 500f)    // Diagonal down to bottom-center
                }, // 7

                Path().apply {
                    // Top circle
                    moveTo(400f, 300f)
                    quadTo(300f, 300f, 300f, 400f)  // Left curve
                    quadTo(300f, 500f, 400f, 500f)  // Bottom curve
                    quadTo(500f, 500f, 500f, 400f)  // Right curve
                    quadTo(500f, 300f, 400f, 300f)  // Top curve

                    // Bottom circle
                    moveTo(400f, 400f)
                    quadTo(300f, 400f, 300f, 500f)  // Left curve
                    quadTo(300f, 600f, 400f, 600f)  // Bottom curve
                    quadTo(500f, 600f, 500f, 500f)  // Right curve
                    quadTo(500f, 400f, 400f, 400f)  // Top curve
                }, // 8
                Path().apply {
                    // Top circle
                    moveTo(400f, 300f)
                    quadTo(300f, 300f, 300f, 400f)  // Left curve
                    quadTo(300f, 500f, 400f, 500f)  // Bottom curve
                    quadTo(500f, 500f, 500f, 400f)  // Right curve
                    quadTo(500f, 300f, 400f, 300f)  // Top curve

                    // Tail
                    moveTo(500f, 400f)
                    lineTo(400f, 600f)
                }, // 9

                Path().apply {
                    // First 1
                    moveTo(300f, 300f)
                    lineTo(300f, 500f)

                    // Second 1
                    moveTo(400f, 300f)
                    lineTo(400f, 500f)
                }, // 11

                Path().apply {
                    // Number 1
                    moveTo(300f, 300f)
                    lineTo(300f, 500f)

                    // Number 2
                    moveTo(400f, 300f)
                    lineTo(500f, 300f)    // Top
                    lineTo(500f, 400f)    // Right down
                    lineTo(400f, 400f)    // Left
                    lineTo(400f, 500f)    // Down
                    lineTo(500f, 500f)    // Right
                }, // 12
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
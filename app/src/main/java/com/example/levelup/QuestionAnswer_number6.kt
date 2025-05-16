package com.example.levelup

import android.graphics.Path

object QuestionAnswer_number6 {

    // Data for questions grouped by level
    val levelData = mapOf(

        1 to LevelData(
            questions = arrayOf(
                R.drawable.nine,
                R.drawable.fourteen,
                R.drawable.thriteen,
                R.drawable.eleven,
                R.drawable.twelve,
                R.drawable.ten
            ),
            questionTexts = arrayOf(
                "Can you find the number Nine?",
                "Which number is Fourteen ? Point to it!",
                "Show me the number Thirteen.",
                "Where is the number Eleven? Tap it!",
                "Find the number Twelve. Can you choose it?",
                "Pick the number Ten from these!"
            ),
            choices = arrayOf(
                arrayOf("4", "9", "8", "2"),
                arrayOf("6", "10", "15", "14"),
                arrayOf("13", "7", "3", "5"),
                arrayOf("17", "13", "11", "12"),
                arrayOf("11", "12", "10", "15"),
                arrayOf("1", "6", "8", "10")
            ),
            correctAnswers = arrayOf("9", "14", "13", "11", "12", "10")
        ),

        2 to LevelData(

            questions = arrayOf(
                R.drawable.sixteen,
                R.drawable.eighteen,
                R.drawable.seventeen,
                R.drawable.ninteen,
                R.drawable.fifteen,
                R.drawable.twenty
            ),
            questionTexts = arrayOf(
                "What picture do you see on your phone? What is its first letter? _ixteen",
                "What do you see in this picture? What is its first letter? _ighteen",
                "Can you guess what this object is? What is its first letter? _eventeen",
                "Do you know what this picture is? What is its first letter? _ineteen",
                "What picture do you see above? What is its first letter? _ifteen",
                "What do you see on your screen? What is its first letter? _wenty"
            ),
            choices = arrayOf(
                arrayOf("M", "G", "T", "S"),
                arrayOf("E", "P", "H", "A"),
                arrayOf("T", "C", "S", "O"),
                arrayOf("E", "N", "A", "E"),
                arrayOf("A", "C", "P", "F"),
                arrayOf("N", "T", "I ", "S")
            ),
            correctAnswers = arrayOf("S", "E", "S", "N", "F", "T")
        ),

        3 to Level3TracingData(
            numberImages = arrayOf(
                R.drawable.thriteen, R.drawable.fifteen, R.drawable.seventeen,
                R.drawable.ninteen, R.drawable.ten, R.drawable.fourteen
            ),
            questionTexts = arrayOf(
                "Can you trace the number 13?",
                "Can you trace the number 15?",
                "Can you trace the number 17?",
                "Can you trace the number 19?",
                "Can you trace the number 10?",
                "Can you trace the number 14?"
            ),
            numberPaths = arrayOf(
                // Number 13
                Path().apply {
                    // Number 1
                    moveTo(350f, 200f)
                    lineTo(350f, 500f)
                    // Number 3
                    moveTo(450f, 200f)
                    quadTo(550f, 200f, 550f, 350f)
                    quadTo(550f, 500f, 450f, 500f)
                    moveTo(450f, 350f)
                    quadTo(550f, 350f, 550f, 350f)
                },

// Number 15
                Path().apply {
                    // Number 1
                    moveTo(350f, 200f)
                    lineTo(350f, 500f)
                    // Number 5
                    moveTo(450f, 200f)
                    lineTo(550f, 200f)
                    lineTo(550f, 350f)
                    lineTo(450f, 350f)
                    lineTo(450f, 500f)
                    lineTo(550f, 500f)
                },

// Number 17
                Path().apply {
                    // Number 1
                    moveTo(350f, 200f)
                    lineTo(350f, 500f)
                    // Number 7
                    moveTo(450f, 200f)
                    lineTo(550f, 200f)
                    lineTo(450f, 500f)
                },

// Number 19
                Path().apply {
                    // Number 1
                    moveTo(350f, 200f)
                    lineTo(350f, 500f)
                    // Number 9
                    moveTo(450f, 200f)
                    quadTo(550f, 200f, 550f, 350f)
                    quadTo(550f, 500f, 450f, 500f)
                    quadTo(350f, 500f, 350f, 350f)
                    quadTo(350f, 200f, 450f, 200f)
                    moveTo(450f, 350f)
                    quadTo(550f, 350f, 550f, 350f)
                },

// Number 10
                Path().apply {
                    // Number 1
                    moveTo(350f, 200f)
                    lineTo(350f, 500f)
                    // Number 0
                    moveTo(450f, 200f)
                    quadTo(550f, 200f, 550f, 350f)
                    quadTo(550f, 500f, 450f, 500f)
                    quadTo(350f, 500f, 350f, 350f)
                    quadTo(350f, 200f, 450f, 200f)
                },

// Number 14
                Path().apply {
                    // Number 1
                    moveTo(350f, 200f)
                    lineTo(350f, 500f)
                    // Number 4
                    moveTo(450f, 200f)
                    lineTo(450f, 500f)
                    moveTo(350f, 350f)
                    lineTo(550f, 350f)
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
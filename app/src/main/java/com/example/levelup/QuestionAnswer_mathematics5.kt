package com.example.levelup

object QuestionAnswer_mathematics5 {

    // Data for questions grouped by level
    val levelData = mapOf(

        1 to LevelData(
            questions = arrayOf(
                R.drawable.question,
                R.drawable.question,
                R.drawable.question,
                R.drawable.question,
                R.drawable.question,
                R.drawable.question
            ),
            questionTexts = arrayOf(
                "1+1=?",
                "3+1=?",
                "2+1=?",
                "4+1=?",
                "3+4=?",
                "5+1=?"
            ),
            choices = arrayOf(
                arrayOf("9", "3", "5", "2"),
                arrayOf("5", "7", "12", "4"),
                arrayOf("9", "3", "14", "19"),
                arrayOf("5", "10", "5", "15"),
                arrayOf("9", "7", "4", "1"),
                arrayOf("6", "8", "3", "5")
            ),
            correctAnswers = arrayOf("2", "4", "3", "5", "7", "6")
        ),

        2 to LevelData(
            questions = arrayOf(
                R.drawable.question,
                R.drawable.question,
                R.drawable.question,
                R.drawable.question,
                R.drawable.question,
                R.drawable.question
            ),
            questionTexts = arrayOf(
                "6+2=?",
                "9+1=?",
                "4+5=?",
                "10+2=?",
                "5+8=?",
                "5+6=?"
            ),
            choices = arrayOf(
                arrayOf("9", "6", "5", "8"),
                arrayOf("5", "10", "12", "4"),
                arrayOf("9", "3", "14", "19"),
                arrayOf("5", "10", "12", "15"),
                arrayOf("10", "13", "15", "18"),
                arrayOf("20", "19", "15", "11")
            ),
            correctAnswers = arrayOf("8", "10", "9", "12", "13", "11")
        ),

        3 to LevelData(
            questions = arrayOf(
                R.drawable.question,
                R.drawable.question,
                R.drawable.question,
                R.drawable.question,
                R.drawable.question,
                R.drawable.question
            ),
            questionTexts = arrayOf(
                "10+5=?",
                "9+8=?",
                "8+11=?",
                "12+12=?",
                "12+10=?",
                "12+9=?"
            ),
            choices = arrayOf(
                arrayOf("15", "18", "10", "20"),
                arrayOf("14", "10", "12", "17"),
                arrayOf("22", "19", "14", "24"),
                arrayOf("21", "16", "24", "20"),
                arrayOf("11", "13", "22", "18"),
                arrayOf("21", "19", "15", "11")
            ),
            correctAnswers = arrayOf("15", "17", "19", "24", "22", "21")
        )
    )

    /**
     * Helper function to get data for a specific level.
     * @param level The level Color to fetch data for.
     * @return [LevelData] containing questions, texts, choices, and answers for the given level.
     */
    fun getLevelData(level: Int): LevelData? {
        return levelData[level]
    }

    // Data class to store questions, texts, choices, and correct answers per level
    data class LevelData(
        val questions: Array<Int>,           // Array of drawable resource IDs
        val questionTexts: Array<String>,    // Array of question texts
        val choices: Array<Array<String>>,   // Array of choices for each question
        val correctAnswers: Array<String>    // Array of correct answers
    )
}
package com.example.levelup

object QuestionAnswer_color5 {

    // Data for questions grouped by level
    val levelData = mapOf(

        1 to LevelData(
            questions = arrayOf(
                R.drawable.blue1,
                R.drawable.red1,
                R.drawable.pumpkin,
                R.drawable.purple,
                R.drawable.sun,
                R.drawable.grass
            ),
            questionTexts = arrayOf(
                "What color do you see in this picture?",
                "Can you tell me what color this picture is?",
                "What color is in this picture?",
                "Can you point out the color in this picture?",
                "What color do you think this picture is?",
                "Do you know what color is in this picture?"
            ),
            choices = arrayOf(
                arrayOf("Red", "Orange", "Blue", "Pink"),
                arrayOf("White", "Red", "Yellow", "Green"),
                arrayOf("Orange", "Black","Red", "Yellow"),
                arrayOf("Purple", "Blue", "Pink", "Red"),
                arrayOf("Green", "Yellow", "Blue", "Red"),
                arrayOf("Green", "Red", "Blue", "Pink")
            ),
            correctAnswers = arrayOf("Blue", "Red", "Orange", "Purple", "Yellow", "Green")
        ),

        2 to LevelData(
            questions = arrayOf(
                R.drawable.butterfly,
                R.drawable.clouds,
                R.drawable.grass,
                R.drawable.purple,
                R.drawable.sun,
                R.drawable.pumpkin
            ),
            questionTexts = arrayOf(
                "What color is the butterfly you saw in the video? What is the first letter of the color? _lack",
                "What color are the clouds you saw in the video? What is the first letter of the color? _hite",
                "What’s the first letter of this color? _reen",
                "Look! What letter starts this color? _urple",
                "What letter comes first in this color? _ellow",
                "Can you find the first letter in this color? _range"
            ),
            choices = arrayOf(
                arrayOf("R", "B", "O", "P"),
                arrayOf("W", "R", "Y", "G"),
                arrayOf("O", "B","R", "G"),
                arrayOf("P", "B", "Y", "R"),
                arrayOf("G", "Y", "B", "R"),
                arrayOf("G", "R", "O", "P")
            ),
            correctAnswers = arrayOf("B", "W", "G", "P", "Y", "O")
        ),

        3 to LevelData(
            questions = arrayOf(
                R.drawable.grass,
                R.drawable.butterfly,
                R.drawable.blue1,
                R.drawable.clouds,
                R.drawable.pumpkin,
                R.drawable.purple
            ),
            questionTexts = arrayOf(
                "Which color name starts with 'G'?",
                "Find the color that starts with 'B'!",
                "Which of these colors begins with 'B'?",
                "Can you pick the color that starts with 'W'?",
                "Which color starts with the letter 'O'?",
                "Find the color that starts with 'P'!"
            ),
            choices = arrayOf(
                arrayOf("Green", "Orange", "Blue", "Pink"),
                arrayOf("White", "Red", "Yellow", "Black"),
                arrayOf("Orange", "Blue","Red", "Yellow"),
                arrayOf("White", "Blue", "Pink", "Red"),
                arrayOf("Green", "Yellow", "Orange", "Red"),
                arrayOf("Green", "Purple", "Blue", "Pink")
            ),
            correctAnswers = arrayOf("Green", "Black", "Blue", "White", "Orange", "Purple")
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
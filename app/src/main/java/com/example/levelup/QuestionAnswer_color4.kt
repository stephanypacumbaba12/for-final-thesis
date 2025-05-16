package com.example.levelup

object QuestionAnswer_color4 {

    // Data for questions grouped by level
    val levelData = mapOf(

        1 to LevelData(
            questions = arrayOf(
                R.drawable.orange,
                R.drawable.red,
                R.drawable.yellow,
                R.drawable.black_girl,
                R.drawable.green,
                R.drawable.blue
            ),
            questionTexts = arrayOf(
                "Can you tell me the color of this character?",
                "What is the color of this character?",
                "Do you know what color this character is?",
                "Can you guess the color of this character?",
                "Which color is this character?",
                "What color does this character have?"
            ),
            choices = arrayOf(
                arrayOf("Red", "Orange", "Yellow", "Pink"),
                arrayOf("Blue", "Red", "Yellow", "Green"),
                arrayOf("Orange", "Black","Red", "Yellow"),
                arrayOf("Black", "Blue", "Pink", "Orange"),
                arrayOf("Green", "Pink", "Blue", "Red"),
                arrayOf("Orange", "Red", "Blue", "Pink")
            ),
            correctAnswers = arrayOf("Orange", "Red", "Yellow", "Black", "Green", "Blue")
        ),

        2 to LevelData(
            questions = arrayOf(
                R.drawable.pink,
                R.drawable.blue,
                R.drawable.red,
                R.drawable.orange,
                R.drawable.green,
                R.drawable.yellow
            ),
            questionTexts = arrayOf(
                "What letter does this color start with? _ink",
                "Can you guess the first letter of this color? _lue",
                "What’s the first letter of this color? _ed",
                "Look! What letter starts this color? _range",
                "What letter comes first in this color? _reen",
                "Can you find the first letter in this color? _ellow"
            ),
            choices = arrayOf(
                arrayOf("P", "T", "F", "C"),
                arrayOf("F", "G", "B", "O"),
                arrayOf("T", "R", "F", "E"),
                arrayOf("N", "S", "R", "O"),
                arrayOf("W", "T", "G", "F"),
                arrayOf("Y", "S", "E", "L")
            ),
            correctAnswers = arrayOf("P", "B", "R", "O", "G", "Y")
        ),

        3 to LevelData(
            questions = arrayOf(
                R.drawable.green,
                R.drawable.orange,
                R.drawable.pink,
                R.drawable.yellow,
                R.drawable.red,
                R.drawable.blue
            ),
            questionTexts = arrayOf(
                "Which color name starts with 'G'?",
                "Find the color that starts with 'O'!",
                "Which of these colors begins with 'P'?",
                "Can you pick the color that starts with 'Y'?",
                "Which color starts with the letter 'R'?",
                "Find the color that starts with 'B'!"
            ),
            choices = arrayOf(
                arrayOf("Red", "Pink", "Blue", "Green"),
                arrayOf("Orange", "Blue", "Purple", "Yellow"),
                arrayOf("Yellow", "Pink", "Red", "Black"),
                arrayOf("Yellow", "Orange", "Green", "Pink"),
                arrayOf("Gray", "Red", "Green", "Blue"),
                arrayOf("Red", "Yellow", "Blue", "Orange")
            ),
            correctAnswers = arrayOf("Green", "Orange", "Pink", "Yellow", "Red", "Blue")
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
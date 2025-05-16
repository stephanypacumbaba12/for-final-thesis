package com.example.levelup

object QuestionAnswer_color6 {

    // Data for questions grouped by level
    val levelData = mapOf(

        1 to LevelData(
            questions = arrayOf(
                R.drawable.blue2,
                R.drawable.white,
                R.drawable.red2,
                R.drawable.black2,
                R.drawable.purple2,
                R.drawable.orang2
            ),
            questionTexts = arrayOf(
                "Based on the video you watched, what color is shown in the picture?",
                "What color do you see in the picture from the video?",
                "Can you tell what color is in the picture from the video?",
                "Look at the picture! What color was shown in the video?",
                "From the video, what color do you see in the picture?",
                "Can you tell the color you saw in the video?"
            ),
            choices = arrayOf(
                arrayOf("Red", "Gray", "Black", "Blue"),
                arrayOf("White", "Pink", "Orange", "Yellow"),
                arrayOf("Orange", "Black","Red", "Green"),
                arrayOf("White", "Black", "Pink", "Red"),
                arrayOf("Purple", "Yellow", "Blue", "Red"),
                arrayOf("Green", "Red", "Blue", "Orange")
            ),
            correctAnswers = arrayOf("Blue", "White", "Red", "Black", "Purple", "Orange")
        ),

        2 to LevelData(
            questions = arrayOf(
                R.drawable.gray2,
                R.drawable.brown,
                R.drawable.green2,
                R.drawable.black2,
                R.drawable.purple2,
                R.drawable.orang2
            ),
            questionTexts = arrayOf(
                "Based on the video you watched, what does the combination of Black and White look like?",
                "Based on the video you watched, what does the combination of Black and Orange look like?",
                "Based on the video you watched, what does the combination of Blue and Yellow look like?",
                "Based on the video you watched, what does the combination of Red, Blue, and Yellow look like?",
                "Based on the video you watched, what does the combination of Red and Blue look like?",
                "Based on the video you watched, what does the combination of Yellow and Red look like?"
            ),
            choices = arrayOf(
                arrayOf("Red", "Gray", "Black", "Green"),
                arrayOf("White", "Brown", "Orange", "Yellow"),
                arrayOf("Orange", "Black","Red", "Green"),
                arrayOf("White", "Black", "Pink", "Red"),
                arrayOf("Purple", "Yellow", "Blue", "Red"),
                arrayOf("Green", "Red", "Blue", "Orange")
            ),
            correctAnswers = arrayOf("Gray", "Brown", "Green", "Black", "Purple", "Orange")
        ),

        3 to LevelData(
            questions = arrayOf(
                R.drawable.black2,
                R.drawable.gray2,
                R.drawable.orang2,
                R.drawable.pinky2,
                R.drawable.purple2,
                R.drawable.green2
            ),
            questionTexts = arrayOf(
                "What color comes from Red, Blue, and Yellow? First letter: _lack",
                "What color do Black and White make? First letter: _ray",
                "What color do Yellow and Red make? First letter: _range",
                "What color do Red and White make? First letter: _ink",
                "What color do Red and Blue make? First letter: _urple",
                "What color do Blue and Yellow make? First letter: _reen"
            ),
            choices = arrayOf(
                arrayOf("R", "B", "O", "P"),
                arrayOf("W", "R", "Y", "G"),
                arrayOf("O", "B","R", "G"),
                arrayOf("Y", "B", "P", "R"),
                arrayOf("G", "P", "B", "R"),
                arrayOf("P", "R", "O", "G")
            ),
            correctAnswers = arrayOf("B", "G", "O", "P", "P", "G")
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
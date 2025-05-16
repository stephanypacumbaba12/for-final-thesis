package com.example.levelup

object QuestionAnswer_sound6 {

    // Data for questions grouped by level
    val levelData = mapOf(

        1 to LevelData(
            questions = arrayOf(
                R.drawable.croco,
                R.drawable.gorila,
                R.drawable.tiger1,
                R.drawable.rhino,
                R.drawable.wolf,
                R.drawable.lion2
            ),
            questionTexts = arrayOf(
                "Can you tell me what animal this is from the picture?",
                "What animal do you think this is, can you guess?",
                "Do you know the name of this animal?",
                "What kind of animal looks like this?",
                "What animal do you see in this picture?",
                "Can you tell me what animal you think this is?"
            ),
            correctAnswers = arrayOf("Crocodile", "Gorilla", "Tiger", "Rhinoceros", "Wolf", "Lion")
        ),

        2 to LevelData(
            questions = arrayOf(
                R.drawable.pluto,
                R.drawable.venus,
                R.drawable.uranus,
                R.drawable.jupeter,
                R.drawable.mercury,
                R.drawable.earth
            ),
            questionTexts = arrayOf(
                "Can you say the name of the planet in the picture?",
                "What planet is this? Say your answer out loud!",
                "Look at the picture! Can you name this planet?",
                "Which planet do you see? Say its name!",
                "Say the name of the planet that is shown above!",
                "What is the name of this planet? Tell me with your voice!"
            ),
            correctAnswers = arrayOf("Pluto", "Venus", "Uranus", "Jupiter", "Mercury", "Earth")
        ),

        3 to LevelData(
            questions = arrayOf(
                R.drawable.star2,
                R.drawable.heart,
                R.drawable.square,
                R.drawable.triangel,
                R.drawable.circle,
                R.drawable.rectangle,
            ),
            questionTexts = arrayOf(
                "Can you say the name of this shape?",
                "Look at this! What shape do you see? Say it out loud!",
                "Can you guess the shape? Tell me your answer!",
                "What shape do you see here? Say its name!",
                "What is the name of this shape? Speak your answer!",
                "Look closely! What shape is this?"
            ),
            correctAnswers = arrayOf("Star", "Heart", "Square", "Triangle", "Circle", "Rectangle")
        ),
    )

    /**
     * Helper function to get data for a specific level.
     * @param level The level Animals to fetch data for.
     * @return [LevelData] containing questions, texts, choices, and answers for the given level.
     */
    fun getLevelData(level: Int): LevelData? {
        return levelData[level]
    }

    // Data class to store questions, texts, choices, and correct answers per level
    data class LevelData(
        val questions: Array<Int>,           // Array of drawable resource IDs
        val questionTexts: Array<String>,    // Array of question texts
        val correctAnswers: Array<String>    // Array of correct answers
    )
}
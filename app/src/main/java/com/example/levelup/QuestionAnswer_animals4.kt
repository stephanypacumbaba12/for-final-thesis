package com.example.levelup

object QuestionAnswer_animals4 {


    // Data for questions grouped by level
    val levelData = mapOf(

        1 to LevelData(
            questions = arrayOf(
                R.drawable.monkey,
                R.drawable.lion1,
                R.drawable.giraff,
                R.drawable.zebra,
                R.drawable.bear,
                R.drawable.elepant
            ),
            questionTexts = arrayOf(
                "Can you tell me what animal this is from the picture?",
                "What animal do you think this is, can you guess?",
                "Do you know the name of this animal?",
                "What kind of animal looks like this?",
                "What animal do you see in this picture?",
                "Can you tell me what animal you think this is?"
            ),
            choices = arrayOf(
                arrayOf("Crocodile", "Lion", "Monkey", "Giraffe"),
                arrayOf("Rhinoceros", "Bear", "Elephant", "Lion"),
                arrayOf("Gorilla", "Giraffe", "Monkey", "Tiger"),
                arrayOf("Wolf", "Crocodile", "Lion", "Zebra"),
                arrayOf("Zebra", "Rhinoceros", "Monkey", "Bear"),
                arrayOf("Rhinoceros", "Elephant", "Giraffe", "Zebra")
            ),
            correctAnswers = arrayOf("Monkey", "Lion", "Giraffe", "Zebra", "Bear", "Elephant")
        ),

        2 to LevelData(
            questions = arrayOf(
                R.drawable.croco,
                R.drawable.gorila,
                R.drawable.tiger1,
                R.drawable.rhino,
                R.drawable.wolf,
                R.drawable.lion2
            ),
            questionTexts = arrayOf(
                "What letter comes first in this picture? _rocodile",
                "Can you guess the first letter of this picture? _orilla",
                "What’s the first letter of this picture? _iger",
                "Look! What letter starts this picture? _hinoceros",
                "What letter does this picture start with? _olf",
                "Can you find the first letter in this picture? _ion"
            ),
            choices = arrayOf(
                arrayOf("S", "T", "F", "C"),
                arrayOf("F", "G", "E", "O"),
                arrayOf("T", "S", "F", "E"),
                arrayOf("N", "S", "R", "E"),
                arrayOf("W", "T", "S", "F"),
                arrayOf("O", "S", "E", "L")
            ),
            correctAnswers = arrayOf("C", "G", "T", "R", "W", "L")
        ),

        3 to LevelData(
            questions = arrayOf(
                R.drawable.rhino,
                R.drawable.monkey1,
                R.drawable.wolf,
                R.drawable.elephant1,
                R.drawable.croco,
                R.drawable.zebra1
            ),
            questionTexts = arrayOf(
                "Which word starts with the letter 'R'?",
                "Find the word that starts with 'M'!",
                "Which animal name begins with 'W'?",
                "Which word starts with the letter 'E'?",
                "Find the word that starts with 'C'!",
                "Which of these words begins with 'Z'?"
            ),
            choices = arrayOf(
                arrayOf("Lion", "Rhinoceros", "Tiger", "Gorilla"),
                arrayOf("Monkey", "Wolf", "Bear", "Giraffe"),
                arrayOf("Zebra", "Bear", "Monkey", "Wolf"),
                arrayOf("Wolf", "Elephant", "Lion", "Giraffe"),
                arrayOf("Tiger", "Monkey", "Crocodile", "Wolf"),
                arrayOf("Zebra", "Crocodile", "Elephant", "Bear")
            ),
            correctAnswers = arrayOf("Rhinoceros", "Monkey", "Wolf", "Elephant", "Crocodile", "Zebra")
        )
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
        val choices: Array<Array<String>>,   // Array of choices for each question
        val correctAnswers: Array<String>    // Array of correct answers
    )
}

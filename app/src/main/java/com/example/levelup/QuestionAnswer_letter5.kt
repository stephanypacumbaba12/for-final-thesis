package com.example.levelup

import android.graphics.Path

object QuestionAnswer_letter5 {

    // Data for questions grouped by level
    val levelData = mapOf(

        1 to LevelData(
            questions = arrayOf(
                R.drawable.octupus,
                R.drawable.apple1,
                R.drawable.hat,
                R.drawable.penguin,
                R.drawable.elephant1,
                R.drawable.monkey2
            ),
            questionTexts = arrayOf(
                "Based on the video you watched, O is for what? Can you choose the correct answer from the choices?",
                "A is for what in the video? Choose the correct answer!",
                "What does H stand for in the video? Tap the right answer!",
                "Can you find the right answer? P is for what in the video?",
                "Look at the choices! What does E stand for in the video?",
                "From the video you watched, what does M stand for? Can you pick the right answer?"
            ),
            choices = arrayOf(
                arrayOf("Apple", "Octopus", "Ball", "Dog"),
                arrayOf("Ice Cream", "Queen", "Apple", "Elephant"),
                arrayOf("Hat", "Kite", "Penguin", "Fish"),
                arrayOf("Fish", "Penguin", "Ball", "Cat"),
                arrayOf("Cat", "Monkey", "Dog", "Elephant"),
                arrayOf("Goat", "Monkey", "Hat", "Lion")
            ),
            correctAnswers = arrayOf("Octopus", "Apple", "Hat", "Penguin", "Elephant", "Monkey")
        ),

        2 to LevelData(

            questions = arrayOf(
                R.drawable.lion2,
                R.drawable.nest,
                R.drawable.jelly,
                R.drawable.fish,
                R.drawable.goat,
                R.drawable.icecream
            ),
            questionTexts = arrayOf(
                "In the video you watched, what does L stand for? Choose the right answer!",
                "What word starts with N in the video? Pick the correct one!",
                "J is for what? Can you tap the right answer?",
                "Can you find the correct answer? F stands for what in the video?",
                "What does G mean in the video? Choose the right answer!",
                "Look at the choices! What word starts with I in the video?"
            ),
            choices = arrayOf(
                arrayOf("Lion", "Octopus", "Ball", "Dog"),
                arrayOf("Ice Cream", "Queen", "Apple", "Nest"),
                arrayOf("Hat", "Jelly", "Penguin", "Fish"),
                arrayOf("Queen", "Penguin", "Fish", "Cat"),
                arrayOf("Cat", "Goat", "Dog", "Elephant"),
                arrayOf("Goat", "Monkey", "Hat", "Ice Cream")
            ),
            correctAnswers = arrayOf("Lion", "Nest", "Jelly", "Fish", "Goat", "Ice Cream")
        ),
        3 to Level3TracingData(
            numberImages = arrayOf(
                R.drawable.f, R.drawable.m, R.drawable.o,
                R.drawable.p, R.drawable.k, R.drawable.l
            ),
            questionTexts = arrayOf(
                "Can you trace the letter F",
                "Can you trace the letter M?",
                "Can you trace the letter O?",
                "Can you trace the letter P?",
                "Can you trace the letter K",
                "Can you trace the letter L?"



            ),
            numberPaths = arrayOf(
                // Letter F
                Path().apply {
                    moveTo(350f, 500f)
                    lineTo(350f, 300f)
                    lineTo(450f, 300f)
                    moveTo(350f, 400f)
                    lineTo(420f, 400f)
                },

                // Letter M
                Path().apply {
                    moveTo(300f, 500f)
                    lineTo(300f, 300f)
                    lineTo(400f, 400f)
                    lineTo(500f, 300f)
                    lineTo(500f, 500f)
                },

                // Letter O
                Path().apply {
                    moveTo(400f, 300f)
                    quadTo(500f, 300f, 500f, 400f)
                    quadTo(500f, 500f, 400f, 500f)
                    quadTo(300f, 500f, 300f, 400f)
                    quadTo(300f, 300f, 400f, 300f)
                },

                // Letter P
                Path().apply {
                    moveTo(350f, 500f)
                    lineTo(350f, 300f)
                    quadTo(450f, 300f, 450f, 350f)
                    quadTo(450f, 400f, 350f, 400f)
                },

                // Letter K
                Path().apply {
                    moveTo(350f, 500f)
                    lineTo(350f, 300f)
                    moveTo(350f, 400f)
                    lineTo(450f, 300f)
                    moveTo(350f, 400f)
                    lineTo(450f, 500f)
                },

                // Letter L
                Path().apply {
                    moveTo(350f, 300f)
                    lineTo(350f, 500f)
                    lineTo(450f, 500f)
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
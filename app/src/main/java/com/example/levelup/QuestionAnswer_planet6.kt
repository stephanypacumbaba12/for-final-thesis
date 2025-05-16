package com.example.levelup

object QuestionAnswer_planet6 {

    // Data for questions grouped by level
    val levelData = mapOf(

        1 to LevelData(
            questions = arrayOf(
                R.drawable.jupeter,
                R.drawable.mars,
                R.drawable.neptune,
                R.drawable.earth,
                R.drawable.pluto,
                R.drawable.saturn
            ),
            questionTexts = arrayOf(
                "I am the largest planet'? Who am I",
                "Who said about this planet, 'I am the hot hot planet'?",
                "Who said 'I am made of gas' in the video you watched earlier?",
                "Based on the video you watched, who said 'I am your Planet'? ",
                "Do you remember who said 'I'm the farthest from the Sun' in the video earlier?",
                "Who claimed 'Im the planet with the rings' in the video you just watched?"
            ),
            choices = arrayOf(
                arrayOf("Earth", "Mercury", "Neptune", "Jupiter"),
                arrayOf("Mars", "Saturn", "Venus", "Neptune"),
                arrayOf("Mercury", "Earth","Neptune", "Mars"),
                arrayOf("Sun", "Earth", "Pluto", "Saturn"),
                arrayOf("Mars", "Saturn", "Uranus", "Pluto"),
                arrayOf("Mercury", "Saturn", "Pluto", "Sun")
            ),
            correctAnswers = arrayOf("Jupiter", "Mars", "Neptune", "Earth", "Pluto", "Saturn")
        ),

        2 to LevelData(
            questions = arrayOf(
                R.drawable.mercury,
                R.drawable.venus,
                R.drawable.uranus,
                R.drawable.mars,
                R.drawable.saturn,
                R.drawable.neptune
            ),
            questionTexts = arrayOf(
                "Who said, 'I am closest to the sun'? What is the first letter of its name? _ercury",
                "Who said, 'I am the brightest in the sky'? What is the first letter of its name? _enus",
                "Who said, 'I have 27 moons'? What is the first letter of its name? _ranus",
                "Who said, 'I am the hottest planet'? What is the first letter of its name? _ars",
                "Who said, 'I am the planet with rings'? What is the first letter of its name? _aturn",
                "Who said, 'I am made of gas'? What is the first letter of its name? _eptune"
            ),
            choices = arrayOf(
                arrayOf("M", "B", "O", "P"),
                arrayOf("W", "R", "Y", "V"),
                arrayOf("O", "U","R", "G"),
                arrayOf("P", "B", "M", "R"),
                arrayOf("G", "S", "B", "R"),
                arrayOf("G", "R", "O", "N")
            ),
            correctAnswers = arrayOf("M", "V", "U", "M", "S", "N")
        ),

        3 to LevelData(
            questions = arrayOf(
                R.drawable.pluto,
                R.drawable.venus,
                R.drawable.uranus,
                R.drawable.jupeter,
                R.drawable.mercury,
                R.drawable.earth
            ),
            questionTexts = arrayOf(
                "Who said, 'I am the farthest from the Sun'? The first letter is P.",
                "Who said, 'I am the brightest in the sky'? The first letter is V.",
                "Who said, 'I have 27 moons'? The first letter is U.",
                "Who said, 'I am the largest planet'? The first letter is J.",
                "Who said, 'I am closest to the Sun'? The first letter is M.",
                "Who said, 'I am your planet'? The first letter is E."
            ),
            choices = arrayOf(
                arrayOf("Mars", "Earth", "Saturn", "Pluto"),
                arrayOf("Neptune", "Venus", "Jupiter", "Venus"),
                arrayOf("Mercury", "Venus","Uranus", "Pluto"),
                arrayOf("Jupiter", "Earth", "Saturn", "Mars"),
                arrayOf("Jupiter", "Pluto", "Mercury", "Venus"),
                arrayOf("Mercury", "Earth", "Uranus", "Neptune")
            ),
            correctAnswers = arrayOf("Pluto", "Venus", "Uranus", "Jupiter", "Mercury", "Earth")
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
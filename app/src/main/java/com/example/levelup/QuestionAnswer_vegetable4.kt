package com.example.levelup

object QuestionAnswer_vegetable4 {

    val levelData = mapOf(
        1 to LevelData(
            questions = arrayOf(
                R.drawable.malunggay,
                R.drawable.talong,
                R.drawable.vegetables,
                R.drawable.ampalaya,
                R.drawable.kalabasa,
                R.drawable.patatas
            ),
            questionTexts = arrayOf(
                "Unsa nga gulay ang imong nakita sa hulagway sa taas?",
                "Kini nga gulay kay kolor tapol, unsa kini?",
                "Mahimo ba nimo'ng isulti kung unsa ang hulagway nga imong nakita sa selpon?",
                "Kani nga gulay nga imong makita sa hulagway sa taas kay pait. Unsa kini?",
                "Ang gulay nga makita nimo sa ibabaw makatabang aron mo-tin-aw ang imong panan-aw. Unsa kini?",
                "Kita ka anang gulay sa ibabaw? Mahimo ba nimo'ng isulti kung unsa kini?"
            ),
            choices = arrayOf(
                arrayOf("Luya", "Ahos", "Letsugas", "Kalamunggay"),
                arrayOf("Talong", "Mais", "Pipino", "Sibuyas"),
                arrayOf("Karne", "Bulak", "Gulay", "Prutas"),
                arrayOf("Pechay", "Ampalaya", "Sili", "Labanos"),
                arrayOf("Kalabasa", "Sibuyas Dahon", "Kamote", "Kamatis"),
                arrayOf("Pipino", "Tawgi", "Repolyo", "Patatas")
            ),
            correctAnswers = arrayOf("Kalamunggay", "Talong", "Gulay", "Ampalaya", "Kalabasa", "Patatas"),
            audioResources = arrayOf(
                R.raw.quest1, R.raw.quest2, R.raw.quest3, R.raw.quest4, R.raw.quest5, R.raw.quest6,
                0, 0, 0, 0, 0, 0
            ),
            answerAudioResources = arrayOf(
                arrayOf(R.raw.luya_audio, R.raw.ahos_audio, R.raw.letsugas_audio, R.raw.kalamunggay_audio),
                arrayOf(R.raw.talong, R.raw.mais, R.raw.pipino, R.raw.sibuyas),
                arrayOf(R.raw.karne, R.raw.bulak, R.raw.gulay, R.raw.prutas),
                arrayOf(R.raw.pechay, R.raw.ampalaya, R.raw.sili, R.raw.labanos),
                arrayOf(R.raw.kalabasa, R.raw.sibuyas_dahon, R.raw.kamote, R.raw.kamatis),
                arrayOf(R.raw.pipino, R.raw.tawgi, R.raw.repolyo, R.raw.patatas)
            ),
            correctFeedbackAudio = Array(6) { R.raw.tama }, // Same "tama.m4a" for all correct answers
            wrongFeedbackAudio = arrayOf(
                R.raw.mali_kalamunggay,
                R.raw.mali_talong,
                R.raw.mali_gulay,
                R.raw.mali_ampalaya,
                R.raw.mali_kalabasa,
                R.raw.mali_patatas
            )
        ),
        2 to LevelData(
            questions = arrayOf(
                R.drawable.labanos,
                R.drawable.repolyo,
                R.drawable.kamote,
                R.drawable.pipino,
                R.drawable.mais,
                R.drawable.kamatis
            ),
            questionTexts = arrayOf(
                "Mahimo ba nimo'ng pilion ang sakto nga tubag sa mga kapilian sa ubos kung unsa nga gulay ang imong nakita sa ibabaw?",
                "Kani nga gulay kay murag bola ug lingin. Mahimo ba nimo'ng pilion ang sakto nga tubag sa ubos?",
                "Base sa hulagway nga imong nakita sa ibabaw, unsa kini? Pilia ang sakto nga tubag sa mga kapilian.",
                "Ang gulay nga imong nakita sa selpon, mahimo ba nimo'ng pilion ang sakto nga tubag?",
                "Base sa hulagway nga imong nakita, mahimo ba nimo'ng pilion sa ubos kung unsa kini?",
                "Kini nga gulay kay pula kung mahinog. Pilia ang sakto nga tubag sa mga kapilian."
            ),
            choices = arrayOf(
                arrayOf("Kalamunggay", "Labanos", "Gulay", "Ampalaya"),
                arrayOf("Repolyo", "Talong", "Kalabasa", "Patatas"),
                arrayOf("Luya", "Tawgi", "Sili", "Kamote"),
                arrayOf("Ahos", "Atsal", "Pipino", "Letsugas"),
                arrayOf("Mais", "Sibuyas", "Pechay", "Talong"),
                arrayOf("Sibuyas Dahon", "Kamote", "Kamatis", "Kalamunggay")
            ),
            correctAnswers = arrayOf("Labanos", "Repolyo", "Kamote", "Pipino", "Mais", "Kamatis"),
            audioResources = arrayOf(
                R.raw.quest1lvl2, R.raw.quest2lvl2, R.raw.quest3lvl2, R.raw.quest4lvl2, R.raw.quest5lvl2, R.raw.quest6lvl2,
                0, 0, 0, 0, 0
            ),
            answerAudioResources = arrayOf(
                arrayOf(R.raw.kalamunggay_audio, R.raw.labanos, R.raw.gulay, R.raw.ampalaya),
                arrayOf(R.raw.repolyo, R.raw.talong, R.raw.kalabasa, R.raw.patatas),
                arrayOf(R.raw.luya_audio, R.raw.tawgi, R.raw.sili, R.raw.kamote),
                arrayOf(R.raw.ahos_audio, R.raw.atsal, R.raw.pipino, R.raw.letsugas_audio),
                arrayOf(R.raw.mais, R.raw.sibuyas, R.raw.pechay, R.raw.talong),
                arrayOf(R.raw.sibuyas_dahon, R.raw.kamote, R.raw.kamatis, R.raw.kalamunggay_audio)
            ),
            correctFeedbackAudio = Array(6) { R.raw.tama }, // Add "tama.m4a" for correct answers
            wrongFeedbackAudio = arrayOf(
                R.raw.mali_labanos,
                R.raw.mali_repolyo,
                R.raw.mali_kamote,
                R.raw.mali_pipino,
                R.raw.mali_mais,
                R.raw.mali_kamatis
            )
        ),
        3 to LevelData(
            questions = arrayOf(
                R.drawable.mais,
                R.drawable.kalabasa,
                R.drawable.repolyo,
                R.drawable.kamote,
                R.drawable.vegetables,
                R.drawable.talong
            ),
            questionTexts = arrayOf(
                "Unsang letra ang naa sa kataposan sa kini nga hulagway? mai_",
                "Mahimo ba nimo'ng mahibal-an ang kataposang letra sa kini nga hulagway? kalabas_",
                "Unsay kataposang letra sa kini nga hulagway? repoly_",
                "Tan-awa! Unsang letra ang naa sa kataposan sa kini nga hulagway? kamot_",
                "Unsang letra ang kataposang moabot sa kini nga hulagway? gula_",
                "Makita ba nimo ang kataposang letra sa kini nga hulagway? talon_"
            ),
            choices = arrayOf(
                arrayOf("S", "T", "F", "C"),
                arrayOf("F", "G", "E", "A"),
                arrayOf("T", "O", "F", "E"),
                arrayOf("N", "E", "R", "S"),
                arrayOf("W", "T", "Y", "F"),
                arrayOf("G", "S", "E", "L")
            ),
            correctAnswers = arrayOf("S", "A", "O", "E", "Y", "G"),
            audioResources = arrayOf(
                R.raw.quest1lvl3, R.raw.quest2lvl3, R.raw.quest3lvl3, R.raw.quest4lvl3, R.raw.quest5lvl3, R.raw.quest6lvl3,
                0, 0, 0, 0, 0
            ),
            answerAudioResources = arrayOf(
                arrayOf(R.raw.letter_s, R.raw.letter_t, R.raw.letter_f, R.raw.letter_c),
                arrayOf(R.raw.letter_f, R.raw.letter_g, R.raw.letter_e, R.raw.letter_a),
                arrayOf(R.raw.letter_t, R.raw.letter_o, R.raw.letter_f, R.raw.letter_e),
                arrayOf(R.raw.letter_n, R.raw.letter_e, R.raw.letter_r, R.raw.letter_s),
                arrayOf(R.raw.letter_w, R.raw.letter_t, R.raw.letter_y, R.raw.letter_f),
                arrayOf(R.raw.letter_g, R.raw.letter_s, R.raw.letter_e, R.raw.letter_l)
            ),
            correctFeedbackAudio = Array(6) { R.raw.tama }, // Add "tama.m4a" for correct answers
            wrongFeedbackAudio = arrayOf(
                R.raw.mali_s,      // For the first question (Mais)
                R.raw.mali_a,  // For the second question (Kalabasa)
                R.raw.mali_o,   // For the third question (Repolyo)
                R.raw.mali_e,    // For the fourth question (Kamote)
                R.raw.mali_y,     // For the fifth question (Vegetables)
                R.raw.mali_g     // For the sixth question (Talong)
            )
        )
    )

    fun getLevelData(level: Int): LevelData? {
        return levelData[level]
    }

    data class LevelData(
        val questions: Array<Int>,
        val questionTexts: Array<String>,
        val choices: Array<Array<String>>,
        val correctAnswers: Array<String>,
        val audioResources: Array<Int>,
        val answerAudioResources: Array<Array<Int>>, // Audio for each choice
        val correctFeedbackAudio: Array<Int>,        // Audio for correct answers
        val wrongFeedbackAudio: Array<Int>           // Audio for wrong answers
    )
}
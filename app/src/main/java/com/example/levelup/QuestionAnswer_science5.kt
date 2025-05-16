package com.example.levelup

object QuestionAnswer_science5 {

    val levelData = mapOf(
        1 to LevelData(
            questions = arrayOf(
                R.drawable.head,
                R.drawable.hair,
                R.drawable.ears,
                R.drawable.face,
                R.drawable.eyelash,
                R.drawable.eye
            ),
            questionTexts = arrayOf(
                "Unsa nga parte sa lawas ang imong nakita sa ibabaw?",
                "Sa imahe nga imong nakita sa ibabaw, unsa nga parte sa lawas kini?",
                "Sa imong nakita nga hulagway sa ibabaw, kini atong gamiton para makadungog. Unsa kini?",
                "Unsa ang imong nakita sa ibabaw nga hulagway?",
                "Unsa ang tawag sa parte sa lawas nga imong nakita sa ibabaw?",
                "Sa imong nakita nga hulagway sa ibabaw, kini atong gamiton para makakita. Unsa kini?"
            ),
            choices = arrayOf(
                arrayOf("Kilay", "Agtang", "Aping", "Ulo"),
                arrayOf("Buhok", "Ngabil", "Li-og", "Abaga"),
                arrayOf("Palad", "Kamot", "Dalunggan", "Tud-lo"),
                arrayOf("Dughan", "Nawong", "I-lok", "Pusod"),
                arrayOf("Bat-ang", "Tuhod", "Tiil", "Pilok"),
                arrayOf("Mata", "Kuyamoy", "Tikod", "Bat-ang")
            ),
            correctAnswers = arrayOf("Ulo", "Buhok", "Dalunggan", "Nawong", "Pilok", "Mata"),
            audioResources = arrayOf(
                R.raw.quest1_sci,
                R.raw.quest2_sci,
                R.raw.quest3_sci,
                R.raw.quest4_sci,
                R.raw.quest5_sci,
                R.raw.quest6_sci
            ),
            answerAudioResources = arrayOf(
                arrayOf(R.raw.kilay, R.raw.agtang, R.raw.aping, R.raw.ulo),
                arrayOf(R.raw.buhok, R.raw.ngabil, R.raw.liog, R.raw.abaga),
                arrayOf(R.raw.palad, R.raw.kamot, R.raw.dalunggan, R.raw.tudlo),
                arrayOf(R.raw.dughan, R.raw.nawong, R.raw.ilok, R.raw.pusod),
                arrayOf(R.raw.batang, R.raw.tuhod, R.raw.tiil, R.raw.pilok),
                arrayOf(R.raw.mata, R.raw.kuyamoy, R.raw.tikod, R.raw.batang)
            ),
            correctFeedbackAudio = Array(6) { R.raw.tama }, // Same "tama.m4a" for all correct answers
            wrongFeedbackAudio = arrayOf(
                R.raw.mali_ulo,
                R.raw.mali_buhok,
                R.raw.mali_dalunggan,
                R.raw.mali_nawong,
                R.raw.mali_pilok,
                R.raw.mali_mata
            )
        ),
        2 to LevelData(
            questions = arrayOf(
                R.drawable.elbow,
                R.drawable.cheeks,
                R.drawable.hand,
                R.drawable.finger,
                R.drawable.lip,
                R.drawable.mouth
            ),
            questionTexts = arrayOf(
                "Sa imong nakita nga hulagway sa ibabaw, unsa kini sa Binisaya?",
                "Unsa ang tawag ani sa Binisaya base sa hulagway nga imong nakita?",
                "Sa hulagway nga imong nakita sa ibabaw, unsa kini?",
                "Sa hulagway nga imong nakita sa ibabaw, unsa ang tama nga ngalan niini?",
                "Sa imong nakita nga hulagway sa ibabaw, unsay tawag ani?",
                "Mahimo ba nimo isulti ang tawag sa hulagway nga imong nakita?"
            ),
            choices = arrayOf(
                arrayOf("Kilay", "Siko", "Aping", "Ulo"),
                arrayOf("Aping", "Ngabil", "Li-og", "Abaga"),
                arrayOf("Palad", "Dalunggan", "Kamot", "Tud-lo"),
                arrayOf("Dughan", "Nawong", "I-lok", "Tudlo"),
                arrayOf("Bat-ang", "Ngabil", "Tiil", "Pilok"),
                arrayOf("Likod", "Kuyamoy", "Baba", "Bat-ang")
            ),
            correctAnswers = arrayOf("Siko", "Aping", "Kamot", "Tudlo", "Ngabil", "Baba"),
            audioResources = arrayOf(
                R.raw.quest1_sci2,
                R.raw.quest2_sci2,
                R.raw.quest3_sci2,
                R.raw.quest4_sci2,
                R.raw.quest5_sci2,
                R.raw.quest6_sci2
            ),
            answerAudioResources = arrayOf(
                arrayOf(R.raw.kilay, R.raw.siko, R.raw.aping, R.raw.ulo),
                arrayOf(R.raw.aping, R.raw.ngabil, R.raw.liog, R.raw.abaga),
                arrayOf(R.raw.palad, R.raw.dalunggan, R.raw.kamot, R.raw.tudlo),
                arrayOf(R.raw.dughan, R.raw.nawong, R.raw.ilok, R.raw.tudlo),
                arrayOf(R.raw.batang, R.raw.ngabil, R.raw.tiil, R.raw.pilok),
                arrayOf(R.raw.likod, R.raw.kuyamoy, R.raw.baba, R.raw.batang)
            ),
            correctFeedbackAudio = Array(6) { R.raw.tama }, // Same "tama.m4a" for all correct answers
            wrongFeedbackAudio = arrayOf(
                R.raw.mali_siko, // For the first question (Kalamunggay)
                R.raw.mali_aping,      // For the second question (Talong)
                R.raw.mali_kamot,       // For the third question (Gulay)
                R.raw.mali_tudlo,    // For the fourth question (Ampalaya)
                R.raw.mali_ngabil,    // For the fifth question (Kalabasa)
                R.raw.mali_baba     // For the sixth question (Patatas)
            )
        ),
        3 to LevelData(
            questions = arrayOf(
                R.drawable.neck,
                R.drawable.nose,
                R.drawable.stomach,
                R.drawable.waist,
                R.drawable.tounge,
                R.drawable.teeth
            ),
            questionTexts = arrayOf(
                "Base sa video nga imong nakita, unsa kini sa Binisaya ang 'Neck'?",
                "Unsa ang tawag ani sa Binisaya base sa video nga imong nakita?",
                "Sa video nga imong nakita, unsay tawag sa Binisaya ani?",
                "Makabalo ka unsay Binisaya sa 'Waist' base sa video?",
                "Base sa imong nakita sa video, unsa ang tawag sa Binisaya sa 'Tongue'?",
                "Mahimo ba nimo isulti ang Binisaya sa 'Teeth' base sa video nga imong nakita?"
            ),
            choices = arrayOf(
                arrayOf("Kilay", "Siko", "Li-og", "Ulo"),
                arrayOf("Ilong", "Ngabil", "Li-og", "Abaga"),
                arrayOf("Palad", "Dalunggan", "Kamot", "Tiyan"),
                arrayOf("Dughan", "Hawak", "I-lok", "Tudlo"),
                arrayOf("Bat-ang", "Ngabil", "Dila", "Pilok"),
                arrayOf("Ngipon", "Kuyamoy", "Baba", "Bat-ang")
            ),
            correctAnswers = arrayOf("Li-og", "Ilong", "Tiyan", "Hawak", "Dila", "Ngipon"),
            audioResources = arrayOf(
                R.raw.quest1_sci3,
                R.raw.quest2_sci3,
                R.raw.quest3_sci3,
                R.raw.quest4_sci3,
                R.raw.quest5_sci3,
                R.raw.quest6_sci3
            ),
            answerAudioResources = arrayOf(
                arrayOf(R.raw.kilay, R.raw.siko, R.raw.liog, R.raw.ulo),
                arrayOf(R.raw.ilong, R.raw.ngabil, R.raw.liog, R.raw.abaga),
                arrayOf(R.raw.palad, R.raw.dalunggan, R.raw.kamot, R.raw.tiyan),
                arrayOf(R.raw.dughan, R.raw.hawak, R.raw.ilok, R.raw.tudlo),
                arrayOf(R.raw.batang, R.raw.ngabil, R.raw.dila, R.raw.pilok),
                arrayOf(R.raw.ngipon, R.raw.kuyamoy, R.raw.baba, R.raw.batang)
            ),
            correctFeedbackAudio = Array(6) { R.raw.tama }, // Same "tama.m4a" for all correct answers
            wrongFeedbackAudio = arrayOf(
                R.raw.mali_liog, // For the first question (Kalamunggay)
                R.raw.mali_ilong,      // For the second question (Talong)
                R.raw.mali_tiyan,       // For the third question (Gulay)
                R.raw.mali_hawak,    // For the fourth question (Ampalaya)
                R.raw.mali_dila,    // For the fifth question (Kalabasa)
                R.raw.mali_ngipon      // For the sixth question (Patatas)
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
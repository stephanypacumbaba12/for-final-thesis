package com.example.levelup

import android.graphics.Path

object QuestionAnswer_shape6 {
    val levelData = mapOf(
        1 to LevelData(
            questions = arrayOf(
                R.drawable.triangle1,
                R.drawable.rectangle1,
                R.drawable.star2,
                R.drawable.circle1,
                R.drawable.heart,
                R.drawable.diamon
            ),
            questionTexts = arrayOf(
                "Base sa video nga imong nakita, unsa ni nga hulma? Unsa ang unang litra? _riyanggulo",
                "Unsa ni nga porma nga naa sa video? Unsa ang unang litra? _ectanggulo",
                "Unsa ni nga shape nga gipakita sa video? Unsa ang unang litra? _ituon",
                "Base sa video, makaila ka ani nga porma? Unsa ang unang litra? _ingin",
                "Unsa ni nga dagway sa video? Unsa ang unang litra? _asing-kasingon",
                "Tan-awa ang video, unsa ni nga hulma? Unsa ang unang litra? _iyamante"
            ),
            choices = arrayOf(
                arrayOf("S", "T", "U", "I"),
                arrayOf("C", "X", "B", "R"),
                arrayOf("R", "B", "L", "P"),
                arrayOf("K", "P", "L", "R"),
                arrayOf("K", "M", "L", "A"),
                arrayOf("T", "G", "D", "U")
            ),
            correctAnswers = arrayOf("T", "R", "B", "L", "K", "D"),
            audioResources = arrayOf(
                R.raw.quest1_shape,
                R.raw.quest2_shape,
                R.raw.quest3_shape,
                R.raw.quest4_shape,
                R.raw.quest5_shape,
                R.raw.quest6_shape
            ),
            answerAudioResources = arrayOf(
                arrayOf(R.raw.letter_s, R.raw.letter_t, R.raw.letter_u, R.raw.letter_i),
                arrayOf(R.raw.letter_c, R.raw.letter_x, R.raw.letter_b, R.raw.letter_r),
                arrayOf(R.raw.letter_r, R.raw.letter_b, R.raw.letter_l, R.raw.letter_p),
                arrayOf(R.raw.letter_k, R.raw.letter_p, R.raw.letter_l, R.raw.letter_r),
                arrayOf(R.raw.letter_k, R.raw.letter_m, R.raw.letter_l, R.raw.letter_a),
                arrayOf(R.raw.letter_t, R.raw.letter_g, R.raw.letter_d, R.raw.letter_u)
            ),
            correctFeedbackAudio = Array(6) { R.raw.tama }, // Same "tama.m4a" for all correct answers
            wrongFeedbackAudio = arrayOf(
                R.raw.mali_t, // For the first question (Kalamunggay)
                R.raw.mali_r,      // For the second question (Talong)
                R.raw.mali_b,       // For the third question (Gulay)
                R.raw.mali_l,    // For the fourth question (Ampalaya)
                R.raw.mali_k,    // For the fifth question (Kalabasa)
                R.raw.mali_d      // For the sixth question (Patatas)
            )
        ),
        2 to LevelData(
            questions = arrayOf(
                R.drawable.oval,
                R.drawable.square,
                R.drawable.pentagon,
                R.drawable.heksagono,
                R.drawable.oktagono,
                R.drawable.krus
            ),
            questionTexts = arrayOf(
                "Base sa hulagway nga gipakita sa taas ug sa video, ang I nagpasabot og unsa?",
                "Sa litrato ug video nga imong nakita, unsa ang kahulugan sa litra K?",
                "Unsa ang buot ipasabot sa litra P sa hulagway ug video?",
                "Tan-awa ang litrato ug video, unsa ang gipasabot sa litra H?",
                "Base sa video ug larawan, unsay kahulugan sa litra O?",
                "Sa imong nakita nga litrato ug video, unsay pasabot sa litra K?"
            ),
            choices = arrayOf(
                arrayOf("Tabanog", "Hulikab", "Initlog", "Arko"),
                arrayOf("Kuwadrado", "Udyong", "Diyamante", "Bituon"),
                arrayOf("Triyanggulo", "Rectanggulo", "Lingin", "Pentagono"),
                arrayOf("Kasingkasingon", "Heksagono", "Diyamante", "Krus"),
                arrayOf("Pentagono", "Heksagono", "Kuwadrado", "Oktagono"),
                arrayOf("Krus", "Udyong", "Hulikab", "Initlog")
            ),
            correctAnswers = arrayOf("Initlog", "Kuwadrado", "Pentagono", "Heksagono", "Oktagono", "Krus"),
            audioResources = arrayOf(
                R.raw.quest1_shape2,
                R.raw.quest2_shape2,
                R.raw.quest3_shape2,
                R.raw.quest4_shape2,
                R.raw.quest5_shape2,
                R.raw.quest6_shape2
            ),
            answerAudioResources = arrayOf(
                arrayOf(R.raw.tabanog, R.raw.hulikab, R.raw.initlog, R.raw.arko),
                arrayOf(R.raw.kuwadrado, R.raw.udyong, R.raw.diyamante, R.raw.bituon),
                arrayOf(R.raw.triyanggulo, R.raw.letter_o, R.raw.letter_f, R.raw.letter_e),
                arrayOf(R.raw.kasingkasing, R.raw.heksaguno, R.raw.diyamante, R.raw.krus),
                arrayOf(R.raw.pentagono, R.raw.heksaguno, R.raw.kuwadrado, R.raw.oktagono),
                arrayOf(R.raw.krus, R.raw.udyong, R.raw.hulikab, R.raw.initlog)
            ),
            correctFeedbackAudio = Array(6) { R.raw.tama }, // Same "tama.m4a" for all correct answers
            wrongFeedbackAudio = arrayOf(
                R.raw.mali_initlog,
                R.raw.mali_kuwadrado,
                R.raw.mali_pentagono,
                R.raw.mali_heksagono,
                R.raw.mali_oktagono,
                R.raw.mali_krus
            )
        ),
        3 to Level3TracingData(
            numberImages = arrayOf(
                R.drawable.diamon, // Fixed typo: diamon → diamond
                R.drawable.rectangle,
                R.drawable.oval,
                R.drawable.square,
                R.drawable.triangle1,
                R.drawable.triangle1
            ),
            questionTexts = arrayOf(
                "Can you trace the Shape Diamond?",
                "Can you trace the Shape Rectangle?",
                "Can you trace the Shape Oval?",
                "Can you trace the Shape Square?",
                "Can you trace the Shape Diamond?",
                "Can you trace the Shape Triangle?"
            ),
            numberPaths = arrayOf(
                Path().apply {
                    moveTo(400f, 250f)
                    lineTo(500f, 400f)
                    lineTo(400f, 550f)
                    lineTo(300f, 400f)
                    close()
                },
                Path().apply {
                    moveTo(300f, 300f)
                    lineTo(500f, 300f)
                    lineTo(500f, 500f)
                    lineTo(300f, 500f)
                    close()
                },
                Path().apply {
                    moveTo(400f, 250f)
                    quadTo(500f, 250f, 500f, 400f)
                    quadTo(500f, 550f, 400f, 550f)
                    quadTo(300f, 550f, 300f, 400f)
                    quadTo(300f, 250f, 400f, 250f)
                },
                Path().apply {
                    moveTo(300f, 300f)
                    lineTo(500f, 300f)
                    lineTo(500f, 500f)
                    lineTo(300f, 500f)
                    close()
                },
                Path().apply {
                    moveTo(400f, 250f)
                    lineTo(500f, 400f)
                    lineTo(400f, 550f)
                    lineTo(300f, 400f)
                    close()
                },
                Path().apply {
                    moveTo(400f, 300f)
                    lineTo(500f, 500f)
                    lineTo(300f, 500f)
                    close()
                }
            ),
            letterImages = arrayOf(),
            letterPaths = arrayOf()
        )
    )

    fun getLevelData(level: Int): LevelData? {
        return levelData[level] as? LevelData
    }

    fun getLevel3TracingData(): Level3TracingData? {
        return levelData[3] as? Level3TracingData
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

    data class Level3TracingData(
        val numberImages: Array<Int>,
        val questionTexts: Array<String>,
        val numberPaths: Array<Path>,
        val letterImages: Array<Int>,
        val letterPaths: Array<Path>
    )
}
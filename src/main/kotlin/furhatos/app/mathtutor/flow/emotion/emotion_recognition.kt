package furhatos.app.mathtutor.flow.emotion

import furhatos.app.mathtutor.*
import furhatos.records.User
import kotlin.math.pow
import kotlin.math.sqrt

fun getEmotionFromApi(user: User): Emotion {
    try {
        val response = khttp.get("http://127.0.0.1:5000/get_emotion").text
        val values = response.split(' ')
        user.lastValence = values[0].toDouble()
        user.lastArousal = values[1].toDouble()

        user.rollingValence = user.rollingValence.times(1 - FORGET_RATE)?.plus(values[0].toDouble() * FORGET_RATE)
        user.rollingArousal = user.rollingArousal.times(1 - FORGET_RATE)?.plus(values[1].toDouble() * FORGET_RATE)

        val emotion = matchEmotion(user.lastValence, user.lastArousal)
        println("Valence: ${values[0]}, arousal: ${values[1]}")
        println("User feels ${emotion.name}")
        return emotion
    } catch (e: NumberFormatException) {
        return NEUTRAL
    }
}

fun matchEmotion(valence: Double, arousal: Double): Emotion {
    var min = Double.POSITIVE_INFINITY
    var userEmotion: Emotion = NEUTRAL
    for (emotion: Emotion in listOf(FRUSTRATION, SATISFIED, EXCITED)) {
        val distance = sqrt((emotion.valence - valence).pow(2) + (emotion.arousal - arousal).pow(2))
        if (distance < min) {
            min = distance
            userEmotion = emotion
        }
    }
    return userEmotion
}

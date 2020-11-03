package furhatos.app.mathtutor.flow.emotion

import furhatos.app.mathtutor.*
import furhatos.records.User
import java.lang.NumberFormatException
import java.net.ConnectException

const val FORGET_RATE = 0.8

fun updateEmotionFromApi(user: User) {
    try {
        val response = khttp.get("http://127.0.0.1:5000/get_emotion").text
        val values = response.split(' ')
        user.lastValence = values[0].toDouble()
        user.lastArousal = values[1].toDouble()

        user.rollingValence = user.rollingValence.times(1 - FORGET_RATE).plus(values[0].toDouble() * FORGET_RATE)
        user.rollingArousal = user.rollingArousal.times(1 - FORGET_RATE).plus(values[1].toDouble() * FORGET_RATE)

//        println("Valence: ${values[0]}, arousal: ${values[1]}")
    } catch (e: ConnectException) {
        println("No connection to http://127.0.0.1:5000. Are you sure the emotion recognition API is running?")
    } catch (e: NumberFormatException) {
        println("Something is wrong with the API response format")
    }
}

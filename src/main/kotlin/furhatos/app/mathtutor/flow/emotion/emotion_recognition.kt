package furhatos.app.mathtutor.flow.emotion

import furhatos.app.mathtutor.arousal
import furhatos.app.mathtutor.valence
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

fun getEmotionFromApi() : State = state {
    onEntry {
        val response = khttp.get("http://127.0.0.1:5000/get_emotion").text
        val values = response.split(' ')
        users.current.valence = values[0].toDouble()
        users.current.arousal = values[1].toDouble()
        println("Valence: ${users.current.valence}, arousal: ${users.current.arousal}")
    }
}

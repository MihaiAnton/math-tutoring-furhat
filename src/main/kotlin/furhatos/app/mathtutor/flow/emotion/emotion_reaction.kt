package furhatos.app.mathtutor.flow.emotion


import furhatos.app.mathtutor.*
import furhatos.app.mathtutor.flow.useEmotion
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users
import furhatos.gestures.Gestures
import furhatos.records.User

fun reactToEmotion(): State = state {


    onTime(0, 1000) {
//        while (true) {
        if (useEmotion) {
            try {
                getEmotionFromApi(users.current)
            } catch (e: NumberFormatException) {

            }


            println("Reacting")

            val arousal = users.current.rollingArousal;
            val valence = users.current.rollingValence;

            if (arousal < 0 && valence < 0) {
                furhat.gesture(Gestures.ExpressSad(strength = 0.4), async = false)
            } else if (arousal > 0 && valence < 0) {
                furhat.gesture(Gestures.ExpressFear(strength = 0.4), async = false)
            } else if (arousal < 0 && valence > 0) {
                furhat.gesture(Gestures.Thoughtful(strength = 0.4), async = false)
            } else {
                furhat.gesture(Gestures.Smile(strength = 0.4), async = false)
            }
        }
    }
}


fun wrongResponseReaction(): State = state {
    onEntry {
        if (users.current.wrongConsecutiveResponse == 1) {
            furhat.gesture(Gestures.ExpressSad(strength = 0.2))
        } else if (users.current.wrongConsecutiveResponse == 2) {
            furhat.gesture(Gestures.ExpressSad(strength = 0.4))
        } else if (users.current.wrongConsecutiveResponse == 3) {
            furhat.gesture(Gestures.ExpressSad(strength = 0.6))
        } else if (users.current.wrongConsecutiveResponse > 3) {
            furhat.gesture(Gestures.ExpressSad(strength = 0.8))
        }
    }
}
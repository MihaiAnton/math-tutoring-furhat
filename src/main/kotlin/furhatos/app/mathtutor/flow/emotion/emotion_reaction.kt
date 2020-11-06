package furhatos.app.mathtutor.flow.emotion


import furhatos.app.mathtutor.*
import furhatos.app.mathtutor.flow.useEmotion
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users
import furhatos.gestures.Gestures

const val VALENCE_THRESHOLD = -0.2

val detectConfusion: State = state {
    onTime(0, 1000) {
        if (useEmotion) {
            updateEmotionFromApi(users.current)
            if (users.current.rollingValence < VALENCE_THRESHOLD) {
                send("ConfusionEvent")
                terminate()
            }
        }
    }
}

val mirrorEmotion: State = state {
    onTime(0, 1000) {
        if (useEmotion) {
            updateEmotionFromApi(users.current)
            if (users.current.rollingValence > 0) {
                furhat.gesture(Gestures.BigSmile(strength = users.current.rollingArousal * 2, duration = 2.0))
//                println("Smile ${users.current.rollingArousal * 2}")
            } else {
                furhat.gesture(Gestures.BrowFrown(strength = users.current.rollingArousal * 2, duration = 2.0))
//                println("Frown ${users.current.rollingArousal * 2}")
            }
        }
    }
}


fun wrongResponseReaction(): State = state {
    onEntry {
        when {
            users.current.wrongConsecutiveResponse == 0 -> {
                furhat.gesture(Gestures.Thoughtful(strength = 0.2))
                furhat.gesture(Gestures.Shake(strength = 0.2))
            }
            users.current.wrongConsecutiveResponse == 1 -> {
                furhat.gesture(Gestures.Thoughtful(strength = 0.4))
                furhat.gesture(Gestures.Shake(strength = 0.4))
            }
            users.current.wrongConsecutiveResponse == 2 -> {
                furhat.gesture(Gestures.Thoughtful(strength = 0.6))
                furhat.gesture(Gestures.Shake(strength = 0.6))
            }
            users.current.wrongConsecutiveResponse > 3 -> {
                furhat.gesture(Gestures.Thoughtful(strength = 0.8))
                furhat.gesture(Gestures.Shake(strength = 0.8))
            }
        }
    }
}
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
        if (useEmotion) {
            val userEmotion = getEmotionFromApi(users.current)
            when {
                userEmotion.name == "frustrated" -> furhat.gesture(Gestures.Thoughtful(strength = userEmotion.arousal, duration = 2.0), async = false)
                userEmotion.name == "satisfied" -> furhat.gesture(Gestures.Smile(strength = userEmotion.arousal, duration = 2.0), async = false)
                userEmotion.name == "excited" -> furhat.gesture(Gestures.BigSmile(strength = userEmotion.arousal, duration = 2.0), async = false)
            }
        }
    }
}


fun wrongResponseReaction(): State = state {
    onEntry {
        println("Wrong answer " + users.current.wrongConsecutiveResponse)
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
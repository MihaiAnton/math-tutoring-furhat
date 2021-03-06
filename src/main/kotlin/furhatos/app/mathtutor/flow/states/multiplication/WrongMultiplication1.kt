package furhatos.app.mathtutor.flow.states.multiplication


import furhatos.app.mathtutor.MULTIPLICATION
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.emotion.getGenericWrongResponse
import furhatos.app.mathtutor.flow.emotion.wrongResponseReaction
import furhatos.app.mathtutor.wrongConsecutiveResponse
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

val wrongMultiplication: State = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        parallel {
            goto(wrongResponseReaction())
        }
        furhat.say(getGenericWrongResponse(users.current.wrongConsecutiveResponse, MULTIPLICATION))
        delay(1000)
        terminate()
    }
}

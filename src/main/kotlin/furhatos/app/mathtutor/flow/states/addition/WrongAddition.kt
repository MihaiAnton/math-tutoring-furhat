package furhatos.app.mathtutor.flow.states.addition

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state

val WrongAddition = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Wrong Addition")
    }
}

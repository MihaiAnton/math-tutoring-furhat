package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val StartExplanation = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Start Explanation")
    }
}

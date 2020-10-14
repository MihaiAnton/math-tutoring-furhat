package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val InitialState = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Initial State")
    }
}

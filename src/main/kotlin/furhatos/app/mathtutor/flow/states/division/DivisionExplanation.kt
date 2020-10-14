package furhatos.app.mathtutor.flow.states.division;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val DivisionExplanation = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Division Explanation")
    }
}
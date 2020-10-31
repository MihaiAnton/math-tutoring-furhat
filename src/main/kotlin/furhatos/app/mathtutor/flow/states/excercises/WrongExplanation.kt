package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val WrongExplanation = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("I'm afraid that's not correct.... I'll give you another try!")
    }
}

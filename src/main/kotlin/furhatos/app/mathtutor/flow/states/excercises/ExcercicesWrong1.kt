package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val ExcercicesWrong1 = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Excercices Wrong 1")
    }
}

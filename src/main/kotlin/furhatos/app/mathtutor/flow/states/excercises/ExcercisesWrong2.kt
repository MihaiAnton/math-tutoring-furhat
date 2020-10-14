package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val ExcercisesWrong2 = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Excercises Wrong 2")
    }
}

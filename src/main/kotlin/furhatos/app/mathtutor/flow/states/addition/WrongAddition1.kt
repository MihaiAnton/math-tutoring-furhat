package furhatos.app.mathtutor.flow.states.addition;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.states.multiplication.MultiplicationIntro
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

fun WrongAddition1(x1: Int? = null): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Wrong Addition 1")
        delay(1000);
        goto(MultiplicationIntro(x1));
    }
}

package furhatos.app.mathtutor.flow.states.multiplication;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val MultiplicationFinal = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Multiplication Final")
    }
}

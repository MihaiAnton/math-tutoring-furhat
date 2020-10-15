package furhatos.app.mathtutor.flow.states.percentage;

import furhatos.app.mathtutor.DIVISION
import furhatos.app.mathtutor.MULTIPLICATION
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.states.excercises.StartExcercises
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val PercentageFinal = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Percentage Final")
        delay(1000)
        goto(StartExcercises(MULTIPLICATION))
    }
}

package furhatos.app.mathtutor.flow.states.division;

import furhatos.app.mathtutor.DIVISION
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.states.excercises.StartExercises
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val DivisionFinal = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Division Final")
        delay(1000)
        goto(StartExercises(DIVISION))
    }
}

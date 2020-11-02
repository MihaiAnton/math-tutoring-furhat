package furhatos.app.mathtutor.flow.states.multiplication

import furhatos.app.mathtutor.DIVISION
import furhatos.app.mathtutor.MULTIPLICATION
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.states.excercises.StartExercises
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.gestures.Gestures

val MultiplicationFinal = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Multiplication Final")
        } else {
            furhat.gesture(Gestures.Nod())
            furhat.say("Alright, well done! I think you are ready to try some exercises!")
        }
        delay(1000)
        goto(StartExercises(MULTIPLICATION))
    }
}

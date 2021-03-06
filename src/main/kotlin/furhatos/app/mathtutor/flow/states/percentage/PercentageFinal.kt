package furhatos.app.mathtutor.flow.states.percentage;

import furhatos.app.mathtutor.DIVISION
import furhatos.app.mathtutor.MULTIPLICATION
import furhatos.app.mathtutor.PERCENTAGE
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.states.excercises.StartExercises
import furhatos.app.mathtutor.name
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users
import furhatos.gestures.Gestures

val PercentageFinal = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Percentage Final")
        } else {
            furhat.gesture(Gestures.Nod(strength=0.4))
            furhat.say("Very well done ${users.current.name}. I think you are ready to try some exercises!")
        }
        delay(1000)
        goto(StartExercises(PERCENTAGE))
    }
}

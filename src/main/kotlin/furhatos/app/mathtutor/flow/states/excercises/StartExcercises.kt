package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.*
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.nlu.MathMethod
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

fun StartExercises(subject: String?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Start Exercises")
        delay(1000)

        resetUserExerciseData(users.current)

        goto(Exercise(subject))
    }
}

package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.*
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

fun ExercisesWrong1(subject: String?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }

        if (debugMode()) {
            furhat.say("Exercises Wrong 1")
        } else {
            furhat.say("You now have the chance to correct the answers you had wrong.")
            furhat.say(randomHint(subject.toString()))
        }



        delay(1000)


        goto(Exercise(subject, 0, true))
    }
}

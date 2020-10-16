package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.correctAnswers
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.nlu.MathMethod
import furhatos.app.mathtutor.wrongAnswers
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

fun StartExcercises(subject: String?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Start Excercises")
        delay(1000)

        users.current.correctAnswers = 0;
        users.current.wrongAnswers = 0;

        goto(Excercise(subject))
    }
}

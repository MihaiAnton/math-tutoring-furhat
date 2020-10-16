package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.correctAnswers
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.wrongAnswers
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

val ExcerciseEvaluation = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Excercise Evaluation - correct ${users.current.correctAnswers}, wrong ${users.current.wrongAnswers}")
    }
}

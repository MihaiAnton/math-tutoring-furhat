package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val ExcerciseEvaluation = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Excercise Evaluation")
    }
}

package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.states.excercises.StartExcercises
import furhatos.app.mathtutor.nlu.DifferentCalculationIntent
import furhatos.app.mathtutor.nlu.MoreExercisesIntent
import furhatos.app.mathtutor.nlu.StopSessionIntent
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state

fun AllAnswersCorrect(subject: String?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("All Answers Correct")
        furhat.listen()
    }

    onResponse<StopSessionIntent> {
        goto(FinalState)
    }

    onResponse<MoreExercisesIntent> {
        goto(StartExcercises(subject))
    }

    onResponse<DifferentCalculationIntent> {
        goto(OptionsSelection)
    }
}

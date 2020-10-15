package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.nlu.ExercisesIntent
import furhatos.app.mathtutor.nlu.ExplanationIntent
import furhatos.app.mathtutor.nlu.MathMethod
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state


fun StartTutorial(subject: MathMethod?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Start Tutorial on $subject")
        furhat.listen()
    }

    onResponse<ExercisesIntent> {
        goto(VerifyKnowledge(subject))
    }

    onResponse<ExplanationIntent> {
        goto(StartExplanation(subject))
    }
}



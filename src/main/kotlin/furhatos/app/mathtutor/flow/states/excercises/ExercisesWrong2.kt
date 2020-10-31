package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.*
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.states.StartExplanation
import furhatos.app.mathtutor.flow.states.UnwillingUserIntro
import furhatos.app.mathtutor.flow.states.VerifyKnowledge
import furhatos.app.mathtutor.nlu.ExplanationIntent
import furhatos.app.mathtutor.nlu.IKnowItResponse
import furhatos.app.mathtutor.nlu.MathMethod
import furhatos.flow.kotlin.*

fun ExercisesWrong2(subject: String?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("There were still some mistakes in your answers, are you sure you understand $subject correctly or would you like some explanation?")
        resetUserExerciseData(users.current)
        furhat.listen()
    }

    onResponse<IKnowItResponse> {
        goto(VerifyKnowledge(subject))
    }

    onResponse<ExplanationIntent> {
        goto(StartExplanation(subject))
    }

    onResponse {
        goto(UnwillingUserIntro)
    }
}

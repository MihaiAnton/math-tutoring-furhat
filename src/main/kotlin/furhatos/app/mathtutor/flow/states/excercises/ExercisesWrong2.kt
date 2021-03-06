package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.*
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.flow.emotion.detectConfusion
import furhatos.app.mathtutor.flow.emotion.wrongResponseReaction
import furhatos.app.mathtutor.flow.states.StartExplanation
import furhatos.app.mathtutor.flow.states.UnwillingUserIntro
import furhatos.app.mathtutor.flow.states.VerifyKnowledge
import furhatos.app.mathtutor.nlu.ExplanationIntent
import furhatos.app.mathtutor.nlu.IKnowItResponse
import furhatos.app.mathtutor.nlu.UnwillingIntent
import furhatos.flow.kotlin.*

fun exercisesWrong2(subject: String?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        parallel {
            goto(wrongResponseReaction())
        }
        furhat.say("There were still some mistakes in your answers, are you sure you understand $subject correctly or would you like some explanation?")
        resetUserExerciseData(users.current)
        parallel {
            goto(detectConfusion)
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 20000)
    }

    onResponse<IKnowItResponse> {
        goto(VerifyKnowledge(subject))
    }

    onResponse<ExplanationIntent> {
        goto(StartExplanation(subject))
    }

    onResponse<UnwillingIntent> {
        goto(UnwillingUserIntro)
    }

    onResponse {
        furhat.say(getUncaughtResponseText())
        furhat.listen(timeout = 10000)
    }
}

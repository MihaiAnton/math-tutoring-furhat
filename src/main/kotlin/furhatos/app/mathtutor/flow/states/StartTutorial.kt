package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.nlu.ExercisesIntent
import furhatos.app.mathtutor.nlu.ExplanationIntent
import furhatos.app.mathtutor.nlu.MathMethod
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state


fun StartTutorial(subject: String?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Start Tutorial on $subject")
        } else {
            furhat.say("Very well, let's begin. Do you want to hear the explanation of $subject, or get right to " +
                    "the exercises?")
        }
        furhat.listen(timeout = 6000)
    }

    onReentry {
        furhat.listen(timeout = 4000)
    }

    onResponse<ExercisesIntent> {
        goto(VerifyKnowledge(subject))
    }

    onResponse<ExplanationIntent> {
        goto(StartExplanation(subject))
    }

    onResponse {
        furhat.say(getUncaughtResponseText())
        reentry()
    }
}



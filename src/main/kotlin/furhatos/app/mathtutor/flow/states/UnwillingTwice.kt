package furhatos.app.mathtutor.flow.states

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.mirrorEmotion
import furhatos.app.mathtutor.nlu.ExercisesIntent
import furhatos.app.mathtutor.nlu.UnwillingIntent
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onNoResponse
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.Yes

val UnwillingTwice = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        parallel {
            goto(mirrorEmotion)
        }
        if (debugMode()) {
            furhat.say("Unwilling twice")
        } else {
            furhat.say("I won't stay in the way if you really don't want. It is your benefit after all. Are you sure you don't want to learn?")
            furhat.listen(10000)
        }
    }

    onResponse<ExercisesIntent> {
        goto(WhatsYourName)
    }

    onResponse<UnwillingIntent> {
        goto(FinalState(true))
    }

    onResponse<Yes> {
        goto(FinalState(true))
    }

    onNoResponse{
        goto(FinalState(true))
    }
}

package furhatos.app.mathtutor.flow.states

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.mirrorEmotion
import furhatos.app.mathtutor.nlu.UnwillingIntent
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onNoResponse
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes

val UnwillingUserCheerUp = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        parallel {
            goto(mirrorEmotion)
        }
        if (debugMode()) {
            furhat.say("Unwilling user cheer up")
        } else {
            // TODO maybe some randomness here, since we can get in this state quite often
            furhat.say("I understand, I can relate sometimes. But don't forget that we are in this together! With the two of us we can make math a lot of fun, you know. Give it a go and join me this session. Are you in?")
            furhat.listen(timeout = 10000)
        }
    }

    onResponse<No> {
        goto(UnwillingTwice)
    }

    onResponse<Yes> {
        goto(WhatsYourName)
    }

    onResponse<UnwillingIntent> {
        goto(UnwillingTwice)
    }

    onNoResponse {
        furhat.say("Let's give it a try!")
        goto(WhatsYourName)
    }
}

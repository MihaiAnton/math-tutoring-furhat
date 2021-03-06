package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.nlu.MoreIntent
import furhatos.app.mathtutor.nlu.StartIntent
import furhatos.app.mathtutor.nlu.UnwillingIntent
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

val OptionsIntro = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Options Intro")
        } else {
            furhat.say("Alright. We offer tutoring for multiplication, division and percentage calculations. Are " +
                    "you ready to make a choice between these and start the session, or do you want more information?")
        }

        furhat.glance(users.current)
        furhat.listen(timeout = 20000)
    }

    onReentry {
        furhat.listen(timeout = 10000)
    }

    onResponse<StartIntent> {
        goto(WhatsYourName)
    }

    onResponse<MoreIntent> {
        goto(MethodExplanation)
    }

    onResponse<UnwillingIntent> {
        goto(UnwillingUserIntro)
    }

    onResponse {
        furhat.say(getUncaughtResponseText())
        reentry()
    }
}

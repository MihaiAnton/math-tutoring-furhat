package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.flow.emotion.reactToEmotion
import furhatos.app.mathtutor.nlu.MoreIntent
import furhatos.app.mathtutor.nlu.StartIntent
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
        parallel {
            goto(reactToEmotion())
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 6000)
    }

    onReentry {
        furhat.listen(timeout = 4000)
    }

    onResponse<StartIntent> {
//        goto(OptionsSelection)
        goto(WhatsYourName)
    }

    onResponse<MoreIntent> {
        goto(MethodExplanation)
    }

    onResponse {
        furhat.say(getUncaughtResponseText())
        reentry()
    }
}

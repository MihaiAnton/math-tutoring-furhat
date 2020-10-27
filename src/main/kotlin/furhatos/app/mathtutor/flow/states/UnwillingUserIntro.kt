package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.debugMode
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val UnwillingUserIntro = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Unwilling User Intro")
        } else {
            furhat.say("I'm sorry to hear that you are not excited. Why do you not want to learn more math skills?")
        }
    }
}

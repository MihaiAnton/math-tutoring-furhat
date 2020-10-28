package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.debugMode
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onNoResponse
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state

val UnwillingUserIntro = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Unwilling User Intro")
        } else {
            random(
                    {furhat.say("I'm sorry to hear that you are not excited. Why do you not want to learn more " +
                            "math skills?")},
                    {furhat.say("Such a shame that you are not excited to learn math! What is wrong?")},
                    {furhat.say("It makes me sad that you are not as crazy about math as I am. Why are you " +
                            "not excited?")},
                    {furhat.say("What's wrong then? Why are you not as excited for this tutoring session as I am?")},
                    {furhat.say("I find it sad that you don't seem to want to join my lesson. What is going on?")}
            )
        }
        furhat.listen(timeout = 15000)
    }

    onReentry {
        furhat.listen(timeout = 10000)
    }

    onResponse {
        // TODO: Figure out how to motivate student
        random(
                {furhat.say("")}
        )
    }

    onNoResponse {
        random(
                {furhat.say("Go on, tell me, I want to help you.")},
                {furhat.say("Don't be shy, I just want to help you!")}
        )
        reentry()
    }
}

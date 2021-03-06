package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.mirrorEmotion
import furhatos.app.mathtutor.nlu.Unsure
import furhatos.flow.kotlin.*

val UnwillingUserIntro = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        parallel {
            goto(mirrorEmotion)
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
                    {furhat.say("I find it a shame that you don't seem to want to join my lesson. What is going " +
                            "on?")}
            )
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 10000)
    }

    onReentry {
        furhat.listen(timeout = 5000)
    }

    onResponse<Unsure> {
        random(
                {furhat.say("Go on, tell me, I want to help you.")},
                {furhat.say("Don't be shy, I just want to help you!")},
                {furhat.say("Don't worry, we're just having a little conversation. Tell me what's up.")},
                {furhat.say("Tell me, perhaps I can help, you know!")},
                {furhat.say("Come on, let it out. You will feel better when you tell me what's wrong.")}
        )
        reentry()
    }

    onResponse {
        goto(UnwillingUserCheerUp)
    }

    onNoResponse {
        random(
                {furhat.say("Go on, tell me, I want to help you.")},
                {furhat.say("Don't be shy, I just want to help you!")},
                {furhat.say("Don't worry, we're just having a little conversation. Tell me what's up.")},
                {furhat.say("Tell me, perhaps I can help, you know!")},
                {furhat.say("Come on, let it out. You will feel better when you tell me what's wrong.")}
        )
        reentry()
    }
}

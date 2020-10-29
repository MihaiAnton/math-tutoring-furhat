package furhatos.app.mathtutor.flow.states

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes

val UnwillingUserCheerUp : State = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }

        if (debugMode()) {
            furhat.say("Cheer up unwilling user")
        } else {
            random(
                    {furhat.say("It's okay, I sometimes feel the same way. But sometimes things we don't want " +
                            "to do end up being really fun! Take a chance and let me make math fun for you! Are you in?")},
                    {furhat.say("I understand, I can relate sometimes. But don't forget that we are in this together!" +
                            " With the two of us we can make math a lot of fun, you know. Give it a go and join me this " +
                            "session. Are you in?")},
                    {furhat.say("I totally get it, sometimes it's tough. But with a bit of training you can do " +
                            "great things! I know you have it in you to go to the next level! Are you with me?")},
                    {furhat.say("I see what you mean. I will definitely try to take your feelings into account. " +
                            "But even when you feel like this, you can have a good time with me and math! I am confident " +
                            "that you can pull that off! Are you in?")},
                    {furhat.say("Yeah, I think I get it. I still think we can have fun together doing some math. " +
                            "You can make a lot of progress in just one tutorial! You might even feel better after! " +
                            "Are you in?")}
            )
        }
        furhat.listen(timeout = 10000)
    }

    onReentry {
        furhat.say("So, are you in?")
        furhat.listen(timeout = 6000)
    }

    onResponse<Yes> {
        goto(OptionsSelection)
    }

    onResponse<No> {
        goto(UnwillingTwice)
    }

    onNoResponse {
        random(
                {furhat.say("I really want you to continue with me. Please tell me if you want to continue as " +
                        "well. Otherwise I am forced to quit this session.")},
                {furhat.say("Please tell me if you still want to continue. I would really like it if we could " +
                        "finish this session together!")},
                {furhat.say("Think about what you can accomplish in only a short tutorial session! Please tell " +
                        "me you want to continue together!")},
                {furhat.say("It's gonna be a lot of fun and you're going to improve a lot, I can guarantee that!")}
        )
        delay(200)
        reentry()
    }

    onResponse {
        furhat.say(getUncaughtResponseText())
        delay(200)
        reentry()
    }
}
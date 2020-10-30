package furhatos.app.mathtutor.flow.states

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.nlu.DoContinue
import furhatos.app.mathtutor.nlu.StopSessionIntent
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.No

val UnwillingTwice = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("User unwilling twice")
        } else {
            random(
                    {furhat.say("That's really too bad. I will have to abort the session if you're being like " +
                            "this. This is your last chance to continue and try to make the best of it! So are you " +
                            "sure you want to quit or shall we continue?")},
                    {furhat.say("I'm really sorry to hear that. I'm sad to say that the session can't continue " +
                            "if you're acting like this. I will give you one more chance to continue the session and " +
                            "not make me stop. So will you take this last chance and continue with me?")},
                    {furhat.say("It's such a shame that you say that. I really thought we could make something " +
                            "of this together. Perhaps you want to take this last chance to still continue with me?")},
                    {furhat.say("I think you might be wasting a very good opportunity to have fun and learn at " +
                            "the same time. I'm giving you one last chance to still join me this session. Are you " +
                            "coming with me?")}
            )
        }
        furhat.listen(timeout=10000)
    }

    onReentry {
        if (reentryCount > 2) {
            goto(FinalState(completed=false))
        } else {
            furhat.listen(timeout=10000)
        }
    }

    onResponse<DoContinue> {
        goto(OptionsSelection)
    }

    onResponse<StopSessionIntent> {
        goto(FinalState(completed=false))
    }

    onResponse<No> {
        goto(FinalState(completed=false))
    }

    onResponse {
        furhat.say(getUncaughtResponseText())
        reentry()
    }
}
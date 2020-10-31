package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getEmotionFromApi
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.flow.emotion.reactToEmotion
import furhatos.app.mathtutor.nlu.OptionsIntent
import furhatos.app.mathtutor.nlu.StartIntent
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes

val InitialState = state {
    onEntry {
        // Change the default thresholds:


//        getEmotionFromApi(users.current)
        parallel {
            goto(CustomGaze)
        }



        if (debugMode()) {
            furhat.say("Initial State")
        } else {
            random(
                    {furhat.say("Hello, welcome to this math tutoring session. Do you want to start right away or" +
                            " find out about the available options?")},
                    {furhat.say("Hi there, welcome to this math lesson. Do you want to start practicing right " +
                            "away, or do you want to hear about the options first?")},
                    {furhat.say("Hello and welcome to today's lesson! Do you want to start the tutoring session " +
                            "right away, or would you rather hear about the available options first?")},
                    {furhat.say("Hello there, nice of you to join today's session! Do you want to start with the " +
                            "tutoring, or rather hear about all the options?")}
            )
        }

        parallel {
            goto(reactToEmotion())
        }
        furhat.listen(timeout = 20000);
    }

    onReentry {
        furhat.listen(timeout = 10000)
    }

    onResponse<Yes> {
        goto(OptionsSelection)
    }

    onResponse<No> {
        goto(OptionsIntro)
    }

    onResponse<StartIntent> {
        goto(OptionsSelection)
    }

    onResponse<OptionsIntent> {
        goto(OptionsIntro)
    }

    onResponse { // Catches everything else
        furhat.say(getUncaughtResponseText())
        reentry()
    }

    onTime(delay = 40000) {
        goto(UnwillingUserIntro)
    }
}

package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.emotion.getEmotionFromApi
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


        getEmotionFromApi(users.current)
        parallel {
            goto(CustomGaze)
        }



        furhat.say("Initial State")
        parallel {
            goto(reactToEmotion())
        }
        furhat.listen(timeout = 20000);
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
        // TODO more variations and the same in every state where we sk a question
        furhat.say("Can you please repeat?")
    }

    onTime(delay = 40000) {
        goto(UnwillingUserIntro)
    }
}

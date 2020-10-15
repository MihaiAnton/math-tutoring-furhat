package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.nlu.OptionsIntent
import furhatos.app.mathtutor.nlu.StartIntent
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes

val InitialState = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Initial State")
        furhat.listen();
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

    onTime(delay = 10000) {
        goto(UnwillingUserIntro)
    }
}

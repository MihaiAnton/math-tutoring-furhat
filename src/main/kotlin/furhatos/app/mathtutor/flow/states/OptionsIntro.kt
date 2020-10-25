package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.nlu.MoreIntent
import furhatos.app.mathtutor.nlu.StartIntent
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state

val OptionsIntro = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Options Intro")
        furhat.listen()
    }

    onResponse<StartIntent> {
        goto(OptionsSelection)
    }

    onResponse<MoreIntent> {
        goto(MethodExplanation)
    }
}

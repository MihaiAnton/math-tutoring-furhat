package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val VerifyKnowledge = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Verify Knowledge")
    }
}

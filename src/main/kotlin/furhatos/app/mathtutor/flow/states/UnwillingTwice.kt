package furhatos.app.mathtutor.flow.states

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.debugMode
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val UnwillingTwice = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("User unwilling twice")
        } else {
            random(
                    {furhat.say("")}
            )
        }
        furhat.listen(timeout = 10000)
    }
}
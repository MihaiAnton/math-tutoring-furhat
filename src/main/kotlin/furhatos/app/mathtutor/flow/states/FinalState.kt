package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.debugMode
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val FinalState = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Final State")
        } else {
            random(
                    {furhat.say("Great session! Feel free to continue or restart your tutoring at any time!")}
            )
        }
    }
}

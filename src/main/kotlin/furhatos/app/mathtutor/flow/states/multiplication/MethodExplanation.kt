package furhatos.app.mathtutor.flow.states.multiplication;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val MethodExplanation = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Method Explanation")
    }
}

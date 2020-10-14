package furhatos.app.mathtutor.flow.states.percentage;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val PercentagesExplanation = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Percentages Explanation")
    }
}

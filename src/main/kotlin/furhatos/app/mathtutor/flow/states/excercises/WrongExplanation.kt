package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.emotion.wrongResponseReaction
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val WrongExplanation = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        parallel {
            goto(wrongResponseReaction())
        }
        furhat.say("I'm afraid that's not correct.... I'll give you another try!")
    }
}

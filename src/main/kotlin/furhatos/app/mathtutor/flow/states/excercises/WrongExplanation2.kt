package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.states.StartExplanation
import furhatos.app.mathtutor.nlu.MathMethod
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

fun WrongExplanation2(subject: MathMethod?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Wrong Explanation 2")
        delay(1000)
        goto(StartExplanation(subject));
    }
}

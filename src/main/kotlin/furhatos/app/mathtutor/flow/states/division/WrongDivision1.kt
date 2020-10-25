package furhatos.app.mathtutor.flow.states.division;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

fun WrongDivision1(total: Int? = null, perDay: Int? = null): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Wrong Division 1")
        delay(1000);
        goto(DivisionExplanation(total, perDay));
    }
}

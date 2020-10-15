package furhatos.app.mathtutor.flow.states.division;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.nlu.DivisionExpressionResponse
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state

fun DivisionPractice1(total: Int? = null, perDay: Int? = null): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Division Practice 1")
    }

    onResponse<DivisionExpressionResponse> {
        val _total = it.intent.value.value;
        val _times = it.intent.times.value;

        if (total == _total && perDay == _times) {
            goto(DivisionPractice2());
        } else {
            goto(WrongDivisionCalculation(total, perDay))
        }
    }

}

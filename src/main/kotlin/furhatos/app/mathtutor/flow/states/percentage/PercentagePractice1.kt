package furhatos.app.mathtutor.flow.states.multiplication;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.states.percentage.WrongPercentage1
import furhatos.app.mathtutor.flow.states.percentage.WrongPercentage2
import furhatos.app.mathtutor.nlu.MultiplicationResponse
import furhatos.app.mathtutor.nlu.PercentageResponse
import furhatos.app.mathtutor.nlu.PercentageResponse2
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state

fun PercentagePractice1(total: Int? = null, share: Int? = null): State = state(Interaction) {

    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Percentage Practice 1")
        furhat.listen();
    }

    onResponse<PercentageResponse2> {
        val fraction = it.intent.fraction.value;

        if (fraction == 50) {
            goto(PercentagePractice2())
        } else {
            goto(WrongPercentage2)
        }
    }

}

package furhatos.app.mathtutor.flow.states.percentage;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.states.division.DivisionExplanation
import furhatos.app.mathtutor.flow.states.division.DivisionPractice1
import furhatos.app.mathtutor.flow.states.multiplication.PercentagePractice1
import furhatos.app.mathtutor.nlu.PercentageResponse
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import kotlin.random.Random

fun PercentagesExplanation(total: Int? = null, share: Int? = null): State = state(Interaction) {

    val newTotal = 100;
    val newShare = 5 * Random.nextInt(1, 19);


    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Percentages Explanation")
    }

    onResponse<PercentageResponse> {
        val _totalResponse = it.intent.total.value;
        val _shareResponse = it.intent.total.value;

        if (_totalResponse == newTotal && _shareResponse == newShare) {
            goto(PercentagePractice1())
        } else {
            goto(WrongPercentage1(newTotal, newShare))
        }
    }
}

package furhatos.app.mathtutor.flow.states.division;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.nlu.DivisionResponse
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import kotlin.random.Random

fun DivisionExplanation(total: Int? = null, perDay: Int? = null): State = state(Interaction) {

    val newPerDay = Random.nextInt(1, 10);
    val newTotal = newPerDay * Random.nextInt(2, 7);

    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Division Explanation")
    }

    onResponse<DivisionResponse> {
        val days = it.intent.days.value;
        if (days == newTotal / newPerDay) {
            goto(DivisionPractice1(newPerDay, newTotal))
        } else {
            goto(WrongDivision1(newTotal, newPerDay))
        }
    }

}

package furhatos.app.mathtutor.flow.states.division;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.nlu.DivisionExpressionResponse
import furhatos.app.mathtutor.nlu.DivisionResponse
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import kotlin.random.Random

fun DivisionPractice2(): State = state(Interaction) {

    val newPerDay = Random.nextInt(1, 10);
    val newTotal = newPerDay * Random.nextInt(2, 7);

    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Division Practice 2")
    }

    onResponse<DivisionResponse> {
        val response = it.intent.days.value;

        if(response == newTotal / newPerDay){
            goto(DivisionFinal)
        }
        else{
            goto(WrongDivision2(newTotal, newPerDay))
        }
    }

}

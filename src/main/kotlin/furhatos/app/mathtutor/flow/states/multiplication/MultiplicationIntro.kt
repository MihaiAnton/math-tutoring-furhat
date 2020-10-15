package furhatos.app.mathtutor.flow.states.multiplication;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.states.addition.WrongAddition1
import furhatos.app.mathtutor.nlu.AdditionResponse
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import java.lang.Integer.getInteger
import furhatos.nlu.common.Number
import kotlin.math.absoluteValue
import kotlin.random.Random

fun MultiplicationIntro(x1: Int? = null): State = state(Interaction) {

    var _x1 : Int;

    if(x1 == null) {
        _x1 = Random.nextInt(2, 12)
    }
    else{
        _x1 = x1;
    }

    onEntry {
        parallel {
            goto(CustomGaze)
        }

        furhat.say("Multiplication Intro, $_x1 and $_x1")
        furhat.listen()
    }

    onResponse<AdditionResponse> {
        val sum = it.intent.sum.value;

        if (_x1 + _x1 != sum) {
            delay(1000)
            goto(WrongAddition1(_x1))
        }
        else{
            goto(MultiplicationExample(_x1))
        }
    }
}

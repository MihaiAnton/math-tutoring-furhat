package furhatos.app.mathtutor.flow.states.multiplication;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.states.addition.WrongAddition1
import furhatos.app.mathtutor.flow.states.addition.WrongAddition2
import furhatos.app.mathtutor.nlu.AdditionResponse
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state

fun MultiplicationExample(x: Int): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Multiplication Example with sum $x")
        furhat.listen()
    }

    onResponse<AdditionResponse> {
        val sum = it.intent.sum.value;

        if (3 * x != sum) {
            delay(1000)
            goto(WrongAddition2(x))
        }
        else{
            goto(MultiplicationPractice1(3, x))
        }
    }
}

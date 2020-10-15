package furhatos.app.mathtutor.flow.states.multiplication


import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.nlu.AdditionResponse
import furhatos.app.mathtutor.nlu.MultiplicationResponse
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state

fun MultiplicationPractice2(x: Int): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Multiplication Practice 2")
        furhat.listen();
    }

    onResponse<AdditionResponse> {
        if(it.intent.sum.value == x*5){
            goto(MultiplicationFinal)
        }
        else{
            goto(WrongMultiplication2(x));
        }
    }
}

package furhatos.app.mathtutor.flow.states.multiplication


import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.states.multiplication.MultiplicationExample
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

fun WrongMultiplication2(x: Int): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Wrong Multiplication 2")
        delay(1000)
        goto(MultiplicationPractice2(x));
    }
}

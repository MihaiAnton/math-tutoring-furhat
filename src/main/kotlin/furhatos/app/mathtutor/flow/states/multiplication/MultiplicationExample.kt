package furhatos.app.mathtutor.flow.states.multiplication;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.states.addition.WrongAddition1
import furhatos.app.mathtutor.flow.states.addition.WrongAddition2
import furhatos.app.mathtutor.nlu.AdditionResponse
import furhatos.app.mathtutor.resetWrongAnswers
import furhatos.app.mathtutor.wrongAnswer
import furhatos.flow.kotlin.*

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
            wrongAnswer(users.current)
            goto(WrongAddition2(x))
        }
        else{
            resetWrongAnswers(users.current)
            goto(MultiplicationPractice1(3, x))
        }
    }
}

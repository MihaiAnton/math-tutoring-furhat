package furhatos.app.mathtutor.flow.states.multiplication


import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.nlu.AdditionResponse
import furhatos.app.mathtutor.nlu.MultiplicationResponse
import furhatos.app.mathtutor.resetWrongAnswers
import furhatos.app.mathtutor.wrongAnswer
import furhatos.flow.kotlin.*

fun MultiplicationPractice2(x: Int): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Multiplication Practice 2")
        furhat.listen();
    }

    onResponse<AdditionResponse> {
        if (it.intent.sum.value == x * 5) {
            resetWrongAnswers(users.current)
            goto(MultiplicationFinal)
        } else {
            wrongAnswer(users.current)
            goto(WrongMultiplication2(x));
        }
    }
}

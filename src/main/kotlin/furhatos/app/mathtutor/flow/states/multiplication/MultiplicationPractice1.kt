package furhatos.app.mathtutor.flow.states.multiplication;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.nlu.MultiplicationResponse
import furhatos.app.mathtutor.resetWrongAnswers
import furhatos.app.mathtutor.wrongAnswer
import furhatos.flow.kotlin.*

fun MultiplicationPractice1(times: Int, value: Int): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Multiplication Practice 1")
        furhat.listen();
    }

    onResponse<MultiplicationResponse> {
        val x1 = it.intent.times;
        val x2 = it.intent.value;

        if (x1.value == times && x2.value == value || x1.value == value && x2.value == times) {
            resetWrongAnswers(users.current)
            goto(MultiplicationPractice2(value))
        } else {
            wrongAnswer(users.current)
            goto(WrongMultiplication1(times, value))
        }
    }

}

package furhatos.app.mathtutor.flow.states.multiplication;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
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
        if (debugMode()) {
            furhat.say("Multiplication Example with sum $x")
        } else {
            furhat.say("That's right. We can say that together we have $x plus $x apples. In this case, because " +
                    "we both have the same number $x of apples, we can also formulate our combined number " +
                    "differently, using multiplication terms. We say that  we have two times $x apples. Now, how " +
                    "many apples do we have if Alice, who has $x apples as well, joins our party?")
        }
        furhat.listen(timeout = 20000)
    }

    onReentry {
        furhat.listen(timeout = 10000)
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

    onResponse {
        furhat.say(getUncaughtResponseText())
        reentry()
    }
}

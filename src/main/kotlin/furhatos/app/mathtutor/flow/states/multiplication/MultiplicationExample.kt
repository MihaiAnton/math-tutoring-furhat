package furhatos.app.mathtutor.flow.states.multiplication;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.flow.emotion.detectConfusion
import furhatos.app.mathtutor.nlu.AdditionResponse
import furhatos.app.mathtutor.nlu.RepeatQuestionIntent
import furhatos.app.mathtutor.resetWrongAnswers
import furhatos.app.mathtutor.wrongAnswer
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures

fun MultiplicationExample(x: Int): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Multiplication Example with sum $x")
        } else {
            furhat.gesture(Gestures.Nod(strength=0.4))
            furhat.say("That's right. We can say that together we have $x plus $x apples. In this case, because " +
                    "we both have the same number, $x, of apples, we can also formulate our combined number " +
                    "differently, using multiplication terms. We say that we have two times $x apples. " +
                    "${furhat.voice.pause("1000ms")} Now, how " +
                    "many apples do we have if Alice, who has $x apples as well, joins our party?")
        }
        parallel {
            goto(detectConfusion)
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 20000)
    }

    onResponse<AdditionResponse> {
        val sum = it.intent.sum.value;

        if (3 * x != sum) {
            delay(1000)
            wrongAnswer(users.current)
            call(wrongMultiplication)
            reentry()
        }
        else{
            resetWrongAnswers(users.current)
            goto(MultiplicationPractice1(3, x))
        }
    }

    onEvent("ConfusionEvent") {
        furhat.say("It's okay to be confused. This was a tough question. Let me repeat it for you.")
        reentry()
    }

    onResponse<RepeatQuestionIntent> {
        furhat.say("I'll repeat the question.")
        reentry()
    }

    onResponse {
        furhat.say(getUncaughtResponseText())
        furhat.listen(timeout = 10000)
    }
}

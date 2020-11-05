package furhatos.app.mathtutor.flow.states.multiplication;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.detectConfusion
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.nlu.AdditionResponse
import furhatos.app.mathtutor.nlu.RepeatQuestionIntent
import furhatos.app.mathtutor.resetWrongAnswers
import furhatos.app.mathtutor.wrongAnswer
import furhatos.flow.kotlin.*
import kotlin.random.Random

fun MultiplicationIntro(x1: Int? = null): State = state(Interaction) {

    var _x1: Int;

    if (x1 == null) {
        _x1 = Random.nextInt(2, 12)
    } else {
        _x1 = x1;
    }

    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Multiplication Intro, $_x1 and $_x1")
        } else {
            furhat.say("Imagine I have $_x1 apples, ${furhat.voice.pause("500ms")} and you have $_x1 apples as well. ${furhat.voice.pause("500ms")} How many apples do we " +
                    "have together?")
        }
        parallel {
            goto(detectConfusion)
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 15000)
    }

    onResponse<AdditionResponse> {
        val sum = it.intent.sum.value;

        if (_x1 + _x1 != sum) {
            delay(1000)
            wrongAnswer(users.current)
            call(wrongMultiplication)
            reentry()
        } else {
            resetWrongAnswers(users.current)
            goto(MultiplicationExample(_x1))
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
        furhat.listen(timeout = 8000)
    }
}

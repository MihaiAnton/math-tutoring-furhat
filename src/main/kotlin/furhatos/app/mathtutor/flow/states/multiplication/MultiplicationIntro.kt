package furhatos.app.mathtutor.flow.states.multiplication;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.flow.emotion.reactToEmotion
import furhatos.app.mathtutor.flow.states.addition.WrongAddition1
import furhatos.app.mathtutor.nlu.AdditionResponse
import furhatos.app.mathtutor.resetWrongAnswers
import furhatos.app.mathtutor.wrongAnswer
import furhatos.app.mathtutor.wrongConsecutiveResponse
import furhatos.flow.kotlin.*
import java.lang.Integer.getInteger
import furhatos.nlu.common.Number
import kotlin.math.absoluteValue
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
            goto(reactToEmotion())
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 15000)
    }

    onReentry {
        parallel {
            goto(reactToEmotion())
        }
        furhat.listen(timeout = 8000)
    }

    onResponse<AdditionResponse> {
        val sum = it.intent.sum.value;

        if (_x1 + _x1 != sum) {
            delay(1000)
            wrongAnswer(users.current)
            goto(WrongAddition1(_x1))
        } else {
            resetWrongAnswers(users.current)
            goto(MultiplicationExample(_x1))
        }
    }

    onResponse {
        furhat.say(getUncaughtResponseText())
        reentry()
    }
}

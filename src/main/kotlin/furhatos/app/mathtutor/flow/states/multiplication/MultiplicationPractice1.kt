package furhatos.app.mathtutor.flow.states.multiplication

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.flow.emotion.detectConfusion
import furhatos.app.mathtutor.nlu.MultiplicationResponse
import furhatos.app.mathtutor.nlu.RepeatQuestionIntent
import furhatos.app.mathtutor.resetWrongAnswers
import furhatos.app.mathtutor.wrongAnswer
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures

fun MultiplicationPractice1(times: Int, value: Int): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Multiplication Practice 1")
        } else {
            furhat.gesture(Gestures.Nod(strength=0.4))
            furhat.say("Very well. Can you tell me how we can formulate this as a multiplication?")
        }
        parallel {
            goto(detectConfusion)
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 10000)
    }

    onResponse<MultiplicationResponse> {
        val x1 = it.intent.times;
        val x2 = it.intent.value;

        if (x1.value == times && x2.value == value || x1.value == value && x2.value == times) {
            resetWrongAnswers(users.current)
            goto(MultiplicationPractice2(value))
        } else {
            wrongAnswer(users.current)
            call(wrongMultiplication)
            reentry()
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
        furhat.listen(timeout=6000)
    }

}

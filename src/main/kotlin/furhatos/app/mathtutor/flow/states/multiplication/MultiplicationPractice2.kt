package furhatos.app.mathtutor.flow.states.multiplication


import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.flow.emotion.reactToEmotion
import furhatos.app.mathtutor.nlu.AdditionResponse
import furhatos.app.mathtutor.nlu.MultiplicationResponse
import furhatos.app.mathtutor.nlu.RepeatQuestionIntent
import furhatos.app.mathtutor.resetWrongAnswers
import furhatos.app.mathtutor.wrongAnswer
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures

fun MultiplicationPractice2(x: Int): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Multiplication Practice 2")
        } else {
            furhat.gesture(Gestures.Nod(strength=0.4))
            furhat.say("You are doing very well. Following this method, tell me what happens if two more people " +
                    "with $x apples join. ${furhat.voice.pause("500ms")} More specifically, what is the solution of five times $x?")
        }
        parallel {
            goto(reactToEmotion())
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 20000)
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

    onEvent("ConfusionEvent") {
        furhat.say("You look confused. Let me repeat the question")
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

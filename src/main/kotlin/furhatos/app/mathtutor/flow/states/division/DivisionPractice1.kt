package furhatos.app.mathtutor.flow.states.division;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.flow.emotion.reactToEmotion
import furhatos.app.mathtutor.nlu.DivisionExpressionResponse
import furhatos.app.mathtutor.resetWrongAnswers
import furhatos.app.mathtutor.wrongAnswer
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures

fun DivisionPractice1(total: Int? = null, perDay: Int? = null): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Division Practice 1")
        } else {
            furhat.gesture(Gestures.Nod(strength=0.4))
            furhat.say("Great. Can you express that calculation as a division?")
        }
        parallel {
            goto(reactToEmotion())
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 20000)
    }

    onReentry {
        parallel {
            goto(reactToEmotion())
        }
        furhat.listen(timeout = 10000)
    }

    onResponse<DivisionExpressionResponse> {
        val _total = it.intent.times.value;
        val _times = it.intent.value.value;

        if (total == _total && perDay == _times) {
            resetWrongAnswers(users.current)
            goto(DivisionPractice2());
        } else {
            wrongAnswer(users.current)
            goto(WrongDivisionCalculation(total, perDay))
        }
    }

    onResponse {
        furhat.say(getUncaughtResponseText())
        reentry()
    }

}

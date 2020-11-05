package furhatos.app.mathtutor.flow.states.multiplication;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.flow.emotion.detectConfusion
import furhatos.app.mathtutor.flow.states.percentage.*
import furhatos.app.mathtutor.nlu.PercentageResponse
import furhatos.app.mathtutor.nlu.RepeatQuestionIntent
import furhatos.app.mathtutor.resetWrongAnswers
import furhatos.app.mathtutor.wrongAnswer
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import kotlin.random.Random

fun PercentagePractice2(total: Int? = null, share: Int? = null): State = state(Interaction) {

    val newTotal: Int;
    val newShare: Int;

    if (total == null || share == null) {
        newShare = 4 * Random.nextInt(1, 5);
        newTotal = 20
    } else {
        newTotal = total;
        newShare = share;
    }

    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Percentage Practice 2")
        } else {
            furhat.gesture(Gestures.Nod(strength = 0.4))
            furhat.say("Super! Can you now tell me what the percentage is if I have $newShare marbles " +
                    "${furhat.voice.pause("500ms")} and the " +
                    "total number of marbles is $newTotal?")
        }
        parallel {
            goto(detectConfusion)
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 20000)
    }

    onResponse<PercentageResponse> {
        val fractionResponse = it.intent.fraction?.value;
        val totalResponse = it.intent.total.value;

        if (fractionResponse == newShare * 5 && totalResponse == 100) {
            resetWrongAnswers(users.current)
            goto(PercentageFinal)
        } else {
            wrongAnswer(users.current)
            call(wrongPercentage)
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
        if (it.text.contains("%") || it.text.contains("percent")) {
            if (it.text.contains((newShare * 5).toString())) {
                resetWrongAnswers(users.current)
                goto(PercentageFinal)
            } else {
                wrongAnswer(users.current)
                call(wrongPercentage)
                reentry()
            }
        } else {
            furhat.say(getUncaughtResponseText())
            furhat.listen(timeout = 15000)
        }
    }

    onResponse {
        furhat.say(getUncaughtResponseText())
        furhat.listen(timeout = 10000)
    }
}

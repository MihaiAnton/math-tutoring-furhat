package furhatos.app.mathtutor.flow.states.multiplication;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.flow.emotion.detectConfusion
import furhatos.app.mathtutor.flow.states.percentage.wrongPercentage
import furhatos.app.mathtutor.isCorrectPercentage
import furhatos.app.mathtutor.nlu.PercentageResponse
import furhatos.app.mathtutor.nlu.RepeatQuestionIntent
import furhatos.app.mathtutor.nlu.StringAnswer
import furhatos.app.mathtutor.resetWrongAnswers
import furhatos.app.mathtutor.wrongAnswer
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures

fun PercentagePractice1(total: Int? = null, share: Int? = null): State = state(Interaction) {

    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Percentage Practice 1")
        } else {
            furhat.gesture(Gestures.Nod(strength=0.4))
            furhat.say("Great. So far it was easy, our total was 100 already. As a general rule, a percentage " +
                    "can be calculated by dividing the specified portion of items by the total number of items and " +
                    "then multiplying this result with 100. ${furhat.voice.pause("500ms")} " +
                    "Using this method, can you tell me what is the " +
                    "percentage of my marbles if I have 1 ${furhat.voice.pause("500ms")} " +
                    "and there are 2 in total?")
        }
        parallel {
            goto(detectConfusion)
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 20000);
    }

    onResponse<PercentageResponse> {
        val fractionResponse = it.intent.fraction?.value;
        val totalResponse = it.intent.total.value;

        if (fractionResponse == 50 && totalResponse == 100) {
            resetWrongAnswers(users.current)
            goto(PercentagePractice2())
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
        if (it.text.contains("%") || it.text.contains("percent")  || isCorrectPercentage(it.text, 50)) {
            if (it.text.contains("50")) {
                resetWrongAnswers(users.current)
                goto(PercentagePractice2())
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
}

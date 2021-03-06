package furhatos.app.mathtutor.flow.states.percentage;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.flow.emotion.detectConfusion
import furhatos.app.mathtutor.flow.states.multiplication.PercentagePractice1
import furhatos.app.mathtutor.isCorrectPercentage
import furhatos.app.mathtutor.nlu.PercentageResponse
import furhatos.app.mathtutor.nlu.RepeatQuestionIntent
import furhatos.app.mathtutor.resetWrongAnswers
import furhatos.app.mathtutor.wrongAnswer
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import kotlin.random.Random

fun PercentagesExplanation(total: Int? = null, share: Int? = null): State = state(Interaction) {

    val newTotal = 100;
    val newShare = 5 * Random.nextInt(1, 19);


    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Percentages Explanation")
        } else {
            furhat.gesture(Gestures.Nod(strength=0.4))
            furhat.say("Very good. We can formulate this as follows: I have $share percent of the marbles. " +
                    "Percent literally means per hundred. If we have a specific number of items and a total number " +
                    "of these items, we can always express this number as a percentage. " +
                    "${furhat.voice.pause("500ms")} If there are $newTotal " +
                    "marbles still, ${furhat.voice.pause("500ms")} " +
                    "and I have $newShare, ${furhat.voice.pause("500ms")} " +
                    "what is the percentage of marbles I have?")
        }
        parallel {
            goto(detectConfusion)
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 15000)
    }

    onResponse<PercentageResponse> {
        val totalResponse = it.intent.total.value;
        val shareResponse = it.intent.fraction?.value;

        if (totalResponse == newTotal && shareResponse == newShare) {
            resetWrongAnswers(users.current)
            goto(PercentagePractice1(newTotal, newShare))
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
        if (it.text.contains("%") || it.text.contains("percent") || isCorrectPercentage(it.text, newShare)) {
            if (it.text.contains(newShare.toString())) {
                resetWrongAnswers(users.current)
                goto(PercentagePractice1(newTotal, newShare))
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

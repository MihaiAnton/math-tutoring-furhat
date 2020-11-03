package furhatos.app.mathtutor.flow.states.percentage;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.flow.emotion.reactToEmotion
import furhatos.app.mathtutor.nlu.PercentageResponse
import furhatos.app.mathtutor.nlu.RepeatQuestionIntent
import furhatos.app.mathtutor.resetWrongAnswers
import furhatos.app.mathtutor.wrongAnswer
import furhatos.flow.kotlin.*
import kotlin.random.Random

fun PercentageIntro(total: Int? = null, share: Int? = null): State = state(Interaction) {

    val newTotal: Int;
    val newShare: Int;

    if (total == null || share == null) {
        newTotal = 100;
        newShare = 10 * Random.nextInt(1, 7);
    } else {
        newTotal = total;
        newShare = share;
    }

    val oppShare = newTotal - newShare

    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Percentage Intro")
        } else {
            furhat.say("Imagine I have $newShare marbles, ${furhat.voice.pause("500ms")} " +
                    "and you have $oppShare marbles. Then there are $newTotal " +
                    "marbles in total. ${furhat.voice.pause("500ms")}" +
                    "You can express the number of marbles I have as a division of the total " +
                    "number of marbles. ${furhat.voice.pause("500ms")} " +
                    "Can you tell me this division expression?")
        }
        parallel {
            goto(reactToEmotion())
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 30000)
    }

    onResponse<PercentageResponse> {
        val totalResponse = it.intent.total.value;
        val shareResponse = it.intent.fraction?.value;

        if (totalResponse == newTotal && newShare == shareResponse) {
            resetWrongAnswers(users.current)
            goto(PercentagesExplanation(newTotal, newShare))
        } else {
            wrongAnswer(users.current)
            call(wrongPercentage)
            reentry()
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
        if (it.text.contains("%") || it.text.contains("percent")) {
            if (it.text.contains(newShare.toString())) {
                resetWrongAnswers(users.current)
                goto(PercentagesExplanation(newTotal, newShare))
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

package furhatos.app.mathtutor.flow.states.division;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.flow.emotion.detectConfusion
import furhatos.app.mathtutor.nlu.DivisionResponse
import furhatos.app.mathtutor.nlu.RepeatQuestionIntent
import furhatos.app.mathtutor.resetWrongAnswers
import furhatos.app.mathtutor.wrongAnswer
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import kotlin.random.Random

fun DivisionExplanation(total: Int? = null, perDay: Int? = null): State = state(Interaction) {

    val answer = perDay?.let { total?.div(it) }

    val newPerDay = Random.nextInt(1, 10);
    val newTotal = newPerDay * Random.nextInt(2, 7);

    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Division Explanation")
        } else {
            furhat.gesture(Gestures.Nod(strength=0.4))
            furhat.say("Super. That is of course because $perDay fits $answer times in $total, which means we " +
                    "need $answer times $perDay apples. ${furhat.voice.pause("500ms")}" +
                    "In division, we call this equation: $total divided by " +
                    "$perDay is $answer. ${furhat.voice.pause("500ms")}" +
                    "In general, with division, we calculate how many times the second argument " +
                    "fits inside the first argument. ${furhat.voice.pause("1000ms")}" +
                    "Now tell me, how many days have I picked apples if I pick " +
                    "$newPerDay apples per day ${furhat.voice.pause("500ms")} and have $newTotal apples?")
        }
        parallel {
            goto(detectConfusion)
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 30000)
    }

    onResponse<DivisionResponse> {
        val days = it.intent.days.value;
        if (days == newTotal / newPerDay) {
            resetWrongAnswers(users.current)
            goto(DivisionPractice1(newPerDay, newTotal))
        } else {
            wrongAnswer(users.current)
            call(wrongDivision)
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
        furhat.say(getUncaughtResponseText())
        furhat.listen(timeout = 15000)
    }

}

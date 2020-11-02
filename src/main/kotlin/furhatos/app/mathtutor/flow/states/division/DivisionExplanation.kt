package furhatos.app.mathtutor.flow.states.division;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.flow.emotion.reactToEmotion
import furhatos.app.mathtutor.nlu.DivisionResponse
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
            furhat.gesture(Gestures.Nod())
            furhat.say("Super. That is of course because $perDay fits $answer times in $total, which means we " +
                    "need $answer times $perDay apples. In division, we call this equation: $total divided by " +
                    "$perDay is $answer. In general, with division, we calculate how many times the second argument " +
                    "fits inside the first argument. Now tell me, how many days have I picked apples if I pick " +
                    "$newPerDay apples per day and have $newTotal apples?")
        }
        parallel {
            goto(reactToEmotion())
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 30000)
    }

    onReentry {
        parallel {
            goto(reactToEmotion())
        }
        furhat.listen(timeout = 15000)
    }

    onResponse<DivisionResponse> {
        val days = it.intent.days.value;
        if (days == newTotal / newPerDay) {
            resetWrongAnswers(users.current)
            goto(DivisionPractice1(newPerDay, newTotal))
        } else {
            wrongAnswer(users.current)
            goto(WrongDivision1(newTotal, newPerDay))
        }
    }

    onResponse {
        furhat.say(getUncaughtResponseText())
        reentry()
    }

}

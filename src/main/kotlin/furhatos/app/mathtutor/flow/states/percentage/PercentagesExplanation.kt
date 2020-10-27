package furhatos.app.mathtutor.flow.states.percentage;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.flow.states.division.DivisionExplanation
import furhatos.app.mathtutor.flow.states.division.DivisionPractice1
import furhatos.app.mathtutor.flow.states.multiplication.PercentagePractice1
import furhatos.app.mathtutor.nlu.PercentageResponse
import furhatos.app.mathtutor.resetWrongAnswers
import furhatos.app.mathtutor.wrongAnswer
import furhatos.flow.kotlin.*
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
            furhat.say("Very good. We can formulate this is as follows: I have $share percent of the marbles. " +
                    "Percent literally means per hundred. If we have a specific number of items and a total number " +
                    "of these items, we can always express this number as a percentage. If there are $newTotal " +
                    "marbles still, and I have $newShare, what is the percentage of marbles I have?")
        }
        furhat.listen(timeout=15000)
    }

    onReentry {
        furhat.listen(timeout=10000)
    }

    onResponse<PercentageResponse> {
        val _totalResponse = it.intent.total.value;
        val _shareResponse = it.intent.total.value;

        if (_totalResponse == newTotal && _shareResponse == newShare) {
            resetWrongAnswers(users.current)
            goto(PercentagePractice1())
        } else {
            wrongAnswer(users.current)
            goto(WrongPercentage1(newTotal, newShare))
        }
    }

    onResponse {
        furhat.say(getUncaughtResponseText())
        reentry()
    }
}

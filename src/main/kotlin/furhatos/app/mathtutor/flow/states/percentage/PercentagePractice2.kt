package furhatos.app.mathtutor.flow.states.multiplication;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.flow.states.percentage.PercentageFinal
import furhatos.app.mathtutor.flow.states.percentage.WrongPercentage1
import furhatos.app.mathtutor.flow.states.percentage.WrongPercentage2
import furhatos.app.mathtutor.flow.states.percentage.WrongPercentage3
import furhatos.app.mathtutor.nlu.MultiplicationResponse
import furhatos.app.mathtutor.nlu.PercentageResponse
import furhatos.app.mathtutor.nlu.PercentageResponse2
import furhatos.app.mathtutor.resetWrongAnswers
import furhatos.app.mathtutor.wrongAnswer
import furhatos.flow.kotlin.*
import kotlin.random.Random

fun PercentagePractice2(total: Int? = null, share: Int? = null): State = state(Interaction) {

    var _total: Int;
    var _share: Int;

    if (total == null || share == null) {
        _share = 4 * Random.nextInt(1, 5);
        _total = 20
    } else {
        _total = total;
        _share = share;
    }

    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Percentage Practice 2")
        } else {
            furhat.say("Super! Can you now tell me what the percentage is if I have $_share marbles and the " +
                    "total number of marbles is $_total?")
        }
        furhat.listen(timeout = 20000)
    }

    onReentry {
        furhat.listen(timeout = 10000)
    }

    onResponse<PercentageResponse2> {
        val fraction = it.intent.fraction.value;

        if (fraction == _share * 5) {
            resetWrongAnswers(users.current)
            goto(PercentageFinal)
        } else {
            wrongAnswer(users.current)
            goto(WrongPercentage3(_total, _share))
        }
    }

    onResponse {
        furhat.say(getUncaughtResponseText())
        reentry()
    }
}

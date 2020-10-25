package furhatos.app.mathtutor.flow.states.multiplication;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.states.percentage.PercentageFinal
import furhatos.app.mathtutor.flow.states.percentage.WrongPercentage1
import furhatos.app.mathtutor.flow.states.percentage.WrongPercentage2
import furhatos.app.mathtutor.flow.states.percentage.WrongPercentage3
import furhatos.app.mathtutor.nlu.MultiplicationResponse
import furhatos.app.mathtutor.nlu.PercentageResponse
import furhatos.app.mathtutor.nlu.PercentageResponse2
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
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
        furhat.say("Percentage Practice 2")
        furhat.listen();
    }

    onResponse<PercentageResponse2> {
        val fraction = it.intent.fraction.value;

        if (fraction == _share * 5) {
            goto(PercentageFinal)
        } else {
            goto(WrongPercentage3(_total, _share))
        }
    }
}

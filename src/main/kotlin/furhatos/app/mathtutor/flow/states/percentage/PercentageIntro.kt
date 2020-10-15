package furhatos.app.mathtutor.flow.states.percentage;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.nlu.PercentageResponse
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import kotlin.random.Random

fun PercentageIntro(total: Int? = null, share: Int? = null): State = state(Interaction) {

    var _total: Int;
    var _share: Int;

    if (total == null || share == null) {
        _total = 100;
        _share = 10 * Random.nextInt(1, 7);
    } else {
        _total = total;
        _share = share;
    }

    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Percentage Intro")
    }

    onResponse<PercentageResponse> {
        val _totalResponse = it.intent.total.value;
        val _shareResponse = it.intent.total.value;

        if(_totalResponse == _total && _share == _shareResponse){
            goto(PercentagesExplanation(_total, _share))
        }
        else{
            goto(WrongPercentage(_total, _share))
        }
    }
}

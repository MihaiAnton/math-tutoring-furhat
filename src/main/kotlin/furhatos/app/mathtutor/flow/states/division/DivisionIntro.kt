package furhatos.app.mathtutor.flow.states.division;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.nlu.DivisionResponse
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import kotlin.random.Random

fun DivisionIntro(total: Int? = null, perDay : Int? = null): State = state(Interaction) {

    var _applesTotal : Int;
    var _perDay : Int;

    if(total == null || perDay == null) {
        _perDay = Random.nextInt(2,8);
        _applesTotal = _perDay * Random.nextInt(2, 6);
    }
    else{
        _applesTotal = total;
        _perDay = perDay;
    }

    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Division Intro")
        furhat.listen()
    }

    onResponse<DivisionResponse> {
        val days = it.intent.days.value;
        println(days);
        println(_applesTotal / _perDay)
        if(days == _applesTotal / _perDay){
            goto(DivisionExplanation(_perDay, _applesTotal))
        }
        else{
            goto(WrongDivision(_applesTotal, _perDay))
        }
    }


}

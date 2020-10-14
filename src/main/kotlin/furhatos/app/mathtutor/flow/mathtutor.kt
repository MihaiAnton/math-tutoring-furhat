package furhatos.app.mathtutor.flow

import furhatos.app.mathtutor.nlu.*
import furhatos.app.spaceshipattendant.flow.gaze.RuleBasedGaze
import furhatos.flow.kotlin.*
import furhatos.nlu.common.*

val CustomGaze = RuleBasedGaze;
val Start = state(Interaction) {
    onEntry {


//        goto(TakingOrder)
    }
}


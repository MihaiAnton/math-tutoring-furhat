package furhatos.app.mathtutor.flow

import furhatos.app.mathtutor.flow.states.*
import furhatos.app.mathtutor.flow.states.division.DivisionIntro
import furhatos.app.mathtutor.flow.states.multiplication.*
import furhatos.app.mathtutor.nlu.*
import furhatos.app.spaceshipattendant.flow.gaze.RuleBasedGaze
import furhatos.flow.kotlin.*
import furhatos.nlu.common.*

val CustomGaze = RuleBasedGaze;
val Start = state(Interaction) {
    onEntry {
        goto(PercentagePractice2(20, 2))
    }
}


package furhatos.app.mathtutor.flow

import furhatos.app.mathtutor.flow.states.*
import furhatos.app.mathtutor.flow.states.multiplication.MultiplicationExample
import furhatos.app.mathtutor.flow.states.multiplication.MultiplicationIntro
import furhatos.app.mathtutor.flow.states.multiplication.MultiplicationPractice1
import furhatos.app.mathtutor.flow.states.multiplication.MultiplicationPractice2
import furhatos.app.mathtutor.nlu.*
import furhatos.app.spaceshipattendant.flow.gaze.RuleBasedGaze
import furhatos.flow.kotlin.*
import furhatos.nlu.common.*

val CustomGaze = RuleBasedGaze;
val Start = state(Interaction) {
    onEntry {
        goto(MultiplicationPractice2(5))
    }
}


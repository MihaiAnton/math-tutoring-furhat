package furhatos.app.mathtutor.flow

import furhatos.app.mathtutor.DIVISION
import furhatos.app.mathtutor.PERCENTAGE
import furhatos.app.mathtutor.flow.states.*
import furhatos.app.mathtutor.flow.states.division.DivisionIntro
import furhatos.app.mathtutor.flow.states.excercises.Exercise
import furhatos.app.mathtutor.flow.states.excercises.ExercisesWrong2
import furhatos.app.mathtutor.flow.states.multiplication.*
import furhatos.app.mathtutor.nlu.*
import furhatos.app.spaceshipattendant.flow.gaze.RuleBasedGaze
import furhatos.flow.kotlin.*
import furhatos.nlu.common.*

val CustomGaze = RuleBasedGaze;
val Start = state(Interaction) {
    onEntry {
        goto(Exercise(PERCENTAGE, 0))
    }
}


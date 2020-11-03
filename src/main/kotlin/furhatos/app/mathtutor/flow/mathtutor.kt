package furhatos.app.mathtutor.flow

import furhatos.app.mathtutor.flow.gaze.dataDrivenGaze
import furhatos.app.mathtutor.flow.states.InitialState
import furhatos.flow.kotlin.state

val CustomGaze = dataDrivenGaze;
val Start = state(Interaction) {
    onEntry {
        goto(InitialState)
    }
}


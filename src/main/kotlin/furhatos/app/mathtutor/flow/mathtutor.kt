package furhatos.app.mathtutor.flow

import furhatos.app.mathtutor.PERCENTAGE
import furhatos.app.mathtutor.flow.gaze.dataDrivenGaze
import furhatos.app.mathtutor.flow.states.*
import furhatos.flow.kotlin.state

var useEmotion = true
val CustomGaze = dataDrivenGaze

val Start = state(Interaction) {
    onEntry {
        goto(VerifyKnowledge(PERCENTAGE))
    }
}


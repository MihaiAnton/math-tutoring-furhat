package furhatos.app.mathtutor.flow

import furhatos.app.mathtutor.nlu.*
import furhatos.flow.kotlin.*
import furhatos.nlu.common.*

val Start = state(Interaction) {
    onEntry {
        random(
            {   furhat.say("Hi there") },
            {   furhat.say("Oh, hello there") }
        )

//        goto(TakingOrder)
    }
}


package furhatos.app.mathtutor.flow.states.percentage;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.states.multiplication.PercentagePractice1
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val WrongPercentage2 = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Wrong Percentage 2")
        delay(1000)
        goto(PercentagePractice1())
    }
}

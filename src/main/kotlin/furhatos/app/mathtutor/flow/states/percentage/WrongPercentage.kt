package furhatos.app.mathtutor.flow.states.percentage;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

fun WrongPercentage(total: Int? = null, share: Int? = null): State = state(Interaction){
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Wrong Percentage")
        delay(1000)
        goto(PercentageIntro(total, share))
    }
}
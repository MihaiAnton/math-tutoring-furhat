package furhatos.app.mathtutor.flow.states.percentage;

import furhatos.app.mathtutor.PERCENTAGE
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.emotion.getGenericWrongResponse
import furhatos.app.mathtutor.wrongConsecutiveResponse
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

fun WrongPercentage(total: Int? = null, share: Int? = null): State = state(Interaction){
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say(getGenericWrongResponse(users.current.wrongConsecutiveResponse, PERCENTAGE))
        delay(1000)
        goto(PercentageIntro(total, share))
    }
}

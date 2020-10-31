package furhatos.app.mathtutor.flow.states.addition;

import furhatos.app.mathtutor.ADDITION
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.emotion.getGenericWrongResponse
import furhatos.app.mathtutor.flow.emotion.wrongResponseReaction
import furhatos.app.mathtutor.flow.states.multiplication.MultiplicationExample
import furhatos.app.mathtutor.wrongConsecutiveResponse
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

fun WrongAddition2(x: Int): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        parallel {
            goto(wrongResponseReaction())
        }

        furhat.say(getGenericWrongResponse(users.current.wrongConsecutiveResponse, ADDITION))
        delay(1000)
        goto(MultiplicationExample(x));
    }
}

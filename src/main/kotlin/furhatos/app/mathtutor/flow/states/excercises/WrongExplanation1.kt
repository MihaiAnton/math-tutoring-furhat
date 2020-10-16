package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.DIVISION
import furhatos.app.mathtutor.MULTIPLICATION
import furhatos.app.mathtutor.PERCENTAGE
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.nlu.CorrectDivisionResponse
import furhatos.app.mathtutor.nlu.CorrectMultiplicationResponse
import furhatos.app.mathtutor.nlu.CorrectPercentageResponse
import furhatos.app.mathtutor.nlu.MathMethod
import furhatos.app.mathtutor.parseMathMethod
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state

fun WrongExplanation1(subject: String?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Wrong Explanation 1")
    }

    if (subject == MULTIPLICATION) {
        onResponse<CorrectMultiplicationResponse> {
            goto(StartExcercises(subject))
        }
    } else if (subject == DIVISION) {
        onResponse<CorrectDivisionResponse> {
            goto(StartExcercises(subject))
        }
    } else if (subject == PERCENTAGE) {
        onResponse<CorrectPercentageResponse> {
            goto(StartExcercises(subject))
        }
    }

    onResponse {
        goto(WrongExplanation2(subject))
    }
}

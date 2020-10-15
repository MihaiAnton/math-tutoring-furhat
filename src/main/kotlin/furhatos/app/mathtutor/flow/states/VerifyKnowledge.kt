package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.*
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.states.division.DivisionIntro
import furhatos.app.mathtutor.flow.states.excercises.StartExcercises
import furhatos.app.mathtutor.flow.states.excercises.WrongExplanation1
import furhatos.app.mathtutor.flow.states.multiplication.MultiplicationIntro
import furhatos.app.mathtutor.flow.states.percentage.PercentageIntro
import furhatos.app.mathtutor.nlu.CorrectDivisionResponse
import furhatos.app.mathtutor.nlu.CorrectMultiplicationResponse
import furhatos.app.mathtutor.nlu.CorrectPercentageResponse
import furhatos.app.mathtutor.nlu.MathMethod
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state

fun VerifyKnowledge(subject: MathMethod?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Verify Knowledge")
        furhat.listen()
    }

    val method = parseMathMethod(subject);
    if (method == MULTIPLICATION) {
        onResponse<CorrectMultiplicationResponse> {
            goto(StartExcercises(method))
        }
    } else if (method == DIVISION) {
        onResponse<CorrectDivisionResponse> {
            goto(StartExcercises(method))
        }
    } else if (method == PERCENTAGE) {
        onResponse<CorrectPercentageResponse> {
            goto(StartExcercises(method))
        }
    }

    onResponse {
        goto(WrongExplanation1(subject))
    }
}

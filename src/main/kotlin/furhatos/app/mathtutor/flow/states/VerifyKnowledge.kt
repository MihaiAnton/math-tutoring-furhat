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

fun VerifyKnowledge(subject: String?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Verify Knowledge on " + subject.toString())
        furhat.listen()
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
        goto(WrongExplanation1(subject))
    }
}

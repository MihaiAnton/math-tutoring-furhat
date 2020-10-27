package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.*
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.isConfident
import furhatos.app.mathtutor.flow.states.division.DivisionIntro
import furhatos.app.mathtutor.flow.states.excercises.StartExercises
import furhatos.app.mathtutor.flow.states.excercises.WrongExplanation1
import furhatos.app.mathtutor.flow.states.excercises.WrongExplanation2
import furhatos.app.mathtutor.flow.states.multiplication.MultiplicationIntro
import furhatos.app.mathtutor.flow.states.percentage.PercentageIntro
import furhatos.app.mathtutor.nlu.CorrectDivisionResponse
import furhatos.app.mathtutor.nlu.CorrectMultiplicationResponse
import furhatos.app.mathtutor.nlu.CorrectPercentageResponse
import furhatos.app.mathtutor.nlu.MathMethod
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No

fun VerifyKnowledge(subject: String?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Verify Knowledge on $subject.")
        } else {
            furhat.say("Let's see about that knowledge. Can you explain how to compute a $subject?")
        }

        furhat.listen(timeout = 30000)
    }

    onTime(delay = 40000) {
        goto(WrongExplanation2(subject))
    }

    onResponse<No> {
        goto(WrongExplanation2(subject))
    }


    if (subject == MULTIPLICATION) {
        onResponse<CorrectMultiplicationResponse> {

        }

        onResponse {
            if (isConfident(users.current)) {
                goto(StartExercises(subject))
            } else {
                goto(WrongExplanation1(subject))
            }
        }

    } else if (subject == DIVISION) {
        onResponse<CorrectDivisionResponse> {
            goto(StartExercises(subject))
        }

        onResponse {
            if (isConfident(users.current)) {
                goto(StartExercises(subject))
            } else {
                goto(WrongExplanation1(subject))
            }
        }

    } else if (subject == PERCENTAGE) {
        onResponse<CorrectPercentageResponse> {
            goto(StartExercises(subject))
        }

        onResponse {
            if (isConfident(users.current)) {
                goto(StartExercises(subject))
            } else {
                goto(WrongExplanation1(subject))
            }
        }
    }



    onResponse {
        goto(WrongExplanation1(subject))
    }
}

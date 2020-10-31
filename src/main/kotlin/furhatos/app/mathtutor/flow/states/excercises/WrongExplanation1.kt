package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.DIVISION
import furhatos.app.mathtutor.MULTIPLICATION
import furhatos.app.mathtutor.PERCENTAGE
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.emotion.isConfident
import furhatos.app.mathtutor.nlu.CorrectDivisionResponse
import furhatos.app.mathtutor.nlu.CorrectMultiplicationResponse
import furhatos.app.mathtutor.nlu.CorrectPercentageResponse
import furhatos.app.mathtutor.nlu.MathMethod
import furhatos.app.mathtutor.parseMathMethod
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No

fun WrongExplanation1(subject: String?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("That does not sound like how $subject works..")
    }


    onTime(delay = 10000) {
        goto(goto(WrongExplanation2(subject)))
    }

    onResponse<No> {
        goto(goto(WrongExplanation2(subject)))
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
        goto(WrongExplanation2(subject))
    }

}

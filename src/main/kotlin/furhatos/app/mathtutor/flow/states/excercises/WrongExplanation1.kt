package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.DIVISION
import furhatos.app.mathtutor.MULTIPLICATION
import furhatos.app.mathtutor.PERCENTAGE
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction

import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.reactToEmotion
import furhatos.app.mathtutor.flow.states.correctExplanation
import furhatos.app.mathtutor.flow.emotion.isConfident
import furhatos.app.mathtutor.flow.emotion.wrongResponseReaction
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

        if (debugMode()) {
            furhat.say("Wrong explanation 1")
        } else {
            random(
                    { furhat.say("It sounds like you're still missing some of it. Try it again.") },
                    { furhat.say("Hmm, that needs something more. Can you elaborate or try again?") },
                    { furhat.say("This is not a perfect answer. I'll let you try one more time.") },
                    { furhat.say("You haven't covered all the aspects of the explanation. Try it again.") },
                    {
                        furhat.say("That it not quite correct yet. You can give it another go before I take you " +
                                "through the explanation.")
                    }
            )
        }
        parallel {
            goto(reactToEmotion())
        }
        furhat.listen(timeout = 20000)

    }

    onTime(delay = 10000) {
        goto(WrongExplanation2(subject))
    }

    onResponse<No> {
        goto(WrongExplanation2(subject))
    }


    if (subject == DIVISION) {
        onResponse<CorrectDivisionResponse> {
            goto(StartExercises(subject))
        }
    } else if (subject == MULTIPLICATION) {
        onResponse<CorrectMultiplicationResponse> {
            goto(StartExercises(subject))
        }
    } else if (subject == PERCENTAGE) {
        onResponse<CorrectPercentageResponse> {
            goto(StartExercises(subject))
        }
    }

    onResponse {
        if (correctExplanation(subject!!, it.text)) {
            goto(StartExercises(subject))
        } else {
            goto(WrongExplanation2(subject))
        }
    }

}

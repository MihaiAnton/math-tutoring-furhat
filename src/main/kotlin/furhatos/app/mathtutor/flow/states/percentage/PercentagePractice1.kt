package furhatos.app.mathtutor.flow.states.multiplication;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.flow.emotion.reactToEmotion
import furhatos.app.mathtutor.flow.states.percentage.WrongPercentage1
import furhatos.app.mathtutor.flow.states.percentage.WrongPercentage2
import furhatos.app.mathtutor.nlu.MultiplicationResponse
import furhatos.app.mathtutor.nlu.PercentageResponse
import furhatos.app.mathtutor.nlu.PercentageResponse2
import furhatos.app.mathtutor.resetWrongAnswers
import furhatos.app.mathtutor.wrongAnswer
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures

fun PercentagePractice1(total: Int? = null, share: Int? = null): State = state(Interaction) {

    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Percentage Practice 1")
        } else {
            furhat.gesture(Gestures.Nod())
            furhat.say("Great. So far it was easy, our total was $total already. As a general rule, a percentage " +
                    "can be calculated by dividing the specified portion of items by the total number of items and " +
                    "then multiplying this result with 100. Using this method, can you tell me what is the " +
                    "percentage of my marbles if I have 1 and there are 2 in total?")
        }
        parallel {
            goto(reactToEmotion())
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 20000);
    }

    onReentry {
        parallel {
            goto(reactToEmotion())
        }
        furhat.listen(timeout = 10000)
    }

    onResponse<PercentageResponse2> {
        val fraction = it.intent.fraction.value;

        if (fraction == 50) {
            resetWrongAnswers(users.current)
            goto(PercentagePractice2())
        } else {
            wrongAnswer(users.current)
            goto(WrongPercentage2)
        }
    }

    onResponse {
        furhat.say(getUncaughtResponseText())
        reentry()
    }

}

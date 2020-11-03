package furhatos.app.mathtutor.flow.states.division;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.flow.emotion.reactToEmotion
import furhatos.app.mathtutor.nlu.DivisionExpressionResponse
import furhatos.app.mathtutor.nlu.DivisionResponse
import furhatos.app.mathtutor.nlu.RepeatQuestionIntent
import furhatos.app.mathtutor.resetWrongAnswers
import furhatos.app.mathtutor.wrongAnswer
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import kotlin.random.Random

fun DivisionPractice2(): State = state(Interaction) {

    val newPerDay = Random.nextInt(1, 10);
    val newTotal = newPerDay * Random.nextInt(2, 7);

    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Division Practice 2")
        } else {
            furhat.gesture(Gestures.Nod(strength=0.4))
            furhat.say("Alright! Can you now tell me what the solution of $newTotal " +
                    "${furhat.voice.pause("500ms")} divided by $newPerDay is?")
        }
        parallel {
            goto(reactToEmotion())
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 30000)
    }

    onResponse<DivisionResponse> {
        val response = it.intent.days.value;

        if(response == newTotal / newPerDay){
            resetWrongAnswers(users.current)
            goto(DivisionFinal)
        }
        else{
            wrongAnswer(users.current)
            goto(WrongDivision2(newTotal, newPerDay))
        }
    }

    onEvent("ConfusionEvent") {
        furhat.say("You look confused. Let me repeat the question")
        reentry()
    }

    onResponse<RepeatQuestionIntent> {
        furhat.say("I'll repeat the question.")
        reentry()
    }

    onResponse {
        furhat.say(getUncaughtResponseText())
        furhat.listen(timeout = 15000)
    }

}

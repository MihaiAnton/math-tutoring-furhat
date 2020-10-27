package furhatos.app.mathtutor.flow.states.division;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.nlu.DivisionExpressionResponse
import furhatos.app.mathtutor.nlu.DivisionResponse
import furhatos.app.mathtutor.resetWrongAnswers
import furhatos.app.mathtutor.wrongAnswer
import furhatos.flow.kotlin.*
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
            furhat.say("Alright! Can you now tell me what the solution of $newTotal divided by $newPerDay is?")
        }

        furhat.listen(timeout = 30000)
    }

    onReentry {
        furhat.listen(timeout = 15000)
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

    onResponse {
        furhat.say(getUncaughtResponseText())
        reentry()
    }

}

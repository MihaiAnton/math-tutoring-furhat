package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.nlu.NumericAnswer
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.Yes

fun Excercise(subject: String?, excerciseId: Int = 0): State = state(Interaction)
{
    onEntry {
        parallel {
            goto(CustomGaze)
        }

        val numberOfQuestions = 2; // TODO to be fetched from file/api
        if (excerciseId < numberOfQuestions) {
            if (excerciseId != 0) {
                furhat.say("Ok, next question..."); // TODO or something random to avoid repetition
            }
            val question = "What is 2 divided by 2?"; // TODO to be fetched from file/api
            furhat.ask(question)
            furhat.listen(endSil = 2000, maxSpeech = 30 * 1000)
        } else {
            furhat.say("We're done with the exercises, let's see how you've done")
            goto(ExcerciseEvaluation);
        }
    }

    // TODO result type to be decided based on question type (also retrieved from file/api)
    onResponse<NumericAnswer> {
        val result = it.intent.number;
        // TODO handle response (correct/incorrect)
        goto(Excercise(subject, excerciseId + 1))
    }

    onResponse {
        goto(Excercise(subject, excerciseId + 1))
    }
}

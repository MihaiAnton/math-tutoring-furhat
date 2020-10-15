package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.INTEGER_RESPONSE
import furhatos.app.mathtutor.STRING_RESPONSE
import furhatos.app.mathtutor.YES_NO_RESPONSE
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.nlu.NumericAnswer
import furhatos.app.mathtutor.randomQuestion
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes

fun Excercise(subject: String?, excerciseId: Int = 0): State = state(Interaction)
{

    var _responseType = "";

    onEntry {
        parallel {
            goto(CustomGaze)
        }

        val numberOfQuestions = 2; // TODO to be fetched from file/api
        if (excerciseId < numberOfQuestions) {
            if (excerciseId != 0) {
                furhat.say("Ok, next question..."); // TODO or something random to avoid repetition
            }
            val question = randomQuestion(subject.toString(), excerciseId); // TODO to be fetched from file/api
            furhat.ask(question.question)
            _responseType = question.responseType;
            furhat.listen(endSil = 2000, maxSpeech = 30 * 1000)
        } else {
            furhat.say("We're done with the exercises, let's see how you've done")
            goto(ExcerciseEvaluation);
        }
    }

    if (_responseType == YES_NO_RESPONSE) {
        onResponse<Yes> {
            // TODO check response
            goto(Excercise(subject, excerciseId + 1))
        }

        onResponse<No> {
            // TODO check response
            goto(Excercise(subject, excerciseId + 1))
        }

        onResponse {
            goto(Excercise(subject, excerciseId + 1))
        }

    } else if (_responseType == INTEGER_RESPONSE) {
        onResponse<NumericAnswer> {
            val result = it.intent.number;
            // TODO handle response (correct/incorrect)
            goto(Excercise(subject, excerciseId + 1))
        }

        onResponse {
            goto(Excercise(subject, excerciseId + 1))
        }

    } else if (_responseType == STRING_RESPONSE) {
        // TODO

        onResponse {
            goto(Excercise(subject, excerciseId + 1))
        }
    }


}

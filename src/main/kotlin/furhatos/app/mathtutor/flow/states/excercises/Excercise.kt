package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.*
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.nlu.NumericAnswer
import furhatos.app.mathtutor.nlu.StringAnswer
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes

fun Excercise(subject: String?, excerciseId: Int = 0): State = state(Interaction)
{

    var _responseType = ""
    var _question: Question? = null

    onEntry {
        parallel {
            goto(CustomGaze)
        }

        println(users.current.correctAnswers.toString() + " " + users.current.wrongAnswers.toString())

        val numberOfQuestions = questionCount(subject.toString())
        if (excerciseId < numberOfQuestions) {
            if (excerciseId != 0) {
                random(
                        { furhat.say("Ok, next question...") },
                        { furhat.say("Next one...") },
                        { furhat.say("Moving on...") },
                        { furhat.say("So, the question is...") }
                )
            } else {
                random(
                        { furhat.say("Ok, first question...") },
                        { furhat.say("First one...") },
                        { furhat.say("Lets start with...") }
                )
            }
            val question = randomQuestion(subject.toString(), excerciseId)

            _responseType = question.responseType
            println(_responseType)
            _question = question
            furhat.ask(question.question)
            furhat.listen(endSil = 2000, maxSpeech = 30 * 1000)
        } else {
            furhat.say("We're done with the exercises, let's see how you've done")
            goto(ExcerciseEvaluation)
        }
    }

    onResponse<Yes> {
        if (_question != null && _responseType == YES_NO_RESPONSE && _question!!.response == "Yes") {
            users.current.correctAnswers++
        } else if (_question != null && _responseType == YES_NO_RESPONSE && _question!!.response == "No") {
            users.current.wrongAnswers++
        }
        goto(Excercise(subject, excerciseId + 1))
    }

    onResponse<No> {
        if (_question != null && _responseType == YES_NO_RESPONSE && _question!!.response == "No") {
            users.current.correctAnswers++

        } else if (_question != null && _responseType == YES_NO_RESPONSE && _question!!.response == "Yes") {
            users.current.wrongAnswers++
        }
        goto(Excercise(subject, excerciseId + 1))
    }

    onResponse<NumericAnswer> {
        val result = it.intent.number;
        if (_question != null && _responseType == INTEGER_RESPONSE && _question!!.response.toInt() == result.value) {
            users.current.correctAnswers++;
        } else {
            users.current.wrongAnswers++;
        }
        goto(Excercise(subject, excerciseId + 1))
    }

    onResponse<StringAnswer> {
        val result = it.intent.response;
        if (_question != null && _responseType == STRING_RESPONSE && _question!!.response == result) {
            users.current.correctAnswers++;
        } else {
            users.current.wrongAnswers++;
        }
        goto(Excercise(subject, excerciseId + 1))
    }


    onResponse {
        users.current.wrongAnswers++;
        goto(Excercise(subject, excerciseId + 1))
    }

}

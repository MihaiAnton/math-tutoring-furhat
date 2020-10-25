package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.*
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.nlu.NumericAnswer
import furhatos.app.mathtutor.nlu.StringAnswer
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import software.amazon.ion.impl.PrivateIonConstants.False


fun Exercise(subject: String?, exerciseId: Int = 0, redoWrong: Boolean = false): State = state(Interaction)
{

    var _responseType = ""
    var _question: Question? = null

    onEntry {
        parallel {
            goto(CustomGaze)
        }


        val numberOfQuestions = questionCount(subject.toString())
        if (!redoWrong && exerciseId < numberOfQuestions) {
            if (exerciseId != 0) {
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

            val question = randomQuestion(subject.toString(), exerciseId)
            users.current.questions.add(question);
            _responseType = question.responseType
            _question = question

            furhat.say(question.question)
            furhat.listen(endSil = 2000, maxSpeech = 30 * 1000, timeout = 20000)
        } else if (redoWrong && exerciseId < users.current.wrongQuestions.size) {
            _responseType = users.current.wrongQuestions[exerciseId].responseType
            _question = users.current.wrongQuestions[exerciseId]

            furhat.say(_question!!.question)
            furhat.listen(endSil = 2000, maxSpeech = 30 * 1000, timeout = 20000)
        } else {
            furhat.say("We're done with the exercises, let's see how you've done")
            goto(ExerciseEvaluation(subject))
        }
    }

    onResponse<Yes> {
        if (_question != null && _responseType == YES_NO_RESPONSE && _question!!.response == "Yes") {
            if (!redoWrong) users.current.correctAnswers++
            if (redoWrong) users.current.wrongAnswers--
        } else if (_question != null && _responseType == YES_NO_RESPONSE && _question!!.response == "No") {
            if (!redoWrong) users.current.wrongAnswers++
            if (!redoWrong) users.current.wrongQuestions.add(_question!!)
        }
        goto(Exercise(subject, exerciseId + 1, redoWrong))
    }

    onResponse<No> {
        if (_question != null && _responseType == YES_NO_RESPONSE && _question!!.response == "No") {
            if (!redoWrong) users.current.correctAnswers++
            if (redoWrong) users.current.wrongAnswers--
        } else if (_question != null && _responseType == YES_NO_RESPONSE && _question!!.response == "Yes") {
            if (!redoWrong) users.current.wrongAnswers++
            if (!redoWrong) users.current.wrongQuestions.add(_question!!)
        }
        goto(Exercise(subject, exerciseId + 1, redoWrong))
    }

    onResponse<NumericAnswer> {
        val result = it.intent.number;
        if (_question != null && _responseType == INTEGER_RESPONSE && _question!!.response.toInt() == result.value) {
            if (!redoWrong) users.current.correctAnswers++
            if (redoWrong) users.current.wrongAnswers--
        } else {
            if (!redoWrong) users.current.wrongAnswers++
            if (!redoWrong) users.current.wrongQuestions.add(_question!!)
        }
        goto(Exercise(subject, exerciseId + 1, redoWrong))
    }

    onResponse<StringAnswer> {
        val result = it.intent.response;
        if (_question != null && _question!!.category == PERCENTAGE && _question!!.responseType == INTEGER_RESPONSE
                && isCorrectPercentage(result, _question!!.response.toInt())) {
            if (!redoWrong) users.current.correctAnswers++
            if (redoWrong) users.current.wrongAnswers--

        } else if (_question != null && _responseType == STRING_RESPONSE && _question!!.response == result) {
            if (!redoWrong) users.current.correctAnswers++
            if (redoWrong) users.current.wrongAnswers--
        } else {
            if (!redoWrong) users.current.wrongAnswers++;
            if (!redoWrong) users.current.wrongQuestions.add(_question!!)
        }
        goto(Exercise(subject, exerciseId + 1, redoWrong))
    }

    onResponse {
        if (!redoWrong) users.current.wrongAnswers++;
        if (!redoWrong) users.current.wrongQuestions.add(_question!!)
        goto(Exercise(subject, exerciseId + 1, redoWrong))
    }

}

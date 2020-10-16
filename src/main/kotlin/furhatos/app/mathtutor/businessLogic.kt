package furhatos.app.mathtutor

import furhatos.app.mathtutor.nlu.MathMethod


const val MULTIPLICATION = "multiplication"
const val DIVISION = "division"
const val PERCENTAGE = "percentage"
const val ADDITION = "addition"

fun parseMathMethod(method: MathMethod?): String? {
    if (method == null) {
        return null;
    }
    val value = method.value;
    if (value == "addition" || value == "summation" || value == "summing" || value == "adding") {
        return ADDITION;
    } else if (value == "multiplication" || value == "multiplying") {
        return MULTIPLICATION;
    } else if (value == "division" || value == "splitting" || value == "dividing") {
        return DIVISION;
    } else if (value == "percentage" || value == "module" || value == "remainder") {
        return PERCENTAGE;
    }

    return null;
}

const val INTEGER_RESPONSE = "integer_response"
const val STRING_RESPONSE = "string_response"
const val YES_NO_RESPONSE = "yes_no_response"

data class Question(val question: String,
                    val category: String,
                    val response: String,
                    val responseType: String) {}

fun questionCount(type: String): Int {
    when (type) {
        DIVISION -> {
            return 2
        }
        MULTIPLICATION -> {
            return 2
        }
        PERCENTAGE -> {
            return 4
        }
    }
    return 0
}

fun randomQuestion(type: String, questionId: Int): Question {

    when (type) {
        DIVISION -> {
            return randomDivisionQuestion(questionId)
        }
        MULTIPLICATION -> {
            return randomMultiplicationQuestion(questionId)
        }
        PERCENTAGE -> {
            return randomPercentageQuestion(questionId)
        }
    }
    return randomPercentageQuestion(questionId);
}

fun randomMultiplicationQuestion(questionId: Int): Question {
    // TODO make this really random
    return Question("What is 8 multiplied by 4?",
            MULTIPLICATION,
            "32",
            INTEGER_RESPONSE)
}

fun randomDivisionQuestion(questionId: Int): Question {
    // TODO make this really random
    return Question("What is 8 divided by 4?",
            DIVISION,
            "2",
            INTEGER_RESPONSE)
}

fun randomPercentageQuestion(questionId: Int): Question {
    // TODO make this really random
    return Question("I have 2 apples and you have 8. If I say that someone has 20% of the apples, am I referring to you?",
            PERCENTAGE,
            "No",
            YES_NO_RESPONSE)
}
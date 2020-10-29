package furhatos.app.mathtutor

import furhatos.app.mathtutor.nlu.MathMethod
import furhatos.flow.kotlin.users
import furhatos.nlu.common.Time
import furhatos.records.User
import kotlin.random.Random


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

fun resetUserExerciseData(user: User) {
    user.wrongAnswers = 0
    user.correctAnswers = 0
    user.questions.clear()
    user.wrongQuestions.clear()
    user.attemptsMultiplication = 0
    user.attemptsDivision = 0
    user.attemptsPercentage = 0
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
            return 4
        }
        MULTIPLICATION -> {
            return 4
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
    val r = Random(1);
    val a = r.nextInt(1, 9);
    val b = r.nextInt(1, 9);
    val c = r.nextInt(1, 9);

    if (questionId <= 0) {
        return Question("What is $a plus $b?",
                MULTIPLICATION,
                "${a + b}",
                INTEGER_RESPONSE)
    } else if (questionId == 1) {
        return Question("What is $a times $b?",
                MULTIPLICATION,
                "${a * b}",
                INTEGER_RESPONSE)
    } else if (questionId == 2) {
        return Question("What is $a multiplied by $b times 2?",
                MULTIPLICATION,
                "${a * b * 2}",
                INTEGER_RESPONSE)
    }
    return Question("What is $a times $b times $c?",
            MULTIPLICATION,
            "${a * b * c}",
            INTEGER_RESPONSE)
}

fun randomDivisionQuestion(questionId: Int): Question {
    val r = Random(1);
    val a = r.nextInt(1, 9);
    val b = r.nextInt(1, 9);
    val factor = r.nextInt(1, 9);

    if (questionId <= 0) {
        return Question("What is ${b * 2} divided by 2?",
                DIVISION,
                "$b",
                INTEGER_RESPONSE)
    } else if (questionId == 1) {
        return Question("What is ${a * 3} divided by 3?",
                DIVISION,
                "$a",
                INTEGER_RESPONSE)
    } else if (questionId == 2) {
        return Question("What is ${a * factor} divided by $factor?",
                DIVISION,
                "$a",
                INTEGER_RESPONSE)
    }
    return Question("Is ${a * b * factor} divided by $factor? equal to ${a * b}",
            DIVISION,
            "Yes",
            YES_NO_RESPONSE)
}

fun randomPercentageQuestion(questionId: Int): Question {
    val r = Random(1);
    val a = r.nextInt(1, 90);
    val b = r.nextInt(1, 9);

    if (questionId <= 0) {
        return Question("I have $b apples from the total of ${b * 4}. What is my share in percentages?",
                PERCENTAGE,
                "25",
                INTEGER_RESPONSE)
    } else if (questionId == 1) {
        return Question("The total number of items is 50. If I own 20%, how many items are not mine?",
                PERCENTAGE,
                "40",
                INTEGER_RESPONSE)
    } else if (questionId == 2) {
        return Question("Let's say I have $a items and you have ${100 - a}. Which is your percentage of the total?",
                PERCENTAGE,
                "${100 - a}",
                INTEGER_RESPONSE)
    }
    return Question("I have 2 apples and you have 8. If I say that someone has 20% of the apples, am I referring to you?",
            PERCENTAGE,
            "No",
            YES_NO_RESPONSE)
}

fun resetWrongAnswers(user: User) {
    user.wrongConsecutiveResponse = 0;
}

fun wrongAnswer(user: User) {
    user.wrongConsecutiveResponse = user.wrongConsecutiveResponse.plus(1);
}

fun isCorrectPercentage(input: String, percentage: Int): Boolean {
    val s = input.trimStart().trimEnd();
    
    if (s == "$percentage%" || s == "$percentage %" || s == "$percentage percent") {
        return true;
    }
    return false;
}
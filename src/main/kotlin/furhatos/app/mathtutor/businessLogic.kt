package furhatos.app.mathtutor

import furhatos.app.mathtutor.nlu.MathMethod


const val MULTIPLICATION = "multiplication"
const val DIVISION = "division"
const val PERCENTAGE = "percentage"
const val ADDITION = "ADDITION"

fun parseMathMethod(method: MathMethod?): String? {
    if (method == null) {
        return null;
    }
    val value = method.value;
    if (value == "addition" || value == "summation" || value == "summing" || value == "adding") {
        return ADDITION;
    }
    else if (value == "multiplication" || value == "multiplying") {
        return MULTIPLICATION;
    }
    else if (value == "division" || value == "splitting" || value == "dividing") {
        return DIVISION;
    }
    else if (value == "percentage" || value == "module" || value == "remainder") {
        return PERCENTAGE;
    }

    return null;
}
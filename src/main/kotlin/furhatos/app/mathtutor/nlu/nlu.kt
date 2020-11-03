package furhatos.app.mathtutor.nlu

import furhatos.nlu.*
import furhatos.nlu.common.Number
import furhatos.util.Language


class StartIntent : Intent()

class OptionsIntent : Intent()

class MoreIntent : Intent()

class MathMethod() : EnumEntity()

class ExercisesIntent : Intent()

class ExplanationIntent : Intent()

class LearningMathMethod(val subject: MathMethod? = null) : ComplexEnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf("@subject")
    }
}

class AdditionResponse(val sum: Number = Number(1), val objectName: String? = "") : ComplexEnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf("@sum", "@sum @objectName", "We have @sum")
    }
}

class MultiplicationResponse(val times: Number = Number(1), val value: Number = Number(1)) : ComplexEnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf("@times times @value", "@value times @times", "@times * @value", "@value * @times")
    }
}

class DivisionResponse(val days: Number = Number(1)) : ComplexEnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf("@days", "@days days", "We have @days days")
    }
}

class DivisionExpressionResponse(val times: Number = Number(1), val value: Number = Number(1)) : ComplexEnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf("@value / @times", "@value divided by @times")
    }
}

class PercentageResponse(val fraction: Number = Number(1), val total: Number = Number(1)) : ComplexEnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf("@fraction / @total", "@fraction divided by @total")
    }
}

class PercentageResponse2(val fraction: Number = Number(1)) : ComplexEnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf("@fraction / 100", "@fraction divided by 100", "@fraction", "@fraction percent", "@fraction out of 100")
    }
}

class CorrectDivisionResponse : EnumEntity()
class CorrectMultiplicationResponse : EnumEntity()
class CorrectPercentageResponse : EnumEntity()


class NumericAnswer(val number: Number = Number(0)) : ComplexEnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf("@number", "It is @number", "The answer is @number")
    }
}

class StringAnswer(val response: String = "") : ComplexEnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf("@response");
    }
}

class RepeatQuestionIntent : Intent()

class IKnowItResponse : EnumEntity()

class StopSessionIntent : Intent()

class MoreExercisesIntent : Intent()

class DifferentCalculationIntent : Intent()

class UnwillingIntent: Intent()

class Unsure: Intent()

class DoContinue : Intent()






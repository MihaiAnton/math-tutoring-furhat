package furhatos.app.mathtutor

import furhatos.flow.kotlin.NullSafeUserDataDelegate
import furhatos.flow.kotlin.UserDataDelegate
import furhatos.records.User

var User.correctAnswers by NullSafeUserDataDelegate { 0 }
var User.wrongAnswers by NullSafeUserDataDelegate { 0 }
val User.questions by NullSafeUserDataDelegate { mutableListOf<Question>() }
val User.wrongQuestions by NullSafeUserDataDelegate { mutableListOf<Question>() }

var User.attemptsMultiplication by NullSafeUserDataDelegate { 0 }
var User.attemptsDivision by NullSafeUserDataDelegate { 0 }
var User.attemptsPercentage by NullSafeUserDataDelegate { 0 }
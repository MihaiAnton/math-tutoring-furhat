package furhatos.app.mathtutor

import furhatos.flow.kotlin.NullSafeUserDataDelegate

import furhatos.records.User

var User.lastValence: Double by NullSafeUserDataDelegate { 0.0 }
var User.lastArousal: Double by NullSafeUserDataDelegate { 0.0 }

var User.rollingValence: Double by NullSafeUserDataDelegate { 0.0 }
var User.rollingArousal: Double by NullSafeUserDataDelegate { 0.0 }


var User.correctAnswers by NullSafeUserDataDelegate { 0 }
var User.wrongAnswers by NullSafeUserDataDelegate { 0 }
val User.questions by NullSafeUserDataDelegate { mutableListOf<Question>() }
val User.wrongQuestions by NullSafeUserDataDelegate { mutableListOf<Question>() }

var User.attemptsMultiplication by NullSafeUserDataDelegate { 0 }
var User.attemptsDivision by NullSafeUserDataDelegate { 0 }
var User.attemptsPercentage by NullSafeUserDataDelegate { 0 }

var User.wrongConsecutiveResponse by NullSafeUserDataDelegate { 0 }

var User.name by NullSafeUserDataDelegate { "" }
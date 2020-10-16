package furhatos.app.mathtutor

import furhatos.flow.kotlin.NullSafeUserDataDelegate
import furhatos.flow.kotlin.UserDataDelegate
import furhatos.records.User

var User.correctAnswers by NullSafeUserDataDelegate { 0 };
var User.wrongAnswers by NullSafeUserDataDelegate { 0 };
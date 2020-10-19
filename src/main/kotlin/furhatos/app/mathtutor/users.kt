package furhatos.app.mathtutor

import furhatos.flow.kotlin.NullSafeUserDataDelegate
import furhatos.records.User

var User.valence : Double? by NullSafeUserDataDelegate { 0.0 }
var User.arousal : Double? by NullSafeUserDataDelegate { 0.0 }

package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.nlu.LearningMathMethod
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state

val OptionsSelection = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Options Selection")
        furhat.listen()
    }

    onResponse<LearningMathMethod> {
        goto(StartTutorial(it.intent.subject))
    }

    onResponse {
        goto(UnwillingUserIntro);
    }
}

package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.DIVISION
import furhatos.app.mathtutor.MULTIPLICATION
import furhatos.app.mathtutor.PERCENTAGE
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.nlu.CorrectDivisionResponse
import furhatos.app.mathtutor.nlu.CorrectMultiplicationResponse
import furhatos.app.mathtutor.nlu.CorrectPercentageResponse
import furhatos.app.mathtutor.nlu.LearningMathMethod
import furhatos.app.mathtutor.parseMathMethod
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
        val method = parseMathMethod(it.intent.subject);
        goto(StartTutorial(method))

    }

    onResponse {
        goto(UnwillingUserIntro);
    }
}

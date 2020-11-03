package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.debugMode
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val MethodExplanation = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }

        if (debugMode()) {
            furhat.say("Method Explanation")
        } else {
            furhat.say("Each tutoring session I will explain or verify your knowledge of the theory behind the " +
                    "chosen calculation. Afterwards, I will test your understanding by giving you exercises.")
        }
        delay(1000)
//        goto(OptionsSelection)
        goto(WhatsYourName)
    }
}
package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.DIVISION
import furhatos.app.mathtutor.MULTIPLICATION
import furhatos.app.mathtutor.PERCENTAGE
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.nlu.*
import furhatos.app.mathtutor.parseMathMethod
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state

val OptionsSelection = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Options Selection")
        } else {
            random(
                    {furhat.say("Okay, great. Please tell me which calculation you want to practice.")},
                    {furhat.say("Okay, great. Which calculation method do you want to practice?")},
                    {furhat.say("Okay, great. Please tell me which method of calculation you wish to practice.")},
                    {furhat.say("Okay, great. What is the calulation method that you want to practice?")}
            )
        }
        furhat.listen(timeout = 6000)
    }

    onReentry {
        if (reentryCount > 2) {
            goto(UnwillingUserIntro)
        } else {
            furhat.say("Remember, you can choose between multiplication, division, and percentages.")
            furhat.listen(timeout = 6000)
        }
    }

    onResponse<LearningMathMethod> {
        val method = parseMathMethod(it.intent.subject);
        goto(StartTutorial(method))

    }

    onResponse<UnwillingIntent> {
        goto(UnwillingUserIntro)
    }

    onResponse {
        furhat.say(getUncaughtResponseText())
        reentry()
    }
}

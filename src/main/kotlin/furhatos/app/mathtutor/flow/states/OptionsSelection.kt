package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.*
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.nlu.*
import furhatos.flow.kotlin.*

val OptionsSelection: State = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Options Selection")
        } else {
            random(
                    { furhat.say ("Ok, ${users.current.name}" ) },
                    { furhat.say ( "Cool, ${users.current.name}" ) },
                    { furhat.say ( "Perfect, ${users.current.name}" ) },
                    { furhat.say ( "Here we go, ${users.current.name}" ) }
            )
            random(
                    { furhat.say("Please tell me which calculation you want to practice.") },
                    { furhat.say("Which calculation method do you want to practice?") },
                    { furhat.say("Please tell me which method of calculation you wish to practice.") },
                    { furhat.say("What is the calculation method that you want to practice?") }
            )
        }

        furhat.glance(users.current)
        furhat.listen(timeout = 6000)
    }

    onReentry {
        if (reentryCount > 2) {
            goto(UnwillingUserIntro)
        } else {
            furhat.say("Remember, you can choose between multiplication, division, and percentages.")
            furhat.glance(users.current)
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
        val method = parseMathMethodWContain(it.text);
        if (method == null) {
            furhat.say(getUncaughtResponseText())
            reentry()
        } else {
            goto(StartTutorial(method))
        }
    }
}

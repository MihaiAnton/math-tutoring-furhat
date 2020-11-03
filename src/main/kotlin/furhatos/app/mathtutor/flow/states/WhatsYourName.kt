package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.name
import furhatos.flow.kotlin.*
import furhatos.nlu.common.TellName

val WhatsYourName = state {
    onEntry {
        // Change the default thresholds:

        users.current.name = "";
//        getEmotionFromApi(users.current)
        parallel {
            goto(CustomGaze)
        }


        if (debugMode()) {
            furhat.say("What is your name")
        } else if (users.current.name == "") {
            random(
                    {
                        furhat.say("First of all, please tell me your name?")
                    },
                    {
                        furhat.say("Dear student, what is your name?")
                    }
            )
        }

        furhat.glance(users.current)
        furhat.listen(timeout = 20000);
    }

    onReentry {
        furhat.listen(timeout = 10000)
    }

    onResponse<TellName> {
        val name = it.intent.name
        users.current.name = name.toString()
        goto(OptionsSelection)
    }

    onNoResponse {
        furhat.say("Ok, let's move on")
        goto(OptionsSelection)
    }
}

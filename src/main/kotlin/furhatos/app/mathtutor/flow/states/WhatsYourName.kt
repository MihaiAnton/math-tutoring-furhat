package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.reactToEmotion
import furhatos.app.mathtutor.name
import furhatos.app.mathtutor.nlu.MyNameIsResponse
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

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

        parallel {
            goto(reactToEmotion())
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 20000);
    }

    onReentry {
        furhat.listen(timeout = 10000)
    }

    onResponse<MyNameIsResponse> {
        val name = it.intent.name
        users.current.name = name.value.toString()
        goto(OptionsSelection)
    }

    onResponse { // Catches everything else
        furhat.say("Ok, let's move on")
        goto(OptionsSelection)
    }

    onTime(delay = 20000) {
        furhat.say("Ok, let's move on")
        goto(OptionsSelection)
    }
}

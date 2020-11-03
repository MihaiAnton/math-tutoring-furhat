package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.name
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

fun FinalState(unwilling: Boolean = false): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Final State")
        } else {
            if (!unwilling) {
                random(
                        { furhat.say("Great session, ${users.current.name}! Feel free to continue or restart your tutoring at any time!") },
                        { furhat.say("Good effort today, ${users.current.name}! Come back to continue or restart your tutorial any time.") },
                        {
                            furhat.say("${users.current.name}, Nice work this session! Come back any time to continue working on your math " +
                                    "skills.")
                        },
                        { furhat.say("Well done today ${users.current.name}! I hope you come back soon to continue to improve your skills.") },
                        {
                            furhat.say("Great work today ${users.current.name}! Don't hesitate to come back to further improve your math " +
                                    "skills.")
                        }
                )
            } else {
                random(
                        { furhat.say("Sorry to see you going ${users.current.name}. I hope I'll see you back again.") },
                        { furhat.say("Don't get discouraged ${users.current.name}, I'll be here whenever you want to learn.") },
                        { furhat.say("Never stop learning ${users.current.name}, it's the safest path to growth.") }
                )
            }
        }
    }
}

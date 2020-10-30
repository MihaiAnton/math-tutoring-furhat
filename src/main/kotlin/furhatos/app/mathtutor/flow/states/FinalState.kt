package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.debugMode
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

fun FinalState(completed: Boolean = true) = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (completed) {
            if (debugMode()) {
                furhat.say("Final State after a session")
            } else {
                random(
                        {furhat.say("Great session! Feel free to continue or restart your tutoring at any time!")},
                        {furhat.say("Good effort today! Come back to continue or restart your tutorial any time.")},
                        {furhat.say("Nice work this session! Come back any time to continue working on your math " +
                                "skills.")},
                        {furhat.say("Well done today! I hope you come back soon to continue to improve your skills.")},
                        {furhat.say("Great work today! Don't hesitate to come back to further improve your math " +
                                "skills.")}
                )
            }
        } else {
            if (debugMode()) {
                furhat.say("Final State after session aborted")
            } else {
                random(
                        {furhat.say("It's a shame to see you go. Feel free to continue or restart your tutoring " +
                                "at any time!")},
                        {furhat.say("Too bad you didn't finish the session with me. Come back to continue or " +
                                "restart your tutorial any time.")},
                        {furhat.say("I'm sorry to see you go so soon. Come back any time to continue working on " +
                                "your math " +
                                "skills.")},
                        {furhat.say("I'm sorry that you're leaving so early. I hope you come back soon to " +
                                "continue to improve your skills.")},
                        {furhat.say("Such a shame that you're going already. Don't hesitate to come back to " +
                                "further improve your math skills.")}
                )
            }
        }
    }
}

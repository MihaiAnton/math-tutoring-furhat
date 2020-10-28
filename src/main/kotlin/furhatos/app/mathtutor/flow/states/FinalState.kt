package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.debugMode
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val FinalState = state {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Final State")
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
    }
}

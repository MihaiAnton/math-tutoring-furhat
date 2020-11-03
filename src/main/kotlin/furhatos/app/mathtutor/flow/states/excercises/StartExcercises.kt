package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.*
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.nlu.MathMethod
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

fun StartExercises(subject: String?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Start Exercises")
        } else {
            random(
                    {furhat.say("Okay, ${users.current.name}, let's start with the exercises. Good luck!")},
                    {furhat.say("${users.current.name}, Let's start with the exercises. Have fun!")},
                    {furhat.say("Alright ${users.current.name}, let's begin. Good luck with the exercises!")},
                    {furhat.say("Time to begin with the exercises, ${users.current.name}. I hope you enjoy it!")}
            )
        }
        delay(1000)

        resetUserExerciseData(users.current)

        goto(Exercise(subject))
    }
}

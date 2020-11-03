package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.flow.states.excercises.StartExercises
import furhatos.app.mathtutor.name
import furhatos.app.mathtutor.nlu.DifferentCalculationIntent
import furhatos.app.mathtutor.nlu.MoreExercisesIntent
import furhatos.app.mathtutor.nlu.StopSessionIntent
import furhatos.flow.kotlin.*

fun AllAnswersCorrect(subject: String?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("All Answers Correct")
        } else {
            random(
                    {
                        furhat.say("You did very well ${users.current.name}, I am impressed! Now, do you want do some more exercises, " +
                                "practice different calculations, or stop the session?")
                    },
                    {
                        furhat.say("That was a good session ${users.current.name}, very nicely done! Now, tell me if you want do some " +
                                "more exercises, practice different calculations, or stop the session.")
                    },
                    {
                        furhat.say("I am very happy with this result ${users.current.name}, well done! Now, what do you want to do next?" +
                                "Do more exercises, practice different calculations, or stop the session?")
                    },
                    {
                        furhat.say("Super ${users.current.name}, that is a great achievement! Do you want to continue with the exercises " +
                                "or would you rather practice a different calculation method?")
                    },
                    {
                        furhat.say("Amazing work this session ${users.current.name}, well done! So do you want to do another round of " +
                                "these exercises, or would you rather hear about a different calculation method?")
                    }
            )
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 10000)
    }

    onReentry {
        furhat.listen(timeout = 10000)
    }

    onResponse<StopSessionIntent> {
        goto(FinalState())
    }

    onResponse<MoreExercisesIntent> {
        goto(StartExercises(subject))
    }

    onResponse<DifferentCalculationIntent> {
        goto(OptionsSelection)
    }

    onResponse {
        furhat.say(getUncaughtResponseText())
        reentry()
    }
}

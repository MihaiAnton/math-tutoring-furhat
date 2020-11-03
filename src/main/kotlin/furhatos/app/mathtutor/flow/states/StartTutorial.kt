package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.nlu.ExercisesIntent
import furhatos.app.mathtutor.nlu.ExplanationIntent
import furhatos.app.mathtutor.nlu.UnwillingIntent
import furhatos.flow.kotlin.*


fun StartTutorial(subject: String?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Start Tutorial on $subject")
        } else {
            random(
                    {furhat.say("Very well, let's begin. Do you want to hear the explanation of $subject, or get " +
                            "right to the exercises?")},
                    {furhat.say("Okay, let's start. Do you want to do exercises right away, or first hear the " +
                            "explanation of $subject?")},
                    {furhat.say("Let's begin then. Do you want to learn the theory of $subject, or do you want " +
                            "to go straight to the exercises?")},
                    {furhat.say("Time to begin then. Do you wish to hear the theory behind $subject, or go " +
                            "immediately to the exercises?")}
            )
        }

        furhat.glance(users.current)
        furhat.listen(timeout = 6000)
    }

    onReentry {
        furhat.listen(timeout = 6000)
    }

    onResponse<ExercisesIntent> {
        goto(VerifyKnowledge(subject))
    }

    onResponse<ExplanationIntent> {
        goto(StartExplanation(subject))
    }

    onResponse<UnwillingIntent> {
        goto(UnwillingUserIntro)
    }

    onResponse {
        furhat.say(getUncaughtResponseText())
        reentry()
    }
}



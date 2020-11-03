package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction

import furhatos.app.mathtutor.flow.debugMode

import furhatos.app.mathtutor.flow.emotion.wrongResponseReaction

import furhatos.app.mathtutor.flow.states.StartExplanation
import furhatos.app.mathtutor.nlu.MathMethod
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

fun WrongExplanation2(subject: String?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }

        if (debugMode()) {
            furhat.say("Wrong Explanation 2")
        } else {
            random(
                    {furhat.say("That is not correct. Let's go through the explanation first.")},
                    {furhat.say("It seems to me that you need to freshen up your $subject skills.")},
                    {furhat.say("That wasn't the answer I was looking for. Time for some explanation.")},
                    {furhat.say("Perhaps you'll do better after going through the explanation.")}
            )

        delay(1000)
        goto(StartExplanation(subject))
    }
}

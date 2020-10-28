package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.*
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.states.division.DivisionIntro
import furhatos.app.mathtutor.flow.states.multiplication.MultiplicationIntro
import furhatos.app.mathtutor.flow.states.percentage.PercentageIntro
import furhatos.app.mathtutor.nlu.MathMethod
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

fun StartExplanation(subject: String?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Start Explanation")
        } else {
            random(
                    {furhat.say("Okay, let's start the explanation of $subject.")},
                    {furhat.say("Alright, let's continue with $subject.")},
                    {furhat.say("Good. Then I will now tell you all about $subject.")},
                    {furhat.say("Great, let me explain $subject to you then.")},
                    {furhat.say("Amazing, let's start with the explanation of computing ${subject}s.")}
            )
        }


        if (subject == MULTIPLICATION) {
            goto(MultiplicationIntro())
        } else if (subject == DIVISION) {
            goto(DivisionIntro())
        } else if (subject == PERCENTAGE) {
            goto(PercentageIntro())
        }
    }


}

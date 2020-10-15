package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.*
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.states.division.DivisionIntro
import furhatos.app.mathtutor.flow.states.multiplication.MultiplicationIntro
import furhatos.app.mathtutor.flow.states.percentage.PercentageIntro
import furhatos.app.mathtutor.nlu.MathMethod
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

fun StartExplanation(subject: MathMethod?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        furhat.say("Start Explanation")

        val method = parseMathMethod(subject);
        if(method == MULTIPLICATION){
            goto(MultiplicationIntro())
        }
        else if(method == DIVISION){
            goto(DivisionIntro())
        }
        else if(method == PERCENTAGE){
            goto(PercentageIntro)
        }
    }


}

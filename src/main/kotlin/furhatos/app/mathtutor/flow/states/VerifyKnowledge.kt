package furhatos.app.mathtutor.flow.states;

import furhatos.app.mathtutor.*
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.isConfident
import furhatos.app.mathtutor.flow.emotion.reactToEmotion
import furhatos.app.mathtutor.flow.states.division.DivisionIntro
import furhatos.app.mathtutor.flow.states.excercises.StartExercises
import furhatos.app.mathtutor.flow.states.excercises.WrongExplanation1
import furhatos.app.mathtutor.flow.states.excercises.WrongExplanation2
import furhatos.app.mathtutor.flow.states.multiplication.MultiplicationIntro
import furhatos.app.mathtutor.flow.states.percentage.PercentageIntro
import furhatos.app.mathtutor.nlu.*
import furhatos.flow.kotlin.*
import furhatos.nlu.common.DontKnow
import furhatos.nlu.common.No

fun VerifyKnowledge(subject: String?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Verify Knowledge on $subject.")
        } else {
            random(
                    { furhat.say("Let's see about that knowledge. Can you explain how to compute a $subject?") },
                    { furhat.say("Alright, then tell me how you should compute a $subject") },
                    { furhat.say("Let me verify your knowledge. How should someone compute a $subject") },
                    { furhat.say("Okay, I'll now check your knowledge. How do you go about computing a $subject") },
                    { furhat.say("Okay, then how should you compute a $subject") },
                    { furhat.say("Very well. Just to verify, can you tell me how to compute a $subject") },
                    { furhat.say("Okay then, how should you compute a $subject") },
                    { furhat.say("Great! Then please tell me how to compute a $subject") }
            )
        }

        parallel {
            goto(reactToEmotion())
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 30000)
    }

    onTime(delay = 40000) {
        goto(WrongExplanation2(subject))
    }

    onResponse<No> {
        goto(WrongExplanation2(subject))
    }

    if (subject == DIVISION) {
        onResponse<CorrectDivisionResponse> {
            goto(StartExercises(subject))
        }
    } else if (subject == MULTIPLICATION) {
        onResponse<CorrectMultiplicationResponse> {
            goto(StartExercises(subject))
        }
    } else if (subject == PERCENTAGE) {
        onResponse<CorrectPercentageResponse> {
            goto(StartExercises(subject))
        }
    }

    onResponse<DontKnow> {
        goto(WrongExplanation2(subject))
    }

    onResponse {
        if (correctExplanation(subject!!, it.text)) {
            goto(StartExercises(subject))
        } else {
            goto(WrongExplanation1(subject))
        }
    }
}

fun correctExplanation(subject: String, text: String): Boolean {
    val words = text.split("[ \\.,']+")
    var topicCount = 0
    var topics: List<String> = arrayListOf("")

    if (subject == MULTIPLICATION) {
        topics = arrayListOf("times", "plus", "addition", "add", "factor", "multiply", "multiplication")
    } else if (subject == DIVISION) {
        topics = arrayListOf("division", "divided", "fits", "fit", "inside", "minus", "add", "addition")
    } else if (subject == PERCENTAGE) {
        topics = arrayListOf("fraction", "division", "hundred", "total", "percentage", "portion", "part")
    }

    for (t in topics) {
        if (words[0].contains(t)) {
            topicCount += 1
        }
    }

    return topicCount >= 2
}

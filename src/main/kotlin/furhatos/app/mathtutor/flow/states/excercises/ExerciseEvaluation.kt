package furhatos.app.mathtutor.flow.states.excercises;

import furhatos.app.mathtutor.*
import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.states.AllAnswersCorrect
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

fun ExerciseEvaluation(subject: String?): State = state(Interaction) {
    onEntry {
        parallel {
            goto(CustomGaze)
        }
        random(
                { furhat.say("I checked your answers and you have given me ${users.current.correctAnswers} correct and  ${users.current.wrongAnswers} wrong answers")},
                { furhat.say("You answered ${users.current.correctAnswers} questions correct and  ${users.current.wrongAnswers} questions wrong ")}
        )

        if (users.current.wrongAnswers == 0) {
            delay(1000)
            resetUserExerciseData(users.current)
            goto(AllAnswersCorrect(subject))
        } else {
            when (subject) {
                MULTIPLICATION -> {
                    users.current.attemptsMultiplication++
                    if (users.current.attemptsMultiplication > 1) {
                        goto(exercisesWrong2(subject))
                    } else {
                        goto(exercisesWrong1(subject))
                    }
                }
                DIVISION -> {
                    users.current.attemptsDivision++
                    if (users.current.attemptsDivision > 1) {
                        goto(exercisesWrong2(subject))
                    } else {
                        goto(exercisesWrong1(subject))
                    }
                }
                PERCENTAGE -> {
                    users.current.attemptsPercentage++
                    if (users.current.attemptsPercentage > 1) {
                        goto(exercisesWrong2(subject))
                    } else {
                        goto(exercisesWrong1(subject))
                    }
                }
            }
        }
    }
}

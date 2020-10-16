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
        furhat.say("Exercise Evaluation - correct ${users.current.correctAnswers}, wrong ${users.current.wrongAnswers}")

        if (users.current.wrongAnswers == 0) {
            delay(1000)
            resetUserExerciseData(users.current)
            goto(AllAnswersCorrect(subject))
        } else {
            when (subject) {
                MULTIPLICATION -> {
                    users.current.attemptsMultiplication++
                    if (users.current.attemptsMultiplication > 1) {
                        goto(ExercisesWrong2(subject))
                    } else {
                        goto(ExercisesWrong1(subject))
                    }
                }
                DIVISION -> {
                    users.current.attemptsDivision++
                    if (users.current.attemptsDivision > 1) {
                        goto(ExercisesWrong2(subject))
                    } else {
                        goto(ExercisesWrong1(subject))
                    }
                }
                PERCENTAGE -> {
                    users.current.attemptsPercentage++
                    if (users.current.attemptsPercentage > 1) {
                        goto(ExercisesWrong2(subject))
                    } else {
                        goto(ExercisesWrong1(subject))
                    }
                }
            }
        }
    }
}

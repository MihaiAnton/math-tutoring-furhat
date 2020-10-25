package furhatos.app.mathtutor.flow.emotion

import furhatos.app.mathtutor.rollingValence
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.state
import furhatos.records.User
import furhatos.skills.UserManager.list

fun getGenericWrongResponse(consecutiveWrong: Int): String {
    if (consecutiveWrong == 1) {
        // TODO more choices
        val stringList = listOf("Not correct level 1, please try again")
        return stringList.shuffled().take(1)[0]
    } else if (consecutiveWrong == 2 || consecutiveWrong == 3) {
        val stringList = listOf("Not correct level 2")
        return stringList.shuffled().take(1)[0]
    } else {
        val stringList = listOf("Not correct level 3")
        return stringList.shuffled().take(1)[0]
    }
}

fun isConfident(user: User): Boolean {
    return user.rollingValence > 0;
    //TODO find a way to check if the student is confident using emotions
}
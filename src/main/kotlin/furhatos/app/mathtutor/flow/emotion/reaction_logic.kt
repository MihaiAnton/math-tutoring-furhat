package furhatos.app.mathtutor.flow.emotion

import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.rollingValence
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.state
import furhatos.records.User
import furhatos.skills.UserManager.list

fun getGenericWrongResponse(consecutiveWrong: Int, subject: String?): String {
    if (debugMode()) {
        if (consecutiveWrong == 1) {
            val stringList = listOf("Not correct level 1")
            return stringList.shuffled().take(1)[0]
        } else if (consecutiveWrong == 2 || consecutiveWrong == 3) {
            val stringList = listOf("Not correct level 2")
            return stringList.shuffled().take(1)[0]
        } else {
            val stringList = listOf("Not correct level 3")
            return stringList.shuffled().take(1)[0]
        }
    } else {
        if (consecutiveWrong == 1) {
            return getWrongResponseLevelOne(subject)
        } else if (consecutiveWrong == 2 || consecutiveWrong == 3) {
            return getWrongResponseLevelTwo(subject)
        } else {
            return getWrongResponseLevelThree(subject)
        }
    }
}

fun isConfident(user: User): Boolean {
    return user.rollingValence > 0;
    //TODO find a way to check if the student is confident using emotions
}

fun getUncaughtResponseText(): String {
    val stringList = listOf(
            "Could you repeat that?",
            "Can you please repeat?",
            "I didn't understand what you said.",
            "Would you repeat that please?",
            "I'm having trouble understanding what you said.",
            "Could you perhaps rephrase that?",
            "What do you mean again?",
            "What did you say again?",
            "Tell me again please.",
            "It is unclear to me what you're saying.",
            "Can you say that again please?",
            "I didn't hear that very well.",
            "I'm having a hard time understanding what you said.",
            "I'm sorry, perhaps you can repeat that?",
            "I can't figure it out, can you repeat that?",
            "Sorry, I didn't catch that.",
            "Could I get a little bit more information on that?",
            "Would you mind going over that one more time?",
            "I don’t think I quite understand what you meant. Would you mind repeating that?",
            "Could you be more specific?",
            "Could you go over that again?",
            "I didn't get that. Could you run it by me one more time?",
            "Could you maybe say that again please?",
            "I'm not sure what you said. Could you repeat?",
            "Could you tell me that again?",
            "Would you please repeat what you said?",
            "I'm sorry, what did you say?",
            "Sorry, I need you to repeat that for me."
    )
    return stringList.shuffled()[0]
}

fun getWrongResponseLevelOne(subject: String?): String {
    val stringList = listOf(
            "That's incorrect, unfortunately. Please try again. I'll give you a hint: It's $subject",
            "Not correct. Give it another go.",
            "That's not the right answer, but you can try again!",
            "Hmm, wrong answer. Try again, please.",
            "That answer is false, try again.",
            "That's incorrect. Think carefully and try again. ",
            "Wrong answer, unfortunately. Give it another go.",
            "That answer is false, but do another attempt.",
            "No, please try again. Remember what you know about $subject?",
            "Unfortunately, that is incorrect, but make another try.",
            "That is incorrect. Listen carefully and try again.",
            "That is not correct. Give it a little bit more thought and try again. A hint: use $subject"
    )
    return stringList.shuffled().take(1)[0]
}

fun getWrongResponseLevelTwo(subject: String?): String {
    val stringList = listOf(
            "That is incorrect again. Follow the instructions carefully and try again. We want to use $subject",
            "That is again not the correct answer. Remember what you learned about $subject? Try to think in simple steps and try again.",
            "That is wrong again. You should be able to figure this out with the information I gave you, so think " +
                    "carefully and try again.",
            "That is again the wrong answer. Try to figure it out with $subject as easy as you can, you can do it!",
            "You answered wrongly again. Try to think logically in steps and give it another go!"
    )
    return stringList.shuffled().take(1)[0]
}

fun getWrongResponseLevelThree(subject: String?): String {
    val stringList = listOf(
            "That is also incorrect. Step it up a bit. You should be able to do this. Listen carefully and try one " +
                    "more time, I'm sure you can do it.",
            "Unfortunately that is not the correct answer. You can try and count with your fingers!",
            "This is the incorrect answer, but I'm sure you can do it! Think really hard!",
            "I see you are having a little trouble with $subject. Maybe a little break can help you, or try to +" +
                    "approach answering the question differently.",
            "That is not the correct answer. Try think of the logic behind $subject, it will help you to find the " +
                    "right answer"
    )
    return stringList.shuffled().take(1)[0]
}

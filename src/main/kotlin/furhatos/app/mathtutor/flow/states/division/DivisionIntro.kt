package furhatos.app.mathtutor.flow.states.division;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.flow.emotion.reactToEmotion
import furhatos.app.mathtutor.nlu.DivisionResponse
import furhatos.app.mathtutor.nlu.RepeatQuestionIntent
import furhatos.app.mathtutor.resetWrongAnswers
import furhatos.app.mathtutor.wrongAnswer
import furhatos.flow.kotlin.*
import kotlin.random.Random

fun DivisionIntro(total: Int? = null, perDay: Int? = null): State = state(Interaction) {

    var _applesTotal: Int;
    var _perDay: Int;

    if (total == null || perDay == null) {
        _perDay = Random.nextInt(2, 8);
        _applesTotal = _perDay * Random.nextInt(2, 6);
    } else {
        _applesTotal = total;
        _perDay = perDay;
    }

    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Division Intro")
        } else {
            furhat.say("Imagine I pick $_perDay apples for every day this week. ${furhat.voice.pause("500ms")}" +
                    "If, at some point, I have " +
                    "$_applesTotal apples, ${furhat.voice.pause("500ms")} how many days have I been picking apples?")
        }
        parallel {
            goto(reactToEmotion())
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 30000)
    }

    onResponse<DivisionResponse> {
        val days = it.intent.days.value;
        if (days == _applesTotal / _perDay) {
            resetWrongAnswers(users.current)
            goto(DivisionExplanation(_applesTotal, _perDay))
        } else {
            wrongAnswer(users.current)
            goto(WrongDivision(_applesTotal, _perDay))
        }
    }

    onResponse<RepeatQuestionIntent> {
        furhat.say("I'll repeat the question.")
        reentry()
    }

    onResponse {
        furhat.say(getUncaughtResponseText())
        furhat.listen(timeout = 15000)
    }

}

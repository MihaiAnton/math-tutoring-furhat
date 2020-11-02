package furhatos.app.mathtutor.flow.states.percentage;

import furhatos.app.mathtutor.flow.CustomGaze
import furhatos.app.mathtutor.flow.Interaction
import furhatos.app.mathtutor.flow.debugMode
import furhatos.app.mathtutor.flow.emotion.getUncaughtResponseText
import furhatos.app.mathtutor.flow.emotion.reactToEmotion
import furhatos.app.mathtutor.nlu.PercentageResponse
import furhatos.app.mathtutor.resetWrongAnswers
import furhatos.app.mathtutor.wrongAnswer
import furhatos.flow.kotlin.*
import kotlin.random.Random

fun PercentageIntro(total: Int? = null, share: Int? = null): State = state(Interaction) {

    var _total: Int;
    var _share: Int;

    if (total == null || share == null) {
        _total = 100;
        _share = 10 * Random.nextInt(1, 7);
    } else {
        _total = total;
        _share = share;
    }

    var _oppShare = _total - _share

    onEntry {
        parallel {
            goto(CustomGaze)
        }
        if (debugMode()) {
            furhat.say("Percentage Intro")
        } else {
            furhat.say("Imagine I have $_share marbles, ${furhat.voice.pause("500ms")} " +
                    "and you have $_oppShare marbles. Then there are $_total " +
                    "marbles in total. ${furhat.voice.pause("500ms")}" +
                    "You can express the number of marbles I have as a division of the total " +
                    "number of marbles. ${furhat.voice.pause("500ms")} " +
                    "Can you tell me this division expression?")
        }
        parallel {
            goto(reactToEmotion())
        }
        furhat.glance(users.current)
        furhat.listen(timeout = 30000)
    }

    onReentry {
        parallel {
            goto(reactToEmotion())
        }
        furhat.listen(timeout = 15000)
    }

    onResponse<PercentageResponse> {
        val _totalResponse = it.intent.total.value;
        val _shareResponse = it.intent.fraction.value;

        if (_totalResponse == _total && _share == _shareResponse) {
            resetWrongAnswers(users.current)
            goto(PercentagesExplanation(_total, _share))
        } else {
            wrongAnswer(users.current)
            goto(WrongPercentage(_total, _share))
        }
    }

    onResponse {
        furhat.say(getUncaughtResponseText())
        reentry()
    }
}

package furhatos.app.mathtutor.flow.emotion

import furhatos.gestures.Gesture
import furhatos.gestures.Gestures

class Emotion(var name: String, var valence: Double, var arousal: Double, var gesture: Gesture)

val FRUSTRATION = Emotion("frustrated", -0.4, 0.4, Gestures.Thoughtful)
val SATISFIED = Emotion("satisfied", 0.2, 0.2, Gestures.Smile)
val EXCITED = Emotion("excited", 0.4, 0.4, Gestures.Smile)
val NEUTRAL = Emotion("neutral", 0.0, 0.0, Gestures.Smile)
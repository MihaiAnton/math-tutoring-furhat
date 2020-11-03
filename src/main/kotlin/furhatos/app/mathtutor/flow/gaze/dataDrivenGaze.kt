package furhatos.app.mathtutor.flow.gaze

import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.records.Location

const val aversionSpeakingMean = 1.96
const val aversionSpeakingStd = 0.32
const val gazeSpeakingMean = 4.75
const val gazeSpeakingStd = 1.39
const val aversionListeningMean = 1.14
const val aversionListeningStd = 0.27
const val gazeListeningMean = 7.21
const val gazeListeningStd = 1.88

fun sampleGaussian(mean : Double, std : Double) : Int {
    val r = java.util.Random()
    return ((r.nextGaussian() * std + mean) * 1000).toInt()
}

fun randomLocation(): Location {
    val glances = listOf<Location>(
            Location.DOWN_LEFT, Location.DOWN_RIGHT,
            Location.DOWN, Location.RIGHT, Location.LEFT)
    return glances.shuffled().take(1)[0]
}


val dataDrivenGaze = state {
    onEntry {
        while (true) {
            if (furhat.isListening) {
                val gazeDuration = sampleGaussian(gazeListeningMean, gazeListeningStd)
//                println("Gazing at user while listening for $gazeDuration millis")
                delay(gazeDuration.toLong())
                val aversionDuration = sampleGaussian(aversionListeningMean, aversionListeningStd)
                furhat.glance(randomLocation(), duration = aversionDuration)
//                println("Gazing at random location while listening for $aversionDuration millis")
                delay(aversionDuration.toLong())
            } else {
                val gazeDuration = sampleGaussian(gazeSpeakingMean, gazeSpeakingStd)
//                println("Gazing at user while speaking for $gazeDuration millis")
                delay(gazeDuration.toLong())
                val aversionDuration = sampleGaussian(aversionSpeakingMean, aversionSpeakingStd)
                furhat.glance(randomLocation(), duration = aversionDuration)
//                println("Gazing at random location while speaking location for $aversionDuration millis")
                delay(aversionDuration.toLong())
            }
        }
    }
}

package furhatos.app.spaceshipattendant.flow.gaze

import furhatos.app.mathtutor.flow.gaze.randomDownLocation
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

import kotlin.random.Random

val RuleBasedGaze = state {
    onTime(repeat=2000..4000) {
        if (furhat.isSpeaking) {
            // Glance at a random location for 25% to 100% of an iteration (avg 50%)
            furhat.glance(randomDownLocation(), duration= Random.nextInt(1000,2000))
        } else {
            // Glance at a random location for 12.5% to 50% of an iteration (avg 25%)
            furhat.glance(randomDownLocation(), duration= Random.nextInt(500,1000))
        }
        // Glance at the user for the rest of the iteration
    }
}

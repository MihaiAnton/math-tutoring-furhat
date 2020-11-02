package furhatos.app.spaceshipattendant.flow.gaze

import furhatos.app.mathtutor.flow.gaze.randomLocation
import furhatos.flow.kotlin.*
import kotlin.random.Random

val RandomGaze = state {
    onTime(repeat = 400..4000) {
        furhat.glance(randomLocation(), duration = Random.nextInt(10, 40) * 100)
    }
}


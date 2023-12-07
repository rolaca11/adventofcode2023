package io.ropi.adventofcode2023.day6

import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt

class Run(
    val raceLength: Double,
    recordDistance: Double
) {

    val holdLengthOfRecord = (raceLength - sqrt(raceLength.pow(2) - (4*recordDistance))) / 2

    val numberOfWaysToBeatRecord
        get() = (holdLengthOfRecord.floor() exclusive (raceLength-holdLengthOfRecord).ceil()).count()

    fun distanceTraveled(buttonHeldFor: Int) = (raceLength - buttonHeldFor) * buttonHeldFor
}

private fun Double.ceil(): Int = ceil(this).toInt()
private fun Double.floor(): Int = floor(this).toInt()

private infix fun Int.exclusive(other: Int) = IntRange(this + 1, other -1)

package io.ropi.adventofcode2023.day5

import kotlin.math.max
import kotlin.math.min

class MappingRange(
    val destinationRangeStart: Long,
    val sourceRangeStart: Long,
    val rangeLength: Long
) {
    operator fun get(source: Long): Long? = if (source in sourceRange) {
        destinationRangeStart + (source - sourceRangeStart)
    } else {
        null
    }

    fun mapRange(range: LongRange) =
        this[max(range.first, sourceRange.first)]!!..this[min(range.last, sourceRange.last)]!!

    override fun toString(): String {
        return "$sourceRange -> $destinationRange"
    }

    val sourceRange = sourceRangeStart..<sourceRangeStart + rangeLength
    val destinationRange = destinationRangeStart..<destinationRangeStart + rangeLength


}
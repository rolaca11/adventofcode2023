package io.ropi.adventofcode2023.day5

class MappingRange(
    private val destinationRangeStart: Long,
    private val sourceRangeStart: Long,
    private val rangeLength: Long
) {
    operator fun get(source: Long): Long? = if (source in sourceRangeStart..<sourceRangeStart + rangeLength) {
        destinationRangeStart + (source - sourceRangeStart)
    } else {
        null
    }
}
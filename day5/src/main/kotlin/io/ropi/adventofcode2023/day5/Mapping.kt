package io.ropi.adventofcode2023.day5

class Mapping(
    val mappingRanges: List<MappingRange>
) {
    var source: Mapping? = null
    var destination: Mapping? = null

    operator fun get(source: Long): Long {
        val mapping = mappingRanges.firstNotNullOfOrNull { it[source] } ?: source

        return destination?.get(mapping) ?: mapping
    }

    operator fun get(sourceRanges: List<LongRange>) = sourceRanges.flatMap { this[it] }

    operator fun get(sourceRange: LongRange): List<LongRange> {
        val result = mutableListOf<LongRange>()

        highestRangeWithLowerStart(sourceRange)?.let {
            result.add(it.mapRange(sourceRange))
        }

        rangesWithHigherStartLowerEnd(sourceRange).forEach { mappingRange ->
            result.add(mappingRange.mapRange(sourceRange))
        }

        result.addAll(rangesWithoutMapping(sourceRange))

        return if (destination != null) {
            destination!![result]
        } else {
            result
        }
    }

    private fun highestRangeWithLowerStart(sourceRange: LongRange): MappingRange? =
        mappingRanges.filter { it.sourceRange.first <= sourceRange.first }
            .filter { it.sourceRange.last >= sourceRange.first }
            .maxByOrNull { it.sourceRangeStart }

    private fun rangesWithHigherStartLowerEnd(sourceRange: LongRange): List<MappingRange> =
        mappingRanges.filter { it.sourceRange.first > sourceRange.first }
            .filter { it.sourceRange.first <= sourceRange.last }

    private fun rangesWithoutMapping(sourceRange: LongRange): List<LongRange> {
        val sortedRanges = mappingRanges
            .filter {
                sourceRange.contains(it.sourceRange.last) || sourceRange.contains(it.sourceRange.first)
            }
            .sortedBy { it.sourceRangeStart }

        val result = mutableListOf<LongRange>()

        for (i in sortedRanges.indices.drop(1)) {
            if (sortedRanges[i - 1].sourceRange.last >= sourceRange.last) {
                break
            }

            if(sortedRanges[i].sourceRange.first - sortedRanges[i - 1].sourceRange.last > 1) {
                result.add(
                    LongRange(
                        start = sortedRanges[i - 1].sourceRange.last + 1,
                        endInclusive = sortedRanges[i].sourceRange.first - 1
                    )
                )
            }
        }

        if (sortedRanges.isEmpty()) {
            if(mappingRanges.all { !it.sourceRange.contains(sourceRange.first) }) {
                result.add(sourceRange)
            }
        } else {
            if (sortedRanges.minOf { it.sourceRange.first } > sourceRange.first) {
                result.add(sourceRange.first..<sortedRanges.minOf { it.sourceRange.first })
            }
            if (sortedRanges.maxOf { it.sourceRange.last } < sourceRange.last) {
                result.add((sortedRanges.maxOf { it.sourceRange.last } + 1)..sourceRange.last)
            }
        }

        return result
    }

    val first: Mapping
        get() = source?.first ?: this
}
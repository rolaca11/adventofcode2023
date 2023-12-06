package io.ropi.adventofcode2023.day5

class Mapping(
    val mappingRanges: List<MappingRange>
) {
    var source: Mapping? = null
    var destination: Mapping? = null

    operator fun get(source: Long): Long {
        val mapping = mappingRanges.map { it[source] }.filterNotNull().firstOrNull() ?: source

        return destination?.get(mapping) ?: mapping
    }

    val first: Mapping
        get() = source?.first ?: this
}
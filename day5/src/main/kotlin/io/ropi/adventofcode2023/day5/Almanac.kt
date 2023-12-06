package io.ropi.adventofcode2023.day5

data class Almanac(
    val seedNumbers: List<Long>,
    val firstMapping: Mapping
) {
    companion object {
        fun parseFrom(input: String): Almanac {
            val parts = input.split("\n\n")

            val seedNumbers =
                Regex("seeds: (.*)").matchEntire(parts[0])?.groups?.get(1)?.value?.split(" ")?.map { it.toLong() }
                    ?: throw Error()

            var previousMapping: Mapping? = null
            for (part in parts.subList(fromIndex = 1)) {
                val mappingRanges = part.lines().subList(1).map {
                    val ranges = it.split(" ").map { range -> range.toLong() }
                    MappingRange(
                        destinationRangeStart = ranges[0],
                        sourceRangeStart = ranges[1],
                        rangeLength = ranges[2]
                    )
                }

                val mapping = Mapping(
                    mappingRanges = mappingRanges
                )

                mapping.source = previousMapping
                previousMapping?.destination = mapping
                previousMapping = mapping
            }

            return Almanac(
                seedNumbers = seedNumbers,
                firstMapping = previousMapping?.first ?: throw Error()
            )
        }
    }
}

private fun <T> List<T>.subList(fromIndex: Int): List<T> {
    return this.subList(fromIndex, this.lastIndex + 1)
}
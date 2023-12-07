package io.ropi.adventofcode2023.day5

fun stage1(input: String): Long {
    val almanac = parseStage1(input)

    return parseSeedNumbersStage1(input.lines()).minOf { almanac.mapping[it] }
}

fun parseStage1(input: String): Almanac {
    val parts = input.split("\n\n")

    val mapping: Mapping = parseMappings(parts)

    return Almanac(
        mapping = mapping
    )
}

fun parseMappings(parts: List<String>): Mapping {
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
    return previousMapping?.first ?: throw Error()
}

fun parseSeedNumbersStage1(parts: List<String>) =
    (Regex("seeds: (.*)").matchEntire(parts[0])?.groups?.get(1)?.value?.split(" ")?.map { it.toLong() }
        ?: throw Error())

fun <T> List<T>.subList(fromIndex: Int): List<T> {
    return this.subList(fromIndex, this.lastIndex + 1)
}
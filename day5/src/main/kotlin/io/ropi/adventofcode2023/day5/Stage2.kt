package io.ropi.adventofcode2023.day5

fun stage2(input: String): Long {
    val almanac = parseStage2(input)

    return almanac.mapping[parseSeedNumbersStage2(input.lines())].minOf { it.first }
}

fun parseStage2(input: String): Almanac {
    val parts = input.split("\n\n")

    val mapping: Mapping = parseMappings(parts)

    return Almanac(
        mapping = mapping
    )
}

fun parseSeedNumbersStage2(parts: List<String>): List<LongRange> {
    val split = Regex("seeds: (.*)").matchEntire(parts[0])?.groups?.get(1)?.value?.split(" ")?.map { it.toLong() }
        ?: throw Error()

    val ranges = mutableListOf<LongRange>()

    for (i in split.indices step 2) {
        ranges.add(split[i]..<split[i] + split[i + 1])
    }

    return ranges
}

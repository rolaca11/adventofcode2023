package io.ropi.adventofcode2023.day5

fun stage1(input: String): Long {
    val almanac = Almanac.parseFrom(input)

    return almanac.seedNumbers.minOf { almanac.firstMapping[it] }
}
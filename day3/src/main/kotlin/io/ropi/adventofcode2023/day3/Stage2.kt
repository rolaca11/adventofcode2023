package io.ropi.adventofcode2023.day3

fun stage2(input: String): Long {
    val schematic = Schematic.parseFrom(input)

    return schematic.gearRatios.sumOf { it.first.number.toLong() * it.third.number.toLong() }
}
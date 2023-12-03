package io.ropi.adventofcode2023.day3

fun stage1(input: String): Int {
    val schematic = Schematic.parseFrom(input)

    return schematic.partNumbers.sumOf { it.number }
}
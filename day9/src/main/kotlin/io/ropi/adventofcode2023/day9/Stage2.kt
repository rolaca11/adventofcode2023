package io.ropi.adventofcode2023.day9

fun stage2(input: String) = input.lines()
    .filter { it.isNotBlank() }
    .map { Sequence.parseFrom(it) }
    .sumOf { it.findPreviousValue() }
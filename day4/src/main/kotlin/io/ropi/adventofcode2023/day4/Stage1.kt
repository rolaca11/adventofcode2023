package io.ropi.adventofcode2023.day4

fun stage1(input: String) = input.lines().filter { it.isNotBlank() }
    .map { Card.parseFrom(it) }
    .sumOf{ it.value }
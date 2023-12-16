package io.ropi.adventofcode2023.day12

fun stage1(input: String) = input.lines()
    .map { it.split(" ") }
    .map { Row(it[0]) to it[1].split(",").map { chunkSize -> chunkSize.toInt() } }
    .sumOf { it.first.numberOfValidConfigurations(it.second) }
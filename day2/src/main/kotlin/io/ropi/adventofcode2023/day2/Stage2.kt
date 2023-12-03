package io.ropi.adventofcode2023.day2

fun stage2(input: String) =
    input.lines().filterNot { it.isBlank() }
        .map { Game.parseFrom(it) }
        .sumOf { game -> game.power }
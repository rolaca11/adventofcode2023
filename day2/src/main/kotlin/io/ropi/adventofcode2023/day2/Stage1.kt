package io.ropi.adventofcode2023.day2

fun stage1(input: String, existingColors: Map<Color, Int>) =
    input.lines().filterNot { it.isBlank() }
        .map { Game.parseFrom(it) }
        .filter { game -> game.isValid(existingColors) }
        .sumOf { game -> game.id }
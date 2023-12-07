package io.ropi.adventofcode2023.day6

fun stage2(input: String) = Race.parseFromStage2(input).listNumOfWaysToWin().reduce { i, j -> i*j }

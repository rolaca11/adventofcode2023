package io.ropi.adventofcode2023.day6

fun stage1(input: String) = Race.parseFrom(input).listNumOfWaysToWin().reduce { i, j -> i*j }

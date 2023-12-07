package io.ropi.adventofcode2023.day7

fun stage1(input: String) = Game.parseFrom(input = input).rankings.map { it.key.bid * it.value }.sum()

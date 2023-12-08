package io.ropi.adventofcode2023.day7

fun stage2(input: String) = Game.parseFrom(input = input).rankingsWithJoker.apply { this.entries.forEach{println(it)} }.map { it.key.bid.toLong() * it.value }.sum()

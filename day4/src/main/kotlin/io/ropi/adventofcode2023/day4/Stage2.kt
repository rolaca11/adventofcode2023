package io.ropi.adventofcode2023.day4

fun stage2(input: String): Int {
    val cardHolder = CardHolder(input.lines().filter { it.isNotBlank() }
        .map { Card.parseFrom(it) }
        .map { it.id to it }
        .toMap())

    return cardHolder.calculateCardCount()
}
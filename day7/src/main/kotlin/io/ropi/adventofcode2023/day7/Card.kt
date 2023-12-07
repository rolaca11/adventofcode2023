package io.ropi.adventofcode2023.day7

enum class Card(
    val label: Char,
    val value: Int
) {
    ACE('A', 13),
    KING('K', 12),
    QUEEN('Q', 11),
    JACK('J', 10),
    TEN('T', 9),
    NINE('9', 8),
    EIGHT('8', 7),
    SEVEN('7', 6),
    SIX('6', 5),
    FIVE('5', 4),
    FOUR('4', 3),
    THREE('3', 2),
    TWO('2', 1);
}
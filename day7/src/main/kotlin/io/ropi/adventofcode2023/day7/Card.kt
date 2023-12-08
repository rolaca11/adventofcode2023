package io.ropi.adventofcode2023.day7

enum class Card(
    val label: Char,
    val value: Int,
    val valueStage2: Int
) {
    ACE('A', 13, 13),
    KING('K', 12, 12),
    QUEEN('Q', 11, 11),
    JACK('J', 10, 1),
    TEN('T', 9, 10),
    NINE('9', 8, 9),
    EIGHT('8', 7, 8),
    SEVEN('7', 6, 7),
    SIX('6', 5, 6),
    FIVE('5', 4, 5),
    FOUR('4', 3, 4),
    THREE('3', 2, 3),
    TWO('2', 1, 2);
}
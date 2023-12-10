package io.ropi.adventofcode2023.day10

data class Position(
    val x: Int,
    val y: Int
) {
    operator fun minus(other: Position) = Position(x - other.x, y - other.y)

    operator fun plus(other: Position) = Position(x + other.x, y + other.y)

    operator fun times(factor: Int) = Position(x * factor, y * factor)

    companion object {
        val LEFT = Position(-1, 0)
        val TOP = Position(0, -1)
        val RIGHT = Position(1, 0)
        val BOTTOM = Position(0, 1)
    }
}

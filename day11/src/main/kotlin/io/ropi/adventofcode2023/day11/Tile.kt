package io.ropi.adventofcode2023.day11

sealed class Tile(val position: Position) {
    abstract fun copy(position: Position): Tile

    override fun toString() = "(${position.x}, ${position.y})"

    class Galaxy(position: Position): Tile(position) {
        override fun copy(position: Position) = Galaxy(position)
    }

    class Empty(position: Position): Tile(position) {
        override fun copy(position: Position) = Empty(position)
    }
}

enum class TileFactory(val label: Char, val generator: (Position) -> Tile) {
    GALAXY(
        label = '#',
        generator = Tile::Galaxy
    ),
    EMPTY(
        label = '.',
        generator = Tile::Empty
    );

    operator fun invoke(position: Position) = generator(position)
}
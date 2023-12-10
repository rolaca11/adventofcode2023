package io.ropi.adventofcode2023.day10

import java.util.LinkedList

class Grid(
    private val tiles: Map<Position, Tile>
) {
    operator fun get(x: Int) = Row(x)

    operator fun get(position: Position) = tiles[position]

    fun tileDistanceMap(): Map<Tile, Int> {
        val result = mutableMapOf<Tile, Int>()

        val route = LinkedList<Tile>()
        val startingTile = tiles.values.findOrThrow { it is Tile.StartingTile }
        route.add(startingTile)
        result[startingTile] = 0

        while (true) {

            val current = try {
                route.first.neighbours(this)
                    .filter { !result.containsKey(it) || result[it]!! > route.size }
                    .findOrThrow { !route.contains(it) }
            } catch (e: NoSuchElementException) {
                if (route.isEmpty()) {
                    break
                } else {
                    route.pop()
                    continue
                }
            }

            route.push(current)

            if (!result.containsKey(current) || result[current]!! > route.size - 1) {
                result[current] = route.size - 1
            }
        }

        return result.filterNot { it.key is Tile.EmptyTile }
    }

    private fun Tile.crawl(distance: Int, visitedTiles: MutableMap<Tile, Int>) {
        neighbours(this@Grid)
            .filterNot { visitedTiles.containsKey(it) && visitedTiles[it]!! <= distance }
            .forEach {
                it?.apply { visitedTiles[this] = distance + 1 }
                    ?.crawl(distance + 1, visitedTiles)
            }
    }

    inner class Row(private val x: Int) {
        operator fun get(y: Int) = this@Grid.tiles[Position(x = x, y = y)]
    }

    companion object {
        fun parseFrom(input: String) = Grid(
            input.lines().flatMapIndexed { y, row ->
                row.mapIndexed { x, label ->
                    val position = Position(x, y)
                    position to TileFactory.entries.findOrThrow { it.label == label }(position)
                }
            }.toMap()
        )
    }
}

private fun <T> Iterable<T>.findOrThrow(predicate: (T) -> Boolean) =
    this.find(predicate) ?: throw NoSuchElementException()
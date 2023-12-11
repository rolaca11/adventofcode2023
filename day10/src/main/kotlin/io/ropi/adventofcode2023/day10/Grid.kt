package io.ropi.adventofcode2023.day10

import java.util.LinkedList
import java.util.concurrent.atomic.AtomicInteger

val i = AtomicInteger(0)

data class Grid(
    val tiles: Map<Position, Tile>
) {
    operator fun get(x: Int) = Row(x)

    operator fun get(position: Position) = tiles[position]

    val mainLoop by lazy { tileDistanceMap().keys.toList() }

    fun groupTilesByComponent(): List<Component> {
        val components = mutableListOf(
            Component(
                tiles = mainLoop.associateBy { it.position },
                grid = this,
                isMainLoop = true
            )
        )

        var i = 0
        while (true) {
            println("GROUPING: ${i++}")
            components += tiles.values.find { tile -> !components.any { it.tiles.values.contains(tile) } }
                ?.let { findAllTilesInComponent(it) } ?: break
        }

        return components
    }

    fun findAllTilesInComponent(firstTile: Tile, grid: Grid = this): Component {
        println("BFS Count: ${i.incrementAndGet()}")
        val route = LinkedList<Tile>()
        route += firstTile
        val currentComponent = mutableMapOf(firstTile.position to firstTile)

        while (true) {
            val tile = try {
                route.first.let { it.neighbours(this).filter { neighbour -> neighbour.isInSameComponentAs(it, this) } }
                    .filter { !route.contains(it) }
                    .filter { !mainLoop.contains(it) }
                    .findOrThrow { !currentComponent.values.contains(it) }
            } catch (e: NoSuchElementException) {
                if (route.isEmpty()) {
                    break
                } else {
                    route.pop()
                    continue
                }
            }

            route.push(tile)
            currentComponent += tile.position to tile
        }

        return Component(currentComponent, grid)
    }

    val expandedGrid by lazy {
        Grid(tiles.values.flatMap {
            val copy = it.copy(it.position * 2)
            val bottomPosition = copy.position + Position(0, 1)
            val rightPosition = copy.position + Position(1, 0)

            listOfNotNull(
                copy.position to copy,
                copy.position + Position(1, 1) to Tile.EmptyTile(copy.position + Position(1, 1)),
                it.bottom(this)?.let { tile ->
                    bottomPosition to if (it.isConnectedTo(tile, this)) {
                        Tile.VerticalPipe(bottomPosition)
                    } else {
                        Tile.EmptyTile(bottomPosition)
                    }
                } ?: (bottomPosition to Tile.EmptyTile(bottomPosition)),
                it.right(this)?.let { tile ->
                    rightPosition to if (it.isConnectedTo(tile, this)) {
                        Tile.HorizontalPipe(rightPosition)
                    } else {
                        Tile.EmptyTile(rightPosition)
                    }
                } ?: (rightPosition to Tile.EmptyTile(rightPosition)),
            )
        }.toMap())
    }

    fun tileDistanceMap(): Map<Tile, Int> {
        val result = mutableMapOf<Tile, Int>()

        val route = LinkedList<Tile>()
        val startingTile = tiles.values.findOrThrow { it is Tile.StartingTile }
        route.add(startingTile)
        result[startingTile] = 0

        while (true) {
            val current = try {
                route.first.connectedNeighbours(this)
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

    override fun toString(): String {
        val buffer = StringBuffer()
        for (y in 0..tiles.keys.maxOf { it.y }) {
            for (x in 0..tiles.keys.maxOf { it.x }) {
                buffer.append(
                    when (this[x][y]) {
                        is Tile.EmptyTile -> "."
                        is Tile.HorizontalPipe -> "-"
                        is Tile.NorthEastPipe -> "L"
                        is Tile.NorthWestPipe -> "J"
                        is Tile.SouthEastPipe -> "F"
                        is Tile.SouthWestPipe -> "7"
                        is Tile.StartingTile -> "S"
                        is Tile.VerticalPipe -> "|"
                        null -> " "
                    }
                )
            }
            buffer.append('\n')
        }

        return buffer.toString()
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

internal fun <T> Iterable<T>.findOrThrow(predicate: (T) -> Boolean) =
    this.find(predicate) ?: throw NoSuchElementException()
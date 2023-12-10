package io.ropi.adventofcode2023.day10

import java.util.LinkedList

class Grid(
    private val tiles: Map<Position, Tile>
) {
    operator fun get(x: Int) = Row(x)

    operator fun get(position: Position) = tiles[position]

    fun groupTilesByComponent(): List<Component> {
        val mainLoop = tileDistanceMap().keys
        val components = mutableListOf(
            Component(
                tiles = mainLoop.toList(),
                grid = this,
                isMainLoop = true
            )
        )

        while (true) {
            val firstTile = tiles.values.find { tile -> !components.any { it.tiles.contains(tile) } } ?: break

            components += Component(findAllTilesInComponent(firstTile), this)
        }

        return components
    }

    fun findAllTilesInComponent(firstTile: Tile): List<Tile> {
        val route = LinkedList<Tile>()
        route += firstTile
        val currentComponent = mutableListOf(firstTile)

        while (true) {
            val tile = try {
                route.first.let { it.neighbours(this).filter { neighbour -> neighbour.isInSameComponentAs(it, this) } }
                    .filter { !route.contains(it) }
                    .findOrThrow { !currentComponent.contains(it) }
            } catch (e: NoSuchElementException) {
                if (route.isEmpty()) {
                    break
                } else {
                    route.pop()
                    continue
                }
            }

            route.push(tile)
            currentComponent += tile
        }

        return currentComponent
    }

    fun expandedGrid(): Grid {
        return Grid(tiles.values.flatMap {
            val copy = it.copy(it.position * 2)
            listOfNotNull(
                copy.position to copy,
                copy.position + Position(1, 1) to Tile.EmptyTile(copy.position + Position(1, 1)),
                it.bottom(this)?.let { tile ->
                    val newPosition = copy.position + Position(1, 0)
                    newPosition to if (it.isConnectedTo(tile, this)) {
                        Tile.HorizontalPipe(newPosition)
                    } else {
                        Tile.EmptyTile(newPosition)
                    }
                },
                it.right(this)?.let { tile ->
                    val newPosition = copy.position + Position(1, 0)
                    newPosition to if (it.isConnectedTo(tile, this)) {
                        Tile.HorizontalPipe(newPosition)
                    } else {
                        Tile.EmptyTile(newPosition)
                    }
                },
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
package io.ropi.adventofcode2023.day11

import java.util.*

class Sky(private val tiles: List<List<Tile>>) {
    fun row(y: Int) = tiles[y]

    fun column(x: Int) = tiles.map { row -> row[x] }

    operator fun get(y: Int) = try {
        row(y)
    } catch (e: NoSuchElementException) {
        null
    }

    operator fun get(position: Position) = try {
        tiles[position.y][position.x]
    } catch (e: NoSuchElementException) {
        null
    } catch (e: IndexOutOfBoundsException) {
        null
    }

    val rows = tiles
    val columns by lazy { tiles.random().indices.map { column(it) } }

    val expanded by lazy {
        val emptyRows = rows.mapIndexed { index, row -> index to row }
            .filter { it.second.all { tile -> tile is Tile.Empty } }
            .map { it.first }
        val emptyColumns = columns.mapIndexed { index, row -> index to row }
            .filter { it.second.all { tile -> tile is Tile.Empty } }
            .map { it.first }

        val expandedTiles = mutableListOf<List<Tile>>()

        tiles.mapIndexed { y, row ->
            val addRow = {
                val expandedRow = mutableListOf<Tile>()
                row.mapIndexed { x, tile ->
                    val addColumn = { expandedRow.add(tile.copy(Position(expandedRow.size, expandedTiles.size))) }

                    addColumn()
                    if (emptyColumns.contains(x)) {
                        addColumn()
                    }
                }

                expandedRow
            }

            expandedTiles.add(addRow())
            if (emptyRows.contains(y)) {
                expandedTiles.add(addRow())
            }

        }

        Sky(expandedTiles)
    }

    val Tile.left: Tile? get() = this@Sky[this.position.left]
    val Tile.top: Tile? get() = this@Sky[this.position.top]
    val Tile.right: Tile? get() = this@Sky[this.position.right]
    val Tile.bottom: Tile? get() = this@Sky[this.position.bottom]

    val Tile.neighbours: List<Tile> get() = listOfNotNull(left, top, right, bottom)

    fun findTiles(predicate: (Tile) -> Boolean) = tiles.flatten().filter(predicate)

    fun findRoute(from: Tile, to: Tile): List<Tile> {
        val startingRoute = LinkedList<Tile>()
        var routes = listOf<Queue<Tile>>(startingRoute)
        startingRoute.add(from)

        while (true) {
            routes = routes.flatMap { route ->
                route.first().neighbours
                    .filterNot { route.contains(it) }
                    .map<Tile, Queue<Tile>> { LinkedList(route).apply { push(it) } }
            }

            val correctRoute = routes.find { it.contains(to) }
            if (correctRoute != null) {
                return correctRoute.toList()
            } else if(routes.isEmpty()) {
                throw Error()
            } else {
                continue
            }
        }
    }

    companion object {
        fun parseFrom(input: String) = Sky(
            input.lines().mapIndexed { y, row ->
                row.mapIndexed { x, label ->
                    TileFactory.entries.findOrThrow { it.label == label }(Position(x, y))
                }
            }
        )
    }
}

fun <T> Collection<T>.findOrThrow(predicate: (T) -> Boolean) = find(predicate) ?: throw Error()
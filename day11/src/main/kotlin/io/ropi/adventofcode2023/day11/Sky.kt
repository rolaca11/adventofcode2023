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

    fun expanded(expansionFactor: Int): Sky {
        val emptyRows = rows.mapIndexed { index, row -> index to row }
            .filter { it.second.all { tile -> tile is Tile.Empty } }
            .map { it.first }
        val emptyColumns = columns.mapIndexed { index, row -> index to row }
            .filter { it.second.all { tile -> tile is Tile.Empty } }
            .map { it.first }

        val expandedTiles = mutableListOf<List<Tile>>()

        tiles.mapIndexed { y, row ->
            val expandedRow = mutableListOf<Tile>()
            row.mapIndexed { x, tile ->
                val expansionCountX = emptyColumns.expansionCount(x)
                val expansionCountY = emptyRows.expansionCount(y)
                expandedRow.add(tile.copy(Position(
                    x = (x - expansionCountX) + (expansionCountX * expansionFactor) + if(emptyColumns.contains(x)) (expansionFactor - 1) else 0,
                    y = (y - expansionCountY) + (expansionCountY * expansionFactor) + if(emptyRows.contains(y)) (expansionFactor - 1) else 0
                )))
            }

            expandedTiles.add(expandedRow)

        }

        return Sky(expandedTiles)
    }

    fun List<Int>.expansionCount(i: Int) = count { it < i }

    fun findTiles(predicate: (Tile) -> Boolean) = tiles.flatten().filter(predicate)

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
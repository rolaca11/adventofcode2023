package io.ropi.adventofcode2023.day11

class Sky(private val tiles: List<List<Tile>>) {
    private fun row(y: Int) = tiles[y]

    private fun column(x: Int) = tiles.map { row -> row[x] }

    operator fun get(y: Int) = try {
        row(y)
    } catch (e: NoSuchElementException) {
        null
    }

    private val rows = tiles
    private val columns by lazy { tiles.random().indices.map { column(it) } }

    fun expanded(expansionFactor: Int): Sky {
        val emptyRows = rows.mapIndexed { index, row -> index to row }
            .filter { it.second.all { tile -> tile is Tile.Empty } }
            .map { it.first }
        val emptyColumns = columns.mapIndexed { index, row -> index to row }
            .filter { it.second.all { tile -> tile is Tile.Empty } }
            .map { it.first }

        val expandedTiles = tiles.mapIndexed { y, row ->
            val expandedRow = mutableListOf<Tile>()
            row.mapIndexed { x, tile ->
                val expansionCountX = emptyColumns.expansionCount(x)
                val expansionCountY = emptyRows.expansionCount(y)
                expandedRow.add(tile.copy(Position(
                    x = (x - expansionCountX) + (expansionCountX * expansionFactor) + if(emptyColumns.contains(x)) (expansionFactor - 1) else 0,
                    y = (y - expansionCountY) + (expansionCountY * expansionFactor) + if(emptyRows.contains(y)) (expansionFactor - 1) else 0
                )))
            }

            expandedRow
        }

        return Sky(expandedTiles)
    }

    private fun List<Int>.expansionCount(i: Int) = count { it < i }

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
package io.ropi.adventofcode2023.day10

data class Component(
    val tiles: Map<Position, Tile>,
    val grid: Grid,
    val isMainLoop: Boolean = false
) {
    val touchesEdge: Boolean
        get() = tiles.any { it.value.neighbours(grid).size < 4 }

    fun canLeak(): Boolean {

        return grid.expandedGrid.findAllTilesInComponent(tiles.values.first().let { it.copy(it.position * 2) }).touchesEdge
    }
}

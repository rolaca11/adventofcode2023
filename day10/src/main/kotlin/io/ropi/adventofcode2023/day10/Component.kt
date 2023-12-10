package io.ropi.adventofcode2023.day10

data class Component(
    val tiles: List<Tile>,
    val grid: Grid,
    val isMainLoop: Boolean = false
) {
    val touchesEdge: Boolean
        get() = tiles.any { it.neighbours(grid).size < 4 }

    fun canLeak(): Boolean {

        val expandedGrid = grid.expandedGrid()



        return false
    }

}

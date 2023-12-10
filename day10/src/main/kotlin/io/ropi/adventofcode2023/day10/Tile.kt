package io.ropi.adventofcode2023.day10

sealed class Tile(
    private val position: Position,
) {
    fun left(grid: Grid) = grid.neighbour(Position.LEFT)
    fun top(grid: Grid) = grid.neighbour(Position.TOP)
    fun right(grid: Grid) = grid.neighbour(Position.RIGHT)
    fun bottom(grid: Grid) = grid.neighbour(Position.BOTTOM)

    protected fun Grid.neighbour(diff: Position): Tile? {
        return this[position + diff]
    }

    abstract val Grid.neighbours: List<Tile>

    private fun isConnectedTo(tile: Tile, grid: Grid) = grid.neighbours.contains(tile)

    fun neighbours(grid: Grid) = grid.neighbours.filter { it.isConnectedTo(this@Tile, grid) }

    override fun toString() = position.toString()

    class VerticalPipe(
        position: Position,
    ) : Tile(position) {
        override val Grid.neighbours: List<Tile>
            get() = listOfNotNull(top(this), bottom(this))
    }

    class HorizontalPipe(
        position: Position,
    ) : Tile(position) {
        override val Grid.neighbours: List<Tile>
            get() = listOfNotNull(left(this), right(this))
    }

    class NorthEastPipe(
        position: Position,
    ) : Tile(position) {
        override val Grid.neighbours: List<Tile>
            get() = listOfNotNull(top(this), right(this))
    }

    class NorthWestPipe(
        position: Position,
    ) : Tile(position) {
        override val Grid.neighbours: List<Tile>
            get() = listOfNotNull(top(this), left(this))
    }

    class SouthWestPipe(
        position: Position,
    ) : Tile(position) {
        override val Grid.neighbours: List<Tile>
            get() = listOfNotNull(bottom(this), left(this))
    }

    class SouthEastPipe(
        position: Position,
    ) : Tile(position) {
        override val Grid.neighbours: List<Tile>
            get() = listOfNotNull(bottom(this), right(this))
    }

    class StartingTile(
        position: Position,
    ) : Tile(position) {
        override val Grid.neighbours: List<Tile>
            get() = listOfNotNull(top(this), bottom(this), left(this), right(this))
    }

    class EmptyTile(
        position: Position,
    ) : Tile(position) {
        override val Grid.neighbours: List<Tile>
            get() = emptyList()
    }
}

enum class TileFactory(
    val label: Char,
    private val constructor: (Position) -> Tile
) {
    VERTICAL_PIPE('|', Tile::VerticalPipe),
    HORIZONTAL_PIPE('-', Tile::HorizontalPipe),
    NORTH_EAST_PIPE('L', Tile::NorthEastPipe),
    NORTH_WEST_PIPE('J', Tile::NorthWestPipe),
    SOUTH_WEST_PIPE('7', Tile::SouthWestPipe),
    SOUTH_EAST_PIPE('F', Tile::SouthEastPipe),
    STARTING_TILE('S', Tile::StartingTile),
    EMPTY_TILE('.', Tile::EmptyTile);

    operator fun invoke(
        position: Position,
    ) = constructor(position)
}








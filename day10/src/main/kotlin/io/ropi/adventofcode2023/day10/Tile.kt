package io.ropi.adventofcode2023.day10

sealed class Tile(
    val position: Position
) {
    fun left(grid: Grid) = grid[position + Position.LEFT]
    fun top(grid: Grid) = grid[position + Position.TOP]
    fun right(grid: Grid) = grid[position + Position.RIGHT]
    fun bottom(grid: Grid) = grid[position + Position.BOTTOM]

    protected abstract val Grid.connectedNeighbours: List<Tile>

    fun isConnectedTo(tile: Tile, grid: Grid) = grid.connectedNeighbours.contains(tile)

    fun connectedNeighbours(grid: Grid) = grid.connectedNeighbours.filter { it.isConnectedTo(this@Tile, grid) }

    fun neighbours(grid: Grid) = listOfNotNull(top(grid), bottom(grid), left(grid), right(grid))

    fun isInSameComponentAs(tile: Tile, grid: Grid) = neighbours(grid).contains(tile)

    fun isEdgeTile(grid: Grid) = neighbours(grid).size < 4

    override fun toString() = position.toString()

    abstract fun copy(position: Position): Tile
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Tile) return false

        if (position != other.position) return false
        if (javaClass != other.javaClass) return false

        return true
    }

    override fun hashCode(): Int {
        return position.hashCode() * 13 + javaClass.hashCode()
    }


    class VerticalPipe(
        position: Position,
    ) : Tile(position) {
        override val Grid.connectedNeighbours: List<Tile>
            get() = listOfNotNull(top(this), bottom(this))

        override fun copy(position: Position) = VerticalPipe(position)
    }

    class HorizontalPipe(
        position: Position,
    ) : Tile(position) {
        override val Grid.connectedNeighbours: List<Tile>
            get() = listOfNotNull(left(this), right(this))

        override fun copy(position: Position) = HorizontalPipe(position)
    }

    class NorthEastPipe(
        position: Position,
    ) : Tile(position) {
        override val Grid.connectedNeighbours: List<Tile>
            get() = listOfNotNull(top(this), right(this))

        override fun copy(position: Position) = NorthEastPipe(position)
    }

    class NorthWestPipe(
        position: Position,
    ) : Tile(position) {
        override val Grid.connectedNeighbours: List<Tile>
            get() = listOfNotNull(top(this), left(this))

        override fun copy(position: Position) = NorthWestPipe(position)
    }

    class SouthWestPipe(
        position: Position,
    ) : Tile(position) {
        override val Grid.connectedNeighbours: List<Tile>
            get() = listOfNotNull(bottom(this), left(this))

        override fun copy(position: Position) = SouthWestPipe(position)
    }

    class SouthEastPipe(
        position: Position,
    ) : Tile(position) {
        override val Grid.connectedNeighbours: List<Tile>
            get() = listOfNotNull(bottom(this), right(this))

        override fun copy(position: Position) = SouthEastPipe(position)
    }

    class StartingTile(
        position: Position,
    ) : Tile(position) {
        override val Grid.connectedNeighbours: List<Tile>
            get() = listOfNotNull(top(this), bottom(this), left(this), right(this))

        override fun copy(position: Position) = StartingTile(position)
    }

    class EmptyTile(
        position: Position,
    ) : Tile(position) {
        override val Grid.connectedNeighbours: List<Tile>
            get() = emptyList()

        override fun copy(position: Position) = EmptyTile(position)
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
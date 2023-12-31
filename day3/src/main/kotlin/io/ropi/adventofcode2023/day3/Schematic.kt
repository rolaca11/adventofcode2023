package io.ropi.adventofcode2023.day3

data class Schematic(
    private val parts: List<Part>
) {
    val partNumbers: List<Part.Number>
        get() = parts.filterIsInstance<Part.Number>()
            .filter { parts.filterIsInstance<Part.Symbol>().any { symbol -> symbol.isAdjacentTo(it) } }

    val gearRatios: List<Triple<Part.Number, Part.Symbol, Part.Number>>
        get() {
            val starSymbols = parts.filterIsInstance<Part.Symbol>().filter { it.symbol == "*" }
                .map { it to parts.filterIsInstance<Part.Number>().filter { num -> it.isAdjacentTo(num) } }

            return starSymbols.filter { it.second.size == 2 }
                .map { Triple(it.second[0], it.first, it.second[1]) }
        }

    companion object {
        private val NUMBER_REGEX = Regex("[0-9]+")
        private val SYMBOL_REGEX = Regex("\\W")
        fun parseFrom(input: String): Schematic {
            val parts = mutableListOf<Part>()

            input.lines().forEachIndexed { y, line ->
                for (match in NUMBER_REGEX.findAll(line).flatMap { it.groups }.filterNotNull()) {
                    parts.add(
                        Part.Number(
                            position = Position(x = match.range.first, y = y),
                            length = match.value.length,
                            number = match.value.toInt()
                        )
                    )
                }
                for (match in SYMBOL_REGEX.findAll(line).flatMap { it.groups }.filterNotNull().filter { it.value != "." }) {
                    parts.add(
                        Part.Symbol(
                            position = Position(x = match.range.first, y = y),
                            symbol = match.value
                        )
                    )
                }
            }

            return Schematic(parts = parts)
        }
    }
}

data class Position(
    val x: Int,
    val y: Int,
) {
    operator fun plus(other: Position) = Position(
        x = x + other.x,
        y = y + other.y
    )

    operator fun minus(other: Position) = Position(
        x = x - other.x,
        y = y - other.y
    )
}

sealed class Part(
    val position: Position,
    val length: Int
) {

    fun isAdjacentTo(part: Part): Boolean {
        val difference = this.position - part.position
        return difference.y in -1..1 &&
                difference.x in -1..part.length
    }

    override fun toString(): String {
        return "Part(position=$position, length=$length)"
    }


    class Number(
        position: Position,
        length: Int,
        val number: Int
    ) : Part(position, length) {
        override fun toString(): String {
            return "Number(number=$number, ${super.toString()})"
        }
    }

    class Symbol(
        position: Position,
        val symbol: String
    ) : Part(position, 1) {
        override fun toString(): String {
            return "Symbol(symbol='$symbol', ${super.toString()})"
        }
    }
}
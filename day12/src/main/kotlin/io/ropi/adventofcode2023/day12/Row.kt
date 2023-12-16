package io.ropi.adventofcode2023.day12

class Row(
    val cells: String
) {
    fun numberOfValidConfigurations(chunkSizes: List<Int>) =
        variations.filter { it.isValidFor(chunkSizes) }.size

    val variations: Collection<Row>
        get() {
            var result = mutableListOf("")

            for (cell in cells) {
                val nextResult = mutableListOf<String>()
                if (cell == '?') {
                    result.forEach { row ->
                        nextResult.add("$row.")
                        nextResult.add("$row#")
                    }
                } else {
                    result.forEach { row ->
                        nextResult.add("$row$cell")
                    }
                }
                result = nextResult
            }

            return result.map { Row(it) }
        }

    fun isValidFor(chunkSizes: List<Int>) = chunkSizes == this.chunkSizes

    val chunkSizes: List<Int>
        get() = cells.split(Regex("\\.+")).filter { it.isNotBlank() }.map { it.length }
}
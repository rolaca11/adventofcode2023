package io.ropi.adventofcode2023.day2

data class Game(
    val id: Int,
    val samples: Collection<Sample>
) {
    fun isValid(existingColors: Map<Color, Int>): Boolean {
        val mergedSamples = minimumRequiredColors()

        return existingColors.map { (color, count) ->
            mergedSamples.getOrDefault(color, 0) <= count
        }.reduce { acc, bool ->
            acc && bool
        }
    }

    private fun minimumRequiredColors() = samples.map { it.colors }.reduce { acc, map ->
        val result = acc.toMutableMap()
        map.entries.forEach { entry ->
            if (!acc.containsKey(entry.key) || entry.value > (acc[entry.key] ?: 0)) {
                result[entry.key] = entry.value
            }
        }

        result
    }

    val power: Long
        get() = minimumRequiredColors().map { it.value.toLong() }.reduce { left, right -> left * right }

    companion object {
        private val REGEX = Regex("^Game (\\d+): (.*)$")
        fun parseFrom(input: String): Game {
            val find = REGEX.matchEntire(input)
            return Game(
                id = find?.groups?.get(1)?.value?.toInt() ?: throw Error(),
                samples = Sample.parseAllFrom(find.groups[2]?.value ?: throw Error())
            )
        }
    }
}

data class Sample(
    val colors: Map<Color, Int>
) {
    companion object {
        fun parseAllFrom(input: String): Collection<Sample> =
            input.split(";").map { parseFrom(it.trim()) }

        fun parseFrom(input: String): Sample {
            return Sample(input.split(",").map {
                val split = it.trim().split(" ")
                Color.valueOf(split[1].uppercase()) to split[0].toInt()
            }.toMap())
        }
    }
}

enum class Color {
    RED, BLUE, GREEN
}

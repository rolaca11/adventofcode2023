package io.ropi.adventofcode2023.day9

class Sequence(val values: List<Long>) {

    fun findNextValue(): Long {
        if (values.all { it == 0L }) {
            return 0
        }

        return differentiate().findNextValue() + values.last()
    }

    fun findPreviousValue(): Long {
        if (values.all { it == 0L }) {
            return 0
        }

        return values.first() - differentiate().findPreviousValue()
    }

    private fun differentiate(): Sequence {
        val result = mutableListOf<Long>()

        for (i in 1..<values.size) {
            result.add(values[i] - values[i - 1])
        }

        return Sequence(result)
    }

    companion object {
        fun parseFrom(input: String) = Sequence(input.split(" ").map { it.toLong() })
    }
}
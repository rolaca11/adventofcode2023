package io.ropi.adventofcode2023.day7

data class Play(
    val hand: Hand,
    val bid: Int
) {

    override fun toString() = "$hand: $bid -> "

    companion object {
        fun parseFrom(input: String): Play {
            val split = input.split(" ")

            return Play(
                hand = Hand.parseFrom(split[0]),
                bid = split[1].toInt()
            )
        }
    }
}
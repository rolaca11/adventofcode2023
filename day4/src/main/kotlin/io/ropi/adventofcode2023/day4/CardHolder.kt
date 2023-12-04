package io.ropi.adventofcode2023.day4

class CardHolder(private val cards: Map<Int, Card>) {
    fun calculateCardCount(): Int {
        val numberOfHits = cards.map { it.key to it.value.findNumberOfHits() }.sortedBy { it.first }
        val multipliers = mutableMapOf<Int, Int>()
        cards.map { it.key to 1 }.toMap(multipliers)

        for((id, hits) in numberOfHits) {
            for(multipliedId in id+1..id+hits) {
                multipliers.compute(multipliedId) { _, value -> (value?.plus(multipliers[id] ?: 1)) ?: 1 }
            }
        }

        return multipliers.values.sum()
    }
}
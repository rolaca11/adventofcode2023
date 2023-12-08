package io.ropi.adventofcode2023.day7

enum class Combos : Combo {
    HighCard {
        override val value = 1

        override fun isApplicable(hand: Hand): Boolean {
            val labelGroups = hand.cards.groupBy { it.label }

            return labelGroups.size == 5
        }

    },

    OnePair {
        override val value = 2

        override fun isApplicable(hand: Hand): Boolean {
            val labelGroups = hand.cards.groupBy { it.label }

            return labelGroups.size == 4 && labelGroups.values.filter { it.size == 2 }.size == 1
        }
    },

    TwoPair {
        override val value = 3

        override fun isApplicable(hand: Hand): Boolean {
            val labelGroups = hand.cards.groupBy { it.label }

            return labelGroups.size == 3 && labelGroups.values.filter { it.size == 2 }.size == 2
        }

    },

    ThreeOfAKind {
        override val value = 4

        override fun isApplicable(hand: Hand): Boolean {
            val labelGroups = hand.cards.groupBy { it.label }

            return labelGroups.size == 3 && labelGroups.values.filter { it.size == 3 }.size == 1
        }

    },

    FullHouse {
        override val value = 5

        override fun isApplicable(hand: Hand): Boolean {
            val labelGroups = hand.cards.groupBy { it.label }

            return labelGroups.size == 2 && labelGroups.values.filter { it.size == 2 }.size == 1
        }

    },

    FourOfAKind {
        override val value = 6

        override fun isApplicable(hand: Hand): Boolean {
            val labelGroups = hand.cards.groupBy { it.label }

            return labelGroups.size == 2 && labelGroups.values.filter { it.size == 4 }.size == 1
        }

    },

    FiveOfAKind {
        override val value = 7

        override fun isApplicable(hand: Hand): Boolean {
            val labelGroups = hand.cards.groupBy { it.label }

            return labelGroups.size == 1 && labelGroups.values.filter { it.size == 5 }.size == 1
        }

    };
}

interface Combo {
    val value: Int

    fun isApplicable(hand: Hand): Boolean

    fun isApplicableWithJoker(hand: Hand): Boolean {
        return hand.jokerExpandedHands.any { isApplicable(it) }
    }
}

package io.ropi.adventofcode2023.day7

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CombosTest {
    @Test
    fun `test high card combo`() {
        assertThat(Combos.HighCard.isApplicable(Hand.parseFrom("AKTQQ"))).isFalse()
        assertThat(Combos.HighCard.isApplicable(Hand.parseFrom("AKTQ2"))).isTrue()
        assertThat(Combos.HighCard.isApplicableWithJoker(Hand.parseFrom("AKTJJ"))).isTrue()
        assertThat(Combos.HighCard.isApplicableWithJoker(Hand.parseFrom("JJJJJ"))).isTrue()
    }

    @Test
    fun `test one pair combo`() {
        assertThat(Combos.OnePair.isApplicable(Hand.parseFrom("AKTQQ"))).isTrue()
        assertThat(Combos.OnePair.isApplicable(Hand.parseFrom("AKTQ2"))).isFalse()
        assertThat(Combos.OnePair.isApplicableWithJoker(Hand.parseFrom("AKTJJ"))).isTrue()
        assertThat(Combos.OnePair.isApplicableWithJoker(Hand.parseFrom("AKTJ2"))).isTrue()
        assertThat(Combos.OnePair.isApplicableWithJoker(Hand.parseFrom("JJJJJ"))).isTrue()
    }

    @Test
    fun `test two pair combo`() {
        assertThat(Combos.TwoPair.isApplicable(Hand.parseFrom("AKTQQ"))).isFalse()
        assertThat(Combos.TwoPair.isApplicable(Hand.parseFrom("ATTQQ"))).isTrue()
        assertThat(Combos.TwoPair.isApplicable(Hand.parseFrom("AKTQ2"))).isFalse()
        assertThat(Combos.TwoPair.isApplicableWithJoker(Hand.parseFrom("ATTJ2"))).isTrue()
        assertThat(Combos.TwoPair.isApplicableWithJoker(Hand.parseFrom("AKTJ2"))).isFalse()
        assertThat(Combos.TwoPair.isApplicableWithJoker(Hand.parseFrom("JJJJJ"))).isTrue()
    }

    @Test
    fun `test three of a kind combo`() {
        assertThat(Combos.ThreeOfAKind.isApplicable(Hand.parseFrom("AKTQQ"))).isFalse()
        assertThat(Combos.ThreeOfAKind.isApplicable(Hand.parseFrom("ATQQQ"))).isTrue()
        assertThat(Combos.ThreeOfAKind.isApplicable(Hand.parseFrom("AAQQQ"))).isFalse()
        assertThat(Combos.ThreeOfAKind.isApplicable(Hand.parseFrom("AKTQ2"))).isFalse()
        assertThat(Combos.ThreeOfAKind.isApplicableWithJoker(Hand.parseFrom("ATTJ2"))).isTrue()
        assertThat(Combos.ThreeOfAKind.isApplicableWithJoker(Hand.parseFrom("AKTJ2"))).isFalse()
        assertThat(Combos.ThreeOfAKind.isApplicableWithJoker(Hand.parseFrom("JJJJJ"))).isTrue()
    }

    @Test
    fun `test four of a kind combo`() {
        assertThat(Combos.FourOfAKind.isApplicable(Hand.parseFrom("AKTQQ"))).isFalse()
        assertThat(Combos.FourOfAKind.isApplicable(Hand.parseFrom("ATQQQ"))).isFalse()
        assertThat(Combos.FourOfAKind.isApplicable(Hand.parseFrom("AQQQQ"))).isTrue()
        assertThat(Combos.FourOfAKind.isApplicable(Hand.parseFrom("AKTQ2"))).isFalse()
        assertThat(Combos.FourOfAKind.isApplicableWithJoker(Hand.parseFrom("ATTJT"))).isTrue()
        assertThat(Combos.FourOfAKind.isApplicableWithJoker(Hand.parseFrom("ATTJA"))).isFalse()
        assertThat(Combos.FourOfAKind.isApplicableWithJoker(Hand.parseFrom("AKTJ2"))).isFalse()
        assertThat(Combos.FourOfAKind.isApplicableWithJoker(Hand.parseFrom("JJJJJ"))).isTrue()
    }

    @Test
    fun `test five of a kind combo`() {
        assertThat(Combos.FiveOfAKind.isApplicable(Hand.parseFrom("AKTQQ"))).isFalse()
        assertThat(Combos.FiveOfAKind.isApplicable(Hand.parseFrom("ATQQQ"))).isFalse()
        assertThat(Combos.FiveOfAKind.isApplicable(Hand.parseFrom("AQQQQ"))).isFalse()
        assertThat(Combos.FiveOfAKind.isApplicable(Hand.parseFrom("QQQQQ"))).isTrue()
        assertThat(Combos.FiveOfAKind.isApplicable(Hand.parseFrom("AKTQ2"))).isFalse()
        assertThat(Combos.FiveOfAKind.isApplicableWithJoker(Hand.parseFrom("TTTJT"))).isTrue()
        assertThat(Combos.FiveOfAKind.isApplicableWithJoker(Hand.parseFrom("ATTJT"))).isFalse()
        assertThat(Combos.FiveOfAKind.isApplicableWithJoker(Hand.parseFrom("ATTJA"))).isFalse()
        assertThat(Combos.FiveOfAKind.isApplicableWithJoker(Hand.parseFrom("AKTJ2"))).isFalse()
        assertThat(Combos.FiveOfAKind.isApplicableWithJoker(Hand.parseFrom("JJJJJ"))).isTrue()
    }

}
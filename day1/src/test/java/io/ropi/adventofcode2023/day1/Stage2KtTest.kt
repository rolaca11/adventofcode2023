package io.ropi.adventofcode2023.day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested

import org.junit.jupiter.api.Test

class Stage2KtTest {

    @Nested
    inner class IndividualFunctions {
        @Test
        fun `test convert spelled out digits to numbers`() {
            assertThat("onetwothreefourfivesixseveneightnineten".`convert first and last spelled out digits to numbers`())
                .isEqualTo("1twothreefourfivesixseveneight9ten")
        }

        @Test
        fun `test convert spelled out digits to numbers with overlapping spelling`() {
            assertThat("eightwothreeight".`convert first and last spelled out digits to numbers`()).isEqualTo("8wothre8")
        }

        @Test
        fun `test convert spelled out digits to numbers with overlapping spelling 2`() {
            assertThat("nine6nine7seven6".`convert first and last spelled out digits to numbers`()).isEqualTo("96nine7seven6")
        }

        @Test
        fun `test convert spelled out digits to numbers with overlapping spelling 3`() {
            assertThat("five61oneightr".`convert first and last spelled out digits to numbers`()).isEqualTo("561on8r")
        }

        @Test
        fun `test convert spelled out digits to numbers with overlapping spelling 4`() {
            assertThat("loneightseveng79".`convert first and last spelled out digits to numbers`()).isEqualTo("l1ightseveng79")
        }

        @Test
        fun `test convert spelled out digits to numbers with overlapping spelling 5`() {
            assertThat("l5oneight".`convert first and last spelled out digits to numbers`()).isEqualTo("l5on8")
        }

        @Test
        fun `test find earliest occurence of digit`() {
            assertThat("eightwo".`find earliest occurence of spelled digit`()?.first).isEqualTo(Digits.EIGHT)
        }

        @Test
        fun `test find earliest occurence of digit no digits`() {
            assertThat("ufaw1".`find earliest occurence of spelled digit`()).isNull()
        }

        @Test
        fun `test last earliest occurence of digit no digits`() {
            assertThat("7pqrstsixteen".`find last occurence of spelled digit`()?.first).isEqualTo(Digits.SIX)
        }

        @Test
        fun `test replace first spelled digit with number`() {
            assertThat("l5oneight".`replace first spelled digit with number`()).isEqualTo("l5oneight")
        }

        @Test
        fun `test replace last spelled digit with number`() {
            assertThat("l5oneight".`replace last spelled digit with number`()).isEqualTo("l5on8")
        }
    }

    @Nested
    inner class ActualInputs {

        @Test
        fun `example input`() {
            assertThat(this::class.java.classLoader.getResource("stage2-example")?.let { stage2(it.readText()) })
                .isEqualTo(281)
        }

        @Test
        fun `actual input`() {
            assertThat(this::class.java.classLoader.getResource("actual")?.let { stage2(it.readText()) })
                .isEqualTo(55614)
        }
    }
}
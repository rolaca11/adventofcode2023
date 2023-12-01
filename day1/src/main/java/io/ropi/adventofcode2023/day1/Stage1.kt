package io.ropi.adventofcode2023.day1

fun start(input: String): Int =
    input.lines().sumOf { it.`calibration value`().also { value -> println(value) } }

fun String.`calibration value`(): Int =
    (this.`first digit`() * 10) + this.reversed().`first digit`()

fun String.`first digit`(): Int =
    this.find { it.isDigit() }?.digitToIntOrNull() ?: 0
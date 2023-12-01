package io.ropi.adventofcode2023.day1

fun stage1(input: String): Int =
    input.lines().filter { it.isNotBlank() }
        .sumOf { it.`calibration value`() }

fun String.`calibration value`(): Int =
    (this.`first digit`() * 10) + this.reversed().`first digit`()

fun String.`first digit`(): Int =
    this.find { it.isDigit() }?.digitToIntOrNull() ?: throw Error("No Digit found")
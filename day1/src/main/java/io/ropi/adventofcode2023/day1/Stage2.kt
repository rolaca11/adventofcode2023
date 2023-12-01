package io.ropi.adventofcode2023.day1

fun stage2(input: String) = stage1(input.`convert spelled out digits to numbers in line`())

fun String.`convert spelled out digits to numbers in line`() =
    lines().joinToString(separator = "\n") { it.`convert first and last spelled out digits to numbers`() }

fun String.`convert first and last spelled out digits to numbers`(): String {
    return `replace first spelled digit with number`().`replace last spelled digit with number`()
}

fun String.`replace first spelled digit with number`(): String {
    val firstSpelledDigit = `find earliest occurence of spelled digit`()
    val firstDigit = find { it.isDigit() } to indexOfFirst { it.isDigit() }

    return if(firstSpelledDigit != null && (firstSpelledDigit.second < firstDigit.second || firstDigit.second < 0)) {
        replaceFirst(firstSpelledDigit.first.name.lowercase(), firstSpelledDigit.first.value)
    } else {
        this
    }
}

fun String.`replace last spelled digit with number`(): String {
    val firstSpelledDigit = `find last occurence of spelled digit`()
    val firstDigit = reversed().find { it.isDigit() } to reversed().indexOfFirst { it.isDigit() }

    return if(firstSpelledDigit != null && (firstSpelledDigit.second < firstDigit.second || firstDigit.second < 0)) {
        reversed().replaceFirst(firstSpelledDigit.first.name.reversed().lowercase(), firstSpelledDigit.first.value.reversed()).reversed()
    } else {
        this
    }
}

fun String.`find earliest occurence of spelled digit`() =
    Digits.entries
        .map { entry -> entry to this.indexOf(entry.name.lowercase()) }
        .filter { entry -> entry.second >= 0 }
        .reduceOrNull { result, entry ->
            if (result.second < entry.second) result else entry
        }

fun String.`find last occurence of spelled digit`() =
    Digits.entries
        .map { entry -> entry to this.reversed().indexOf(entry.name.reversed().lowercase()) }
        .filter { entry -> entry.second >= 0 }
        .reduceOrNull { result, entry ->
            if (result.second < entry.second) result else entry
        }

enum class Digits(val value: String) {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9")
}
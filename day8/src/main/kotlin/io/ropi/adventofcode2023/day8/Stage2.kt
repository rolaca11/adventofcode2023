package io.ropi.adventofcode2023.day8

import java.math.BigInteger

fun stage2(input: String): BigInteger {
    val split = input.split("\n\n")
    val nodeMap = Map.parseNodeMap(split[1])


    return nodeMap.keys.filter { it.endsWith("Z") }
        .map { Map.parseFrom(input, it).stepsToGetEndingNode().size.toBigInteger() }
        .reduce { a, b -> lcm(a, b) }
}

fun lcm(a: BigInteger, b: BigInteger): BigInteger {
    val larger = if (a > b) a else b
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm % a == BigInteger.ZERO && lcm % b == BigInteger.ZERO) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm
}
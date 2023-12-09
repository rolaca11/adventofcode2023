package io.ropi.adventofcode2023.day8

fun stage1(input: String) = Map.parseFrom(input, "AAA").stepsToGetEndingNode().size

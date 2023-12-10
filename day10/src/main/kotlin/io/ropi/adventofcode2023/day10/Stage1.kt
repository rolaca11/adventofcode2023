package io.ropi.adventofcode2023.day10

fun stage1(input: String) = Grid.parseFrom(input).tileDistanceMap()
    .values.max()
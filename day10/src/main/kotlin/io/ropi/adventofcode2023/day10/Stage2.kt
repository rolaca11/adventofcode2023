package io.ropi.adventofcode2023.day10

fun stage2(input: String) = Grid.parseFrom(input).groupTilesByComponent().filterNot { it.isMainLoop }
    .filterNot { it.touchesEdge }
    .filterNot { it.canLeak() }
    .sumOf { it.tiles.size }
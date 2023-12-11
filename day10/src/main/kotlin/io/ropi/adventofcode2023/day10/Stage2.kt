package io.ropi.adventofcode2023.day10

fun stage2(input: String) = Grid.parseFrom(input).groupTilesByComponent().filterNot { it.isMainLoop }
    .filterNot { it.touchesEdge }
    .parallelStream()
    .filter { component ->
        println("CHECKING COMPONENT")
        !component.canLeak()
    }.toList()
    .sumOf { it.tiles.size }
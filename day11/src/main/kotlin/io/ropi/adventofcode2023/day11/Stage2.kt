package io.ropi.adventofcode2023.day11

import kotlin.math.absoluteValue

fun stage2(input: String, expansionFactor: Int = 1000000) =
    Sky.parseFrom(input).expanded(expansionFactor).findTiles { it is Tile.Galaxy }
        .pairItems()
        .map { pair -> pair.second.position - pair.first.position }
        .sumOf { it.x.absoluteValue.toLong() + it.y.absoluteValue.toLong() }
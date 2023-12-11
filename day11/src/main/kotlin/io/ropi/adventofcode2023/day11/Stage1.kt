package io.ropi.adventofcode2023.day11

import kotlin.math.absoluteValue

fun stage1(input: String) = Sky.parseFrom(input).expanded.let { sky ->
    sky.findTiles { it is Tile.Galaxy }.pairItems()
}.map { pair -> pair.second.position - pair.first.position }
    .sumOf { it.x.absoluteValue + it.y.absoluteValue }

fun <T> List<T>.pairItems(): List<Pair<T, T>> {
    val result = mutableListOf<Pair<T, T>>()

    for (i in indices) {
        for (j in i + 1..<size) {
            result += Pair(this[i], this[j])
        }
    }

    return result
}
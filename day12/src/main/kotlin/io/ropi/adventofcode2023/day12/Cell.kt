package io.ropi.adventofcode2023.day12

sealed class Cell {
    data object Spring : Cell()
    data object Empty: Cell()
    data object Unknown: Cell()
}
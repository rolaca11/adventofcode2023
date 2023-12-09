package io.ropi.adventofcode2023.day8

class Map(
    val startingNode: Node,
    val endingNode: Node,
    val directions: List<Direction>
) {
    fun stepsToGetEndingNode(): List<Direction> {
        val result = mutableListOf<Direction>()

        var currentNode = startingNode
        for (direction in directions.asSequence().repeat()) {
            currentNode = direction.next(currentNode)
            result.add(direction)

            if (currentNode == endingNode) {
                break
            }
        }

        return result
    }

    class Node(var left: Node? = null, var right: Node? = null)

    enum class Direction(val label: Char, val next: Node.() -> Node) {
        LEFT('L', { this.left ?: throw Error() }),
        RIGHT('R', { this.right ?: throw Error() })
    }

    private fun <T> Sequence<T>.repeat() = sequence { while (true) yieldAll(this@repeat) }

    companion object {
        private val REGEX = Regex("([A-Z]+) = \\(([A-Z]+), ([A-Z]+)\\)")
        fun parseFrom(input: String): Map {
            val split = input.split("\n\n")

            val directions = split[0].map { Direction.entries.find { direction -> direction.label == it }!! }
            val nodeMap = split[1].lines().map { REGEX.matchEntire(it)!!.groupValues[1] to Node() }.toMap()

            split[1].lines().map { REGEX.matchEntire(it)!!.groupValues }
                .map { nodeMap[it[1]] to (nodeMap[it[2]] to nodeMap[it[3]]) }
                .forEach {
                    it.first?.left = it.second.first
                    it.first?.right = it.second.second
                }

            return Map(
                startingNode = nodeMap["AAA"]!!,
                endingNode = nodeMap["ZZZ"]!!,
                directions = directions
            )
        }
    }
}
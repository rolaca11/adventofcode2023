package io.ropi.adventofcode2023.day8

class Map(
    val startingNode: List<Node>,
    val directions: List<Direction>
) {
    fun stepsToGetEndingNode(): List<Direction> {
        val result = mutableListOf<Direction>()

        var currentNodes = startingNode
        for (direction in directions.asSequence().repeat()) {
            currentNodes = currentNodes.map { direction.next(it) }
            result.add(direction)

            if (currentNodes.all { it.label.endsWith("Z") }) {
                break
            }
        }

        return result
    }

    class Node(val label: String = "", var left: Node? = null, var right: Node? = null) {
        override fun toString() = label
    }

    enum class Direction(val label: Char, val next: Node.() -> Node) {
        LEFT('L', { this.left ?: throw Error() }),
        RIGHT('R', { this.right ?: throw Error() })
    }

    private fun <T> Sequence<T>.repeat() = sequence { while (true) yieldAll(this@repeat) }

    companion object {
        private val REGEX = Regex("([0-9A-Z]+) = \\(([0-9A-Z]+), ([0-9A-Z]+)\\)")
        fun parseFrom(input: String, startingNodeLabel: String): Map {
            val split = input.split("\n\n")
            val directions = parseDirections(split[0])
            val nodeMap = parseNodeMap(split[1])

            return Map(
                startingNode = listOf(nodeMap[startingNodeLabel]!!),
                directions = directions
            )
        }

        fun parseDirections(input: String): List<Direction> {
            return input.map { Direction.entries.find { direction -> direction.label == it }!! }
        }

        fun parseNodeMap(input: String): kotlin.collections.Map<String, Node> {
            val nodeMap = input.lines()
                .associate { REGEX.matchEntire(it)!!.groupValues[1] to Node(REGEX.matchEntire(it)!!.groupValues[1]) }

            input.lines().map { REGEX.matchEntire(it)!!.groupValues }
                .map { nodeMap[it[1]] to (nodeMap[it[2]] to nodeMap[it[3]]) }
                .forEach {
                    it.first?.left = it.second.first
                    it.first?.right = it.second.second
                }
            return nodeMap
        }
    }
}
package io.ropi.adventofcode2023.day6

class Race(private val runs: List<Run>) {

    fun listNumOfWaysToWin() = runs.map { it.numberOfWaysToBeatRecord }

    companion object {
        fun parseFrom(input: String): Race {
            val lines = input.split("\n")

            val raceLengths = lines[0].split(regex = Regex("\\s+")).drop(1)
                .map { it.toDouble() }
            val records = lines[1].split(regex = Regex("\\s+")).drop(1)
                .map { it.toDouble() }

            val runs = mutableListOf<Run>()
            for(i in raceLengths.indices) {
                runs.add(i, Run(
                    raceLength = raceLengths[i],
                    recordDistance = records[i]
                ))
            }

            return Race(runs)
        }

        fun parseFromStage2(input: String): Race {
            val lines = input.split("\n")

            val raceLengths = lines[0].split(regex = Regex(":")).drop(1)
                .map { it.trim().replace(" ", "") }
                .map { it.toDouble() }
            val records = lines[1].split(regex = Regex(":")).drop(1)
                .map { it.trim().replace(" ", "") }
                .map { it.toDouble() }

            val runs = mutableListOf<Run>()
            for(i in raceLengths.indices) {
                runs.add(i, Run(
                    raceLength = raceLengths[i],
                    recordDistance = records[i]
                ))
            }

            return Race(runs)
        }
    }
}
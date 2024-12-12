package adventofcode2024.day11

import adventofcode2024.readInput
import adventofcode2024.runPuzzle

fun main() {
    val input = readInput("day11.input")

    runPuzzle(1) {
        puzzle1(input.first(), 25)
    }

    runPuzzle(1) {
        puzzle1(input.first(), 75)
    }
}

fun parseInput(input: List<String>): List<String> {
    return input.first().split(" ")
}

fun transform(input: List<String>): List<String> {
    fun rules(s: String): List<String> {
        if (s == "0") return listOf("1")
        if (s.length % 2 == 0) return listOf(
            "${s.substring(0, s.length / 2).toBigDecimal()}",
            "${s.substring(s.length / 2).toBigDecimal()}")
        return listOf("${s.toBigDecimal() * 2024.toBigDecimal()}")
    }
    return input.fold(mutableListOf()) { acc, s ->
        acc.addAll(rules(s))
        acc
    }
}

fun puzzle1(input: String, counter: Int): Int {
    var sequence = input.split(" ")
    repeat(counter) {
        val newSequence = transform(sequence)
        sequence = newSequence
    }
    return sequence.size
}
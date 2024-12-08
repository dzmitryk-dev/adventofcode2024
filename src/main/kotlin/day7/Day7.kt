package adventofcode2023.day7

import adventofcode2023.readInput
import kotlin.time.measureTimedValue

fun main() {
    val equations = parseInput(readInput("day7.input"))

    println("Puzzle 1 started")
    val (output, duration) = measureTimedValue {
        puzzle1(equations)
    }
    println("Puzzle 1 output: $output. Done in ${duration.inWholeMilliseconds} ms" )

}

fun parseInput(input: List<String>): List<Pair<Long, List<Long>>> {
    return input.map { line ->
        line.split(":").let { (result, numbers) ->
            result.toLong() to numbers.split(" ").filter { it.isNotEmpty() }.map { it.toLong() }
        }
    }
}

fun tryFindSolution(result: Long, numbers: List<Long>): Boolean {
    tailrec fun calculateRecursive(numbers: List<Long>, intermediateResults: Set<Long> = setOf(0L)): Set<Long> {
        if(numbers.isEmpty()) return intermediateResults
        val number = numbers.first()
        val sumResults = intermediateResults.map { it + number }
        val multResults = intermediateResults.map { it * number }
        return calculateRecursive(numbers.drop(1), (sumResults + multResults).toSet())
    }
    return calculateRecursive(numbers).contains(result)
}

fun puzzle1(equations: List<Pair<Long, List<Long>>>): Long {
    return equations.filter { (result, numbers) -> tryFindSolution(result, numbers) }.sumOf { (result, _) -> result }
}
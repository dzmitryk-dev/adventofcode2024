package adventofcode2023.day5

import adventofcode2023.readInput
import kotlin.time.measureTimedValue

fun main() {
    val input = readInput("day5.input")
    val (rules, updates) = parseInput(input)

    println("Puzzle 1 started")
    val (output, duration) = measureTimedValue {
        puzzle1(rules, updates)
    }
    println("Puzzle 1 output: $output. Done in ${duration.inWholeMilliseconds} ms" )
}

fun parseInput(input: List<String>): Pair<List<Pair<Int, Int>>, List<List<Int>>> {
    val delimeterIndex = input.indexOf("")
    val rules = input.subList(0, delimeterIndex).map { str ->
        str.split("|").map { it.toInt() }.let { (a, b) -> a to b }
    }
    val updates = input.subList(delimeterIndex + 1, input.size).map { str ->
        str.split(",").map { it.toInt() }
    }
    return Pair(rules, updates)
}

fun testUpdate(rules: List<Pair<Int, Int>>, updates: List<Int>): Boolean {
    for ( (index, v) in updates.withIndex()) {
        val afterPages = rules.filter { (f, _) -> f == v }.map { (_, s) -> s }
        if (afterPages.isEmpty() && index == 0) {
            continue
        }
        if (updates.subList(0, index).any { it in afterPages }) {
            return false
        }
    }
    return true
}

fun puzzle1(rules: List<Pair<Int, Int>>, updates: List<List<Int>>): Int {
    return updates.filter { testUpdate(rules, it) }
        .sumOf { it[it.size / 2] }
}
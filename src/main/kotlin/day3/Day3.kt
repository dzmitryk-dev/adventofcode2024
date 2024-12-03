package adventofcode2023.day3

import adventofcode2023.readInput
import kotlin.time.measureTimedValue

fun main() {
    val input = readInput("day3.input")

    println("Puzzle 1")
    val (output, duration) =  measureTimedValue {
        puzzle1(input.joinToString { it })
    }
    println("Puzzle 1 output: $output. Done in ${duration.inWholeMilliseconds} ms")
}


fun cleanUpInput(input: String): List<String> {
    // Updated regex to match "mul(x,y)" with flexible delimiters
    val regex = Regex("""mul\(\d+,\d+\)""")
    return regex.findAll(input).map { it.value }.toList()
}

fun mul(input: String): Int {
    val regex = Regex("""mul\((\d+),(\d+)\)""")
    val (a, b) = regex.find(input)!!.destructured
    return a.toInt() * b.toInt()
}


fun puzzle1(input: String): Int {
    return cleanUpInput(input).sumOf { mul(it) }
}
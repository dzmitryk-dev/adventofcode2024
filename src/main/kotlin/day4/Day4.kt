package adventofcode2023.day4

import adventofcode2023.Point
import adventofcode2023.col
import adventofcode2023.line
import adventofcode2023.readInput
import kotlin.time.measureTimedValue

fun main() {
    val input = parseInput(readInput("day4.input"))

    println("Puzzle 1")
    val (output, duration) =  measureTimedValue {
       findXMAS(input)
    }
    println("Puzzle 1 output: $output. Done in ${duration.inWholeMilliseconds} ms")
}

fun parseInput(input: List<String>): List<CharArray> {
    return input.map { it.toCharArray() }
}

fun findX(input: List<CharArray>): List<Point> {
    return buildList {
        input.forEachIndexed { lineIndex, line ->
            line.forEachIndexed { charIndex, char ->
                if (char == 'X') {
                    add(Point(lineIndex, charIndex))
                }
            }
        }
    }
}

fun findNextSymbol(input: List<CharArray>, point: Point, direction: Pair<Int, Int>, symbols: String): Boolean {
    if (symbols.isEmpty()) {
        return true
    }
    return try {
        val actualPoint = Point(point.line + direction.first, point.col + direction.second)
        val actual = input[actualPoint.line][actualPoint.col]
        val expected = symbols.first()
        if (actual == expected) {
            findNextSymbol(input, actualPoint, direction, symbols.substring(1))
        } else {
            false
        }
    } catch (ex: IndexOutOfBoundsException) {
        false
    }
}

fun findXMAS(input: List<CharArray>): Int {
    val directions = listOf(0 to -1, -1 to -1, -1 to 0, -1 to 1, 0 to 1, 1 to 1, 1 to 0, 1 to -1)
    var count = 0
    findX(input).forEach { point ->
        directions.forEach { direction ->
            if(findNextSymbol(input, point, direction, "MAS")) {
                count++
            }
        }
    }
    return count
}

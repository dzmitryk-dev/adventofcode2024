package adventofcode2023.day9

import adventofcode2023.readInput
import kotlin.time.measureTimedValue

fun main() {
    val input = readInput("day9.input").first()

    println("Puzzle 1 started")
    val (output, duration) = measureTimedValue {
        calculateCheckSum(moveBlocks(unpackDiskMap(input)))
    }
    println("Puzzle 1 output: $output. Done in ${duration.inWholeMilliseconds} ms" )
}

fun unpackDiskMap(input: String): List<Int> {
    val diskMap = mutableListOf<Int>()

    input.foldIndexed(diskMap) { index, acc, c ->
        val value = c.toString().toInt()
        val id = if (index % 2 == 0) {
            index / 2
        } else {
           -1
        }
        repeat(value) {
            acc.add(id)
        }
        acc
    }

    return diskMap
}

fun moveBlocks(input: List<Int>): List<Int> {
    val list = input.toMutableList()

    var empty = list.indexOfFirst { it == -1 }
    var data = list.lastIndex

    while (empty < data) {
        list[empty] = list[data]
        list[data] = -1

        empty = list.indexOfFirst { it == -1 }
        do {
            data--
        } while (list[data] == -1 && data > 0)
    }
    return list
}

fun calculateCheckSum(input: List<Int>): Long {
    return input.map { if (it > 0) it else 0 }
        .mapIndexed { index, i -> index * i.toLong() }.sum()
}
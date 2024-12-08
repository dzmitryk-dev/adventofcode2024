package adventofcode2023.day6

import adventofcode2023.Point
import adventofcode2023.col
import adventofcode2023.line
import adventofcode2023.readInput
import kotlin.time.measureTimedValue

fun main() {
    val input = readInput("day6.input")

    println("Puzzle 1 started")
    val (output, duration) = measureTimedValue {
        puzzle1(input)
    }
    println("Puzzle 1 output: $output. Done in ${duration.inWholeMilliseconds} ms" )
}

data class Guard(
    val cord: Point,
    val direction: Direction,
) {
    enum class Direction {
        UP, DOWN, LEFT, RIGHT;

        fun next(): Direction {
            return when (this) {
                UP -> RIGHT
                RIGHT -> DOWN
                DOWN -> LEFT
                LEFT -> UP
            }
        }
    }
}

data class Scene(
    val scene: List<CharArray>,
    val guard: Guard,
)

fun parseScene(input: List<String>): Scene {
    return Scene(
        scene = input.map { it.toCharArray() },
        guard = Guard(
            cord = run {
                for ( (lineIndex, line) in input.withIndex()) {
                    val charIndex = line.indexOf('^')
                    if (charIndex > -1) {
                        return@run Point(lineIndex, charIndex)
                    }
                }
                throw IllegalStateException("No guard found")
            },
            direction = Guard.Direction.UP,
        )
    )
}

fun Guard.walk(
    field: List<CharArray>,
): Guard? {
    val newPoint = when(direction) {
        Guard.Direction.UP -> cord.copy(first = cord.line - 1)
        Guard.Direction.DOWN -> cord.copy(first = cord.line + 1)
        Guard.Direction.LEFT -> cord.copy(second = cord.col - 1)
        Guard.Direction.RIGHT -> cord.copy(second = cord.col + 1)
    }
    return try {
        val content = field[newPoint.line][newPoint.col]

        if (content == '#') {
            copy(direction = direction.next())
        } else {
            copy(cord = newPoint)
        }
    } catch (e: IndexOutOfBoundsException) {
        null
    }
}

fun calculateRoute(scene: Scene): Set<Point> {
    var guard = scene.guard

    val route = mutableSetOf(guard.cord)

    do {
        val newGuard = guard.walk(scene.scene)
        if (newGuard != null) {
            route.add(newGuard.cord)
            guard = newGuard
        }
    } while (newGuard != null)

    return route
}

fun puzzle1(input: List<String>): Int {
    val scene = parseScene(input)
    val route = calculateRoute(scene)
    return route.size
}
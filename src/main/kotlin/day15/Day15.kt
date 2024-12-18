package adventofcode2024.day15

import adventofcode2024.*


fun main() {
    val input = readInput("day15.input")

    runPuzzle(1) {
        part1(input)
    }
}

data class Scene(
    val size: Pair<Int, Int>,
    val items: Map<Point, Item>,
    val robot: Point,
) {
    enum class Item {
        WALL, BOX
    }
}

data class Command(
    val direction: Direction,
) {
    enum class Direction {
        UP, DOWN, LEFT, RIGHT
    }
}

fun parseInput(input: List<String>): Pair<Scene, List<Command>> {
    val separator = input.indexOfFirst { it.isEmpty() }

    val scene = input.subList(0, separator).let { sceneInput ->
        parseScene(sceneInput)
    }

    val commands = input.subList(separator + 1, input.size).let { commandsInput ->
        commandsInput.joinToString("").map {
            Command(
                when (it) {
                    '^' -> Command.Direction.UP
                    'v' -> Command.Direction.DOWN
                    '<' -> Command.Direction.LEFT
                    '>' -> Command.Direction.RIGHT
                    else -> error("Invalid command: $it")
                }
            )
        }
    }

    return scene to commands
}

fun parseScene(sceneInput: List<String>): Scene {
    val height = sceneInput.size
    val width = sceneInput.maxOf { it.length }

    lateinit var robot: Point

    val items: Map<Point, Scene.Item> = buildMap {
        sceneInput.forEachIndexed { line, symbols ->
            symbols.forEachIndexed { col, c ->
                when (c) {
                    '@' -> robot = Point(line, col)
                    '#' -> put(Point(line, col), Scene.Item.WALL)
                    'O' -> put(Point(line, col), Scene.Item.BOX)
                }
            }
        }
    }

    return Scene(Pair(width, height), items, robot)
}

fun visualize(scene: Scene): String {
    val (width, height) = scene.size
    val field = Array(height) { CharArray(width) { '.' } }

    scene.items.forEach { (position, item) ->
        val (line, col) = position
        field[line][col]  = when (item) {
            Scene.Item.WALL -> '#'
            Scene.Item.BOX ->  'O'
        }
    }
    scene.robot.let { (line, col) ->
        field[line][col] = '@'
    }

    return field.joinToString("\n") { it.joinToString("") }
}

fun executeCommand(scene: Scene, command: Command): Scene {
    fun Point.nextPoint(direction: Command.Direction) = when (direction) {
        Command.Direction.UP -> Point(line - 1, col)
        Command.Direction.DOWN -> Point(line + 1, col)
        Command.Direction.LEFT -> Point(line, col - 1)
        Command.Direction.RIGHT -> Point(line, col + 1)
    }

    val newRobot = scene.robot.nextPoint(command.direction)

    return when (scene.items[newRobot]) {
        Scene.Item.WALL -> scene
        Scene.Item.BOX -> {
            fun moveBox(p: Point, items: Map<Point, Scene.Item>): Map<Point, Scene.Item>? {
                val nextPoint = p.nextPoint(command.direction)
                return when (items[nextPoint]) {
                    Scene.Item.WALL -> null
                    Scene.Item.BOX -> moveBox(nextPoint, items)
                    null -> items
                }?.let { it + (nextPoint to Scene.Item.BOX) - p }
            }
            moveBox(newRobot, scene.items)?.let { newItems ->
                scene.copy(robot = newRobot, items = newItems)
            } ?: scene
        }
        null -> scene.copy(robot = newRobot)
    }
}

fun executeCommands(scene: Scene, commands: List<Command>): Scene {
    return commands.fold(scene) { acc, command ->
        executeCommand(acc, command)
    }
}

fun calculateGPS(scene: Scene): Int {
    return scene.items.filter { it.value == Scene.Item.BOX }
        .keys.sumOf { (line, col) ->
            line * 100 + col
        }
}

fun part1(input: List<String>): Int {
    val (scene, commands) = parseInput(input)
    val finalScene = executeCommands(scene, commands)
    return calculateGPS(finalScene)
}
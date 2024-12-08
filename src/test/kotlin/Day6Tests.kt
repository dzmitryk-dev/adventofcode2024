import adventofcode2023.Point
import adventofcode2023.day6.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day6Tests {

    private val testInput = """
        ....#.....
        .........#
        ..........
        ..#.......
        .......#..
        ..........
        .#..^.....
        ........#.
        #.........
        ......#..
    """.trimIndent()

    @Test
    fun testParseScene() {
        val expectedScene = Scene(
            scene = listOf(
                "....#.....",
                ".........#",
                "..........",
                "..#.......",
                ".......#..",
                "..........",
                ".#..^.....",
                "........#.",
                "#.........",
                "......#.."
            ).map { it.toCharArray() },
            guard = Guard(
                cord = Point(6, 4),
                direction = Guard.Direction.UP,
            )
        )

        val scene = parseScene(testInput.lines())
        assertThat(scene.scene).containsExactlyElementsOf(expectedScene.scene)
        assertThat(scene.guard).isEqualTo(expectedScene.guard)
    }

    @Test
    fun testPuzzle() {
        val result = puzzle1(testInput.lines())

        assertThat(result).isEqualTo(41)
    }
}
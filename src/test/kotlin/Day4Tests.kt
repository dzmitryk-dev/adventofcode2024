import adventofcode2023.day4.findXMAS
import adventofcode2023.day4.parseInput
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day4Tests {

    val testInput1 = """
        ..X...
        .SAMX.
        .A..A.
        XMAS.S
        .X....
    """.trimIndent()

    val testInput2 = """
        ....XXMAS.
        .SAMXMS...
        ...S..A...
        ..A.A.MS.X
        XMASAMX.MM
        X.....XA.A
        S.S.S.S.SS
        .A.A.A.A.A
        ..M.M.M.MM
        .X.X.XMASX
    """.trimIndent()

    val testInput3 = """
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX
    """.trimIndent()


    @Test
    fun testFindXMASonInput1() {
        val actual = findXMAS(parseInput(testInput1.lines()))

        assertThat(actual).isEqualTo(4)
    }

    @Test
    fun testFindXMASonInput2() {
        val actual = findXMAS(parseInput(testInput2.lines()))

        assertThat(actual).isEqualTo(18)
    }

    @Test
    fun testFindXMASonInput3() {
        val actual = findXMAS(parseInput(testInput3.lines()))

        assertThat(actual).isEqualTo(18)
    }
}
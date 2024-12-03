import adventofcode2023.day3.cleanUpInput
import adventofcode2023.day3.mul
import adventofcode2023.day3.puzzle1
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day3Tests {

    private val testInput = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"

    @Test
    fun testCleanUpInput() {
        val result = cleanUpInput(testInput)

        assertThat(result).containsExactly(
            "mul(2,4)",
            "mul(5,5)",
            "mul(11,8)",
            "mul(8,5)"
        )
    }

    @ParameterizedTest
    @MethodSource("mulTestCases")
    fun testMul(testCase: MulTestCase) {
        assertThat(mul(testCase.input)).isEqualTo(testCase.expected)
    }

    @Test
    fun testPuzzle1() {

        val result = puzzle1(testInput)

        assertThat(result).isEqualTo(161)
    }

    companion object {

        data class MulTestCase(val input: String, val expected: Int)

        @JvmStatic
        fun mulTestCases(): Stream<MulTestCase> = Stream.of(
            MulTestCase("mul(2,4)", 8),
            MulTestCase("mul(5,5)", 25),
            MulTestCase("mul(11,8)", 88),
            MulTestCase("mul(8,5)", 40)
        )
    }

}
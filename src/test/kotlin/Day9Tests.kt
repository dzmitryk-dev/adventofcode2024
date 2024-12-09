import adventofcode2023.day9.calculateCheckSum
import adventofcode2023.day9.moveBlocks
import adventofcode2023.day9.unpackDiskMap
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.Test

class Day9Tests {

    @ParameterizedTest
    @MethodSource("unpackSourceProvider")
    fun testUnpackDiskMap(testCase: Pair<String, List<Int>>) {
        val result = unpackDiskMap(testCase.first)

        assertThat(result).containsExactlyElementsOf(testCase.second)
    }

    @ParameterizedTest
    @MethodSource("moveBlocksSourceProvider")
    fun testMoveBlocks(testCase: Pair<List<Int>, List<Int>>) {
        val result = moveBlocks(testCase.first)

        assertThat(result).containsExactlyElementsOf(testCase.second)
    }

    @Test
    fun testCalculateCheckSum() {
        val result = calculateCheckSum(listOf(0,0,9,9,8,1,1,1,8,8,8,2,7,7,7,3,3,3,6,4,4,6,5,5,5,5,6,6,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1))

        assertThat(result).isEqualTo(1928L)
    }

    companion object {

        @JvmStatic
        fun unpackSourceProvider():Stream<Pair<String, List<Int>>> = Stream.of(
            "12345" to listOf(0,-1,-1,1,1,1,-1,-1,-1,-1,2,2,2,2,2),
            "2333133121414131402" to listOf(0,0,-1,-1,-1,1,1,1,-1,-1,-1,2,-1,-1,-1,3,3,3,-1,4,4,-1,5,5,5,5,-1,6,6,6,6,-1,7,7,7,-1,8,8,8,8,9,9)
        )

        @JvmStatic
        fun moveBlocksSourceProvider():Stream<Pair<List<Int>, List<Int>>> = Stream.of(
            listOf(0,-1,-1,1,1,1,-1,-1,-1,-1,2,2,2,2,2) to listOf(0,2,2,1,1,1,2,2,2,-1,-1,-1,-1,-1,-1),
            listOf(0,0,-1,-1,-1,1,1,1,-1,-1,-1,2,-1,-1,-1,3,3,3,-1,4,4,-1,5,5,5,5,-1,6,6,6,6,-1,7,7,7,-1,8,8,8,8,9,9) to
            listOf(0,0,9,9,8,1,1,1,8,8,8,2,7,7,7,3,3,3,6,4,4,6,5,5,5,5,6,6,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1),
        )
    }
}
package day11

import org.junit.Assert.*
import org.junit.Test

class Day11KtTest {
    @Test
    fun testReadingOctopuses() {
        val octopuses = readOctopuses("src/test/resources/day11_test.txt")

        assertEquals(5,octopuses[0][0].energy)
        assertEquals(6,octopuses[9][9].energy)
        assertEquals(3,octopuses[4][4].energy)

    }

    @Test
    fun testReadingOctopusesDump() {
        val octopuses = readOctopuses("src/test/resources/day11_test.txt")

        assertEquals("""
            5483143223
            2745854711
            5264556173
            6141336146
            6357385478
            4167524645
            2176841721
            6882881134
            4846848554
            5283751526
        """.trimIndent(),
            octopuses.dump()
            )
    }

    @Test
    fun testReadFromStringAndDump() {
        val input = """
            5432
            1111
            0882
            0144
        """.trimIndent()

        val octopuses = readOctopusesFromString(input)

        assertEquals("""
                5432
                1111
                0882
                0144
            """.trimIndent(),
        octopuses.dump()
        )
    }
}
package day1

import readInputAsInts

fun main() {
    /* Puzzle: Figure out how quickly the depth increases
      Count the number of times a depth measurement increases from
     the previous measurement.
     Compare each number to the previous one. Compare
     Compare the second to the first, the third to the second
     the fourth to the third...
    */

    fun part1(input: List<Int>): Int {
        return input.windowed(2).count { (a,b) -> a < b }
    }

    // A + B + C <=> B + C + D
    fun part2(input: List<Int>): Int {
// solution 1:    return input.windowed(3).windowed(2).count {
//                (a,b) -> a.sum() < b.sum()
// solution 2: subtract B + C from both sides of the equation.
        // now you can compare A and D.
        val answer: Int = input.windowed(4).count { it[0] < it[3] }
        return answer
    }

    val input = readInputAsInts("Day01")
    println(part1(input))
    println(part2(input))
}

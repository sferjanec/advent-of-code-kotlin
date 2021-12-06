package day3

import java.io.File
import java.lang.IllegalStateException

data class Diagnostic(
    var zero: Int = 0,
    var one: Int =0
)

fun part1() {
    val lines = File("src/resources","Day3.txt").readLines()

    //instead of manually adding to a List of Count objects
    //we can generate a sequence of List of 12 Count objects
    //to be populated with count of zeros and ones.
    val counts: List<Diagnostic> = generateSequence { Diagnostic()
    }.take(12).toList(  )

    lines.forEach { line ->
        line.forEachIndexed { index, character ->
            when (character) {
                '0' -> counts[index].zero++
                '1' -> counts[index].one++
            }
        }
    }

    val gamma = counts.joinToString("") { count ->
        if (count.one > count.zero) {
            "1"
        } else if (count.zero > count.one) {
            "0"
        } else {
            throw IllegalStateException("No most common bit")
        }
    }

    val epsilon = gamma.map {
        character ->
            when (character) {
                '0' -> '1'
                '1' -> '0'
                else -> throw IllegalStateException("Unknown character: $character")
            }
    }.joinToString("")


    val gammaRate = Integer.parseInt(gamma, 2)
    val epsilonRate = Integer.parseInt(epsilon, 2)
    println("gamma: $gamma -> $gammaRate")
    println("epsilon: $epsilon -> $epsilonRate")
    println("solution: ${gammaRate * epsilonRate}")

}

fun main() {
    part1()
}
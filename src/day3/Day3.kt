package day3

import java.io.File
import java.lang.IllegalStateException

data class Count(
    var zero: Int = 0,
    var one: Int =0
)

fun part1() {
    val lines = File("src/resources","Day3.txt").readLines()

    //instead of manually adding to a List of Count objects
    //we can generate a sequence of List of 12 Count objects
    //to be populated with count of zeros and ones.
    val counts: List<Count> = generateSequence { Count()
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


//list of numbers
//look at first bit position of each number
//For each position, decide whether there are more 1s or 0s in that position
//Keep only those numbers that have that bit in that position
//repeat the process for each subsequent bit until there are no numbers remaining
//1) Find the count of 1s or 0s for a given position
fun findCount(values: List<String>, position: Int): Count {
    val count = Count()

    values.forEach { line ->
        val character = line[position]
        when (character) {
            '0' -> count.zero++
            '1' -> count.one++
        }
    }
    return count
}

fun findRating(
    values: List<String>,
    position: Int,
    filter: (String, Int, Count) -> Boolean
): String {

    val count = findCount(values, position)
    val filteredValues = values.filter { value ->
        filter(value, position, count)
    }
    return if (filteredValues.size ==1) {
        filteredValues[0]
    } else if (filteredValues.size > 1) {
        findRating(filteredValues, position + 1, filter)
    } else {
        throw IllegalStateException("Count not find values")
    }
}

fun findOxygenBit(count: Count): Char {
    return if (count.one > count.zero) {
        '1'
    } else if (count.zero > count.one) {
        '0'
    } else {
        '1'
    }
}

fun findCo2Bit(count: Count): Char {
    return if (count.one < count.zero) {
        '1'
    } else if (count.zero < count.one) {
        '0'
    } else {
        '0'
    }
}

fun part2() {
    val values = File("src/resources","Day3.txt").readLines()

    val rawOxygenRating = findRating(values, 0) { value, position, count ->
        val valueAtPosition = value[position]
        valueAtPosition == findOxygenBit (count)
    }
    val oxygenRating = Integer.parseInt(rawOxygenRating, 2)
    println("oxygen: $rawOxygenRating -> $oxygenRating")

    val rawCo2Rating = findRating(values, 0) { value, position, count ->
        val valueAtPosition = value[position]
        valueAtPosition == findCo2Bit(count)
    }
    val co2Rating = Integer.parseInt(rawCo2Rating, 2)
    println("co2: $rawCo2Rating -> $co2Rating")

    println("Solution: ${oxygenRating * co2Rating}")
}

fun main() {
   // part1()
    part2()
}
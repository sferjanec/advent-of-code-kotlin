package day2

import readInput

fun main() {
    /* Puzzle: Calculate horizontal position and depth
        Each input is delimited by name-value pair
        key: horizontal : value -> add
        Key: depth: value -> either add or subtract
        Solution: Multiply horizontal by depth
        include aim
    */

    fun part1(input: List<String>): Int {
        var horizontal: Int = 0
        var depth: Int = 0
        input.forEach {
           val (left, right) = it.split(" ")
           when (left) {
               "forward" -> horizontal += right.toInt()
               "up" -> depth -= right.toInt()
               "down" -> depth += right.toInt()
           }
        }
        return (horizontal * depth)
    }

    data class Operation(val direction: String, val amount: Int)

    fun part2(input: List<String>): Int {
        var aim: Int = 0
        var depth: Int = 0
        var horizontal: Int = 0
        val operations: List<Operation> = input.map { it.split( ' ')}.map { Operation(it[0], it[1].toInt())}
        for ((direction, amount) in operations) {
            when(direction) {
                "up" -> aim -= amount
                "down" -> aim += amount
                "forward" ->  {
                    horizontal += amount
                    depth += (amount * aim)
                }
            }
        }
        return (horizontal * depth)
    }

    // test if implementation meets criteria from the description, like:
    //val testInput = readInput("Day01_test")
    //check(day3.part1(testInput) == 1)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

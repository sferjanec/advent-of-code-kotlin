package day3

import java.io.File

class Day3Alt(private val input: List<String>) {

    fun solvePart1(): Int {
        val gamma = input.first().indices.map { //select first item in list. map all elements of the first item (all bits), counting them
            column ->
                if (input.count { it[column] == '1'} > input.size / 2) '1' else '0'  //filter the count by the predicate function (count all 1s in column 1 of item)
        }.joinToString("")
        val epsilon = gamma.map { if (it == '1') '0' else '1' }.joinToString("")
        return gamma.toInt(2) * epsilon.toInt(2)
    }
}

fun main() {
    val values = File("src/resources","Day3.txt").readLines()
    val solutionOne = Day3Alt(values).solvePart1()

    println(solutionOne)
}
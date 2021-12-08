package day4

import java.io.File

typealias BingoBoard = List<List<Int>>

class Day4(input: List<String>) {
    private val draws: List<Int> = input.first().split(",").map { it.toInt() }
    private val boards: Set<BingoBoard> = parseInput(input)

    fun solvePart1(): Int {
        val drawn = draws.take(4).toMutableSet()
        return draws.drop(4).firstNotNullOf { draw ->
            drawn += draw
            //let passes the context object (in this case, a winning BingoBoard) to the lambda,
            // and returns the value of the lambda block
            boards.firstOrNull { it.isWinner(drawn )}?.let { winner ->
                draw * winner.sumUnmarked(drawn)
            }
        }
    }
}

private fun parseInput(input: List<String>): Set<BingoBoard> {
    val bingoboards = input
        .asSequence()
        .drop(1)
        .filter { it.isNotEmpty() }
        .chunked(5)
        .map { parseSingleBoard(it) }
        .toSet()
    return bingoboards
}



private fun parseSingleBoard(input: List<String>): BingoBoard =
    input.map { row ->
        row.split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
    }

//check if any row has all of its numbers in the draws set.
//check if any column (get index of rows 0 to 4) is in the draws set
private fun BingoBoard.isWinner(draws: Set<Int>) =
    this.any { row -> row.all { it in draws }} ||
            (0..4).any { col -> this.all { row -> row[col] in draws }}

//sum up all the unmarked spots on a board (all spots that are not in the draw)
private fun BingoBoard.sumUnmarked(draws: Set<Int>): Int =
    this.sumOf { row ->
        row.filterNot { it in draws }.sum()
    }

fun main() {
    val values = File("src/resources","Day4.txt").readLines()
    val day4 = Day4(values)
    println(day4.solvePart1())
}
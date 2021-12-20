package day11

import java.io.File

data class Octopus(
    var energy: Int,
    var flashed: Boolean = false,
)

data class Position(
    var x: Int,
    var y: Int
)

fun OctoList.dump(): String {
    val octopuses = this
    return buildString {
        octopuses.forEach { row ->
            row.forEach { octopus ->
                append(octopus.energy)
            }
            appendLine()
        }
    }.trim()
}

fun OctoList.increase() {
    forEach { row ->
        row.forEach { octopus ->
            octopus.energy++
        }
    }
}

fun OctoList.flash(): List<Position> {
    //find octopuses to flash
    //Return list of neighbors
    return emptyList()
}

fun OctoList.simulate() {

}

typealias OctoList = List<List<Octopus>>

fun readOctopusesFromFile(fileName: String): OctoList {
    return File(fileName)
        .readLines()
        .map { line ->
            line.toCharArray().map { value -> Octopus(value.digitToInt())}
        }
}

fun readOctopuses(fileName: String): OctoList {
    return readOctopusesFromString(File(fileName)
        .readText())

}

fun readOctopusesFromString(input: String): OctoList {
    return input.lines()
        .map { line ->
            line.toCharArray().map { value -> Octopus(value.digitToInt())}
        }
}




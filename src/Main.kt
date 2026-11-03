import matrix.determinant
import matrix.div
import matrix.toStr
import matrix.transposedMatrix

fun main() {
    val matrix = arrayOf(
        doubleArrayOf(2.0, 4.0, 6.0),
        doubleArrayOf(0.0, 2.0, 8.0),
        doubleArrayOf(10.0, 12.0, 0.0),
    )

    println("Матрица: ")
    println((matrix / 2.0).toStr())

//    println()
//    println("Матрица алг. доп.: ")
//    println(matrix.transposedMatrix().joinToString(separator = "\n") { it.joinToString(separator = " ")})

    println()
    println("Определитель: ${matrix.determinant()}")
}
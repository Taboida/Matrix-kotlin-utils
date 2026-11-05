import matrix.determinant
import matrix.div
import matrix.inverseMatrix
import matrix.toStr
import matrix.transposedMatrix

fun main() {
    val matrix = arrayOf(
        intArrayOf(2, 1, 3),
        intArrayOf(0, 4, -1),
        intArrayOf(1, 2, 1),
    )

    println("Матрица: ")
    println((matrix).toStr())

    println()
    println("Определитель: ${matrix.determinant()}")

    println()
    println("Транспонирования матрица: ")
    println(matrix.transposedMatrix().joinToString(separator = "\n") { it.joinToString(separator = " ")})

    println()
    println("Обратная матрица: ")
    println(matrix.inverseMatrix().joinToString(separator = "\n") { it.joinToString(separator = " ")})
}
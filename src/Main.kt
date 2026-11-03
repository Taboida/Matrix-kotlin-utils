import matrix.determinant
import matrix.inverseMatrix

fun main() {
    val matrix = arrayOf(
        doubleArrayOf(2.0, 5.0),
        doubleArrayOf(1.0, 3.0),
    )

    println("Матрица: ")
    println(matrix.joinToString(separator = "\n") { it.joinToString(separator = " ")})

    println()
    println("Обратная матрица: ")
    println(matrix.inverseMatrix().joinToString(separator = "\n") { it.joinToString(separator = " ")})
    println()
    println("Определитель: ${matrix.determinant()}")
}
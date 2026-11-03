import matrix.determinant

fun main() {
    val matrix = arrayOf(
        intArrayOf(1, 5, -2, 0),
        intArrayOf(3, 1, 6, -1),
        intArrayOf(7, 0, 1, 3),
        intArrayOf(4, 5, 2, 1),
    )

    println("Матрица: ")
    println(matrix.joinToString(separator = "\n") { it.joinToString(separator = " ")})
    println()
    println("Определитель: ${matrix.determinant()}")
}
package problems

import matrix.inverseMatrix
import kotlin.math.pow

// В обратной матрице вычислить среднее геометричесоке эл-ов, лежащих на главной даигонали
fun problem1(matrix: Array<IntArray>): Double {
    val inverseMatrix = matrix.inverseMatrix()
    val mainDiagonal = IntArray(inverseMatrix.size)
    inverseMatrix.indices.forEach { index ->
        mainDiagonal[index] = inverseMatrix[index][index]
    }

    return (mainDiagonal.reduce { acc, i -> acc * i }).toDouble().pow(1.0 / mainDiagonal.size)
}
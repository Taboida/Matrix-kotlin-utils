package matrix

import kotlin.collections.forEachIndexed

fun Array<DoubleArray>.toStr(): String {
    return this.joinToString(separator = "\n") { it.joinToString(separator = " ") }
}

operator fun Array<DoubleArray>.div(value: Double): Array<DoubleArray> {
    val result = Array(size) { DoubleArray(size) }
    val matrix = this
    matrix.forEachIndexed { i, row ->
        row.forEachIndexed { j, _ ->
            result[i][j] = matrix[i][j] / value
        }
    }
    return result
}

fun Array<DoubleArray>.transposedMatrix(): Array<DoubleArray> {
    val result = Array(size) { DoubleArray(size) }
    val matrix = this
    matrix.forEachIndexed { i, row ->
        row.forEachIndexed { j, _ ->
            result[i][j] = matrix[j][i]
        }
    }
    return result
}

//fun Array<DoubleArray>.inverseMatrix(): Array<DoubleArray> {
//    val algebraicComplementMatrix = Array(size) { DoubleArray(size) }
//    val matrix = this.transposedMatrix()
//    val determinant = determinant()
//    matrix.forEachIndexed { i, row ->
//        row.forEachIndexed { j, el ->
//            algebraicComplementMatrix[i][j] = algebraicComplement(i, j)
//        }
//    }
//    println("Матрица алг. доп.: ")
//    println(algebraicComplementMatrix.joinToString(separator = "\n") { it.joinToString(separator = " ")})
//    return algebraicComplementMatrix / determinant
//}

fun Array<DoubleArray>.algebraicComplement(i: Int, j: Int): Double { // i, j - как индексы массива (т.е. начало с 0)
    return (if (i + j % 2.0 == 0.0) 1 else -1) * this.minor(i, j)
}

fun Array<DoubleArray>.minor(i: Int, j: Int): Double { // i, j - как индексы массива (т.е. начало с 0)
    val mList = this.toMutableList()
    mList.remove(this[i])
    val matrixForMinor = mList.map { row -> row.filterIndexed { index, _ -> index != j } }
    return matrixForMinor.determinant()
}

fun Array<DoubleArray>.determinant(): Double {
    val m = this // для дальнейшей лаконичности и понятности назовем текущий массив m (от слова matrix)
    require(m.all { it.size == m.size })

    var determinant = 0.0
    when (m.size) {
        0 -> determinant = 1.0
        1 -> determinant = m[0][0]
        2 -> determinant = m[0][0] * m[1][1]  -  m[0][1] * m[1][0]
        3 -> {
            determinant = m[0][0] * m[1][1] * m[2][2]  +  m[0][1] * m[1][2] * m[2][0]  +  m[0][2] * m[1][0] * m[2][1]  -
                    m[0][2] * m[1][1] * m[2][0]  -  m[0][0] * m[1][2] * m[2][1]  -  m[0][1] * m[1][0] * m[2][2]
        }
        else -> m[0].indices.forEach { j -> // разложение по 1-й строке
            determinant += m[0][j] * algebraicComplement(0, j)  // элемент умноженый на его алгебр. дополнение
        }
    }

    return determinant
}

fun List<List<Double>>.determinant(): Double {
    val m = this.map { it.toDoubleArray() }.toTypedArray()
    return m.determinant()
}

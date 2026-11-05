package matrix

import kotlin.collections.forEachIndexed

fun Array<IntArray>.toStr(): String {
    return this.joinToString(separator = "\n") { it.joinToString(separator = " ") }
}

operator fun Array<IntArray>.div(value: Int): Array<IntArray> {
    val result = Array(size) { IntArray(size) }
    val matrix = this
    matrix.forEachIndexed { i, row ->
        row.forEachIndexed { j, _ ->
            result[i][j] = matrix[i][j] / value
        }
    }
    return result
}

fun Array<IntArray>.transposedMatrix(): Array<IntArray> {
    val result = Array(size) { IntArray(size) }
    val matrix = this
    matrix.forEachIndexed { i, row ->
        row.forEachIndexed { j, _ ->
            result[i][j] = matrix[j][i]
        }
    }
    return result
}

fun Array<IntArray>.inverseMatrix(): Array<IntArray> {
    val algebraicComplementMatrix = Array(size) { IntArray(size) }
    val matrix = this
    val determinant = determinant()
    matrix.forEachIndexed { i, row ->
        row.forEachIndexed { j, el ->
            algebraicComplementMatrix[i][j] = algebraicComplement(i, j)
        }
    }

    return algebraicComplementMatrix.transposedMatrix() / determinant
}

fun Array<IntArray>.algebraicComplement(i: Int, j: Int): Int { // i, j - как индексы массива (т.е. начало с 0)
    val res = (if ((i + j) % 2 == 0) 1 else -1) * this.minor(i, j)
    return res
}

fun Array<IntArray>.minor(i: Int, j: Int): Int { // i, j - как индексы массива (т.е. начало с 0)
    val mList = this.toMutableList()
    mList.remove(this[i])
    val matrixForMinor = mList.map { row -> row.filterIndexed { index, _ -> index != j } }
    return matrixForMinor.determinant()
}

fun Array<IntArray>.determinant(): Int {
    val m = this // для дальнейшей лаконичности и понятности назовем текущий массив m (от слова matrix)
    require(m.all { it.size == m.size })

    var determinant = 0
    when (m.size) {
        0 -> determinant = 1
        1 -> determinant = m[0][0]
        2 -> {
            determinant = (m[0][0] * m[1][1]) - (m[0][1] * m[1][0])
            val o = 0
        }
        3 -> {
            val p1 = m[0][0] * m[1][1] * m[2][2]
            val p2 = m[0][1] * m[1][2] * m[2][0]
            val p3 = m[0][2] * m[1][0] * m[2][1]
            val p4 = m[0][2] * m[1][1] * m[2][0]
            val p5 = m[0][0] * m[1][2] * m[2][1]
            val p6 = m[0][1] * m[1][0] * m[2][2]
            determinant = p1 + p2 + p3 - p4 - p5 - p6
        }
        else -> m[0].indices.forEach { j -> // разложение по 1-й строке
            determinant += m[0][j] * algebraicComplement(0, j)  // элемент умноженый на его алгебр. дополнение
        }
    }

    return determinant
}

fun List<List<Int>>.determinant(): Int {
    val m = this.map { it.toIntArray() }.toTypedArray()
    return m.determinant()
}

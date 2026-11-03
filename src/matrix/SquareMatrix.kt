package matrix

fun Array<IntArray>.algebraicComplement(i: Int, j: Int): Int {
    return (if (i + j % 2 == 0) 1 else -1) * this.minor(1, j+1)
}

fun Array<IntArray>.minor(i: Int, j: Int): Int {
    val mList = this.toMutableList()
    mList.remove(this[i - 1])
    val matrixForMinor = mList.map { row -> row.filterIndexed { index, _ -> index + 1 != j } }
    return matrixForMinor.determinant()
}

fun Array<IntArray>.determinant(): Int {
    val m = this // для дальнейшей лаконичности и понятности назовем текущий массив m (от слова matrix)
    require(m.all { it.size == m.size })

    var determinant = 0
    when (m.size) {
        1 -> determinant = m[0][0]
        2 -> determinant = m[0][0] * m[1][1] - m[0][1] * m[1][0]
        3 -> {
            determinant = m[0][0]*m[1][1]*m[2][2] + m[0][1]*m[1][2]*m[2][0] + m[0][2]*m[1][0]*m[2][1] -
                    m[0][2]*m[1][1]*m[2][0] - m[0][0]*m[1][2]*m[2][1] - m[0][1]*m[1][0]*m[2][2]
        }
        else -> m[0].indices.forEach { j -> // разложение по 1-й строке
            determinant += algebraicComplement(0, j) * m[0][j] // элемент умноженый на его алгебр. дополнение
        }
    }

    return determinant
}

fun List<List<Int>>.determinant(): Int {
    val m = this.map { it.toIntArray() }.toTypedArray()
    return m.determinant()
}

package matrix

fun Array<IntArray>.minor(i: Int, j: Int): Int {
    val mList = this.toMutableList()
    mList.remove(this[i - 1])
    val matrixForMinor = mList.map { row -> row.filterIndexed { index, el -> index + 1 != j } }
    val d = matrixForMinor.determinant()
//    println("Минор для i: $i, j: $j равен $d")
//    println(matrixForMinor.joinToString(separator = "\n") { it.joinToString(separator = " ")})
//    println()
    return d
}

fun Array<IntArray>.determinant(): Int {
    require(this.all { it.size == this.size })

    var determinant = 0
    var iteration = 1
    when (this.size) {
        1 -> determinant = this[0][0]
        2 -> determinant = this[0][0] * this[1][1] - this[0][1] * this[1][0]
        3 -> {
            determinant = this[0][0]*this[1][1]*this[2][2] + this[0][1]*this[1][2]*this[2][0] + this[0][2]*this[1][0]*this[2][1] -
                    this[0][2]*this[1][1]*this[2][0] - this[0][0]*this[1][2]*this[2][1] - this[0][1]*this[1][0]*this[2][2]
        }
        else -> this[0].indices.forEach { j -> // разложение по 1-й строке
            val algebraicComplement = (if (iteration % 2 != 0) 1 else -1) * this.minor(1, j+1)
            determinant += algebraicComplement * this[0][j] // элемент умноженый на его алгебраическое дополнение
            iteration++
        }
    }

    return determinant
}

fun List<List<Int>>.determinant(): Int {
    val m = this.map { it.toIntArray() }.toTypedArray()
    return m.determinant()
}

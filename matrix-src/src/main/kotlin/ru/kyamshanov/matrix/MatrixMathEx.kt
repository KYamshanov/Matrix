package ru.kyamshanov.matrix

/**
 * Extensions for use math operation on matrix
 */

/**
 * Multiply matrix of float
 */
@JvmName("timesFloat")
operator fun Matrix<Float>.times(matrix: Matrix<Float>): Matrix<Float> =
    this.multiply(matrix, { v1, v2 -> v1 + v2 }, { v1, v2 -> v1 * v2 }, 0f)

/**
 * Multiply matrix of Int
 */
@JvmName("timesInt")
operator fun Matrix<Int>.times(matrix: Matrix<Int>): Matrix<Int> =
    this.multiply(matrix, { v1, v2 -> v1 + v2 }, { v1, v2 -> v1 * v2 }, 0)

/**
 *  transpose matrix
 */
inline fun <reified T : Number> Matrix<T>.transpose(): Matrix<T> {
    val rows = rows
    val columns = rowStride
    val transposedMatrix: Array<Array<T?>> = Array(columns) { arrayOfNulls<T>(rows) }

    for (i in 0 until rows) {
        for (j in 0 until columns) {
            transposedMatrix[j][i] = this[i][j]
        }
    }

    @Suppress("UNCHECKED_CAST")
    return Matrix(transposedMatrix as Array<Array<T>>)
}

private inline fun <reified T : Number> Matrix<T>.multiply(
    matrix: Matrix<T>,
    sum: (T, T) -> T,
    multiply: (T, T) -> T,
    initV: T
): Matrix<T> {
    val row1 = rows
    val col1 = rowStride
    val col2 = matrix.rowStride
    val product = Array(row1) { Array(col2) { initV } }

    for (i in 0..<row1) {
        for (j in 0..<col2) {
            for (k in 0..<col1) {
                product[i][j] = sum(product[i][j], multiply(this[i][k], matrix[k][j]))
            }
        }
    }

    return Matrix(product)
}
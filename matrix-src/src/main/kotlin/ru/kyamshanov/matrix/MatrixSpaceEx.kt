package ru.kyamshanov.matrix

import kotlin.math.cos
import kotlin.math.sin

/**
 * Transform matrix to Homogeneous coordinates
 */
@JvmName("homogeneousFloat")
fun Matrix<Float>.homogeneous(): Matrix<Float> {
    check(rows == 1) { "Matrix must have rows equal 1 to transform in Homogeneous coordinates. But it has $rows" }
    val matrix: Array<Array<Float>> = arrayOf(arrayOf(*values[0], 1f))
    return Matrix(matrix)
}

@JvmName("euclideanFloat")
fun Matrix<Float>.euclidean(): Matrix<Float> {
    check(rows == 1) { "Matrix in Homogeneous coordinates must have rows equal 1. But it has $rows" }
    val row = values[0]
    val array = arrayOfNulls<Float>(row.size - 1)
    for (v in array.indices) {
        array[v] = row[v] / row[row.lastIndex]
    }
    @Suppress("UNCHECKED_CAST")
    return Matrix.of((array as Array<Float>))
}

@JvmName("perspectiveFloat")
fun Matrix<Float>.perspective(n: Float, f: Float, w: Float, h: Float): Matrix<Float> {
    val rotateMatrix: Matrix<Float> = Matrix.of(
        arrayOf(2 * n / w, 0f, 0f, 0f),
        arrayOf(0f, 2 * n / h, 0f, 0f),
        arrayOf(0f, 0f, (n + f) / (n - f), (2 * f * n) / (n - f)),
        arrayOf(0f, 0f, -1f, 0f),
    )

    return (rotateMatrix * homogeneous().transpose()).transpose().euclidean()
}


private inline fun <reified T : Number> Matrix<T>.projective(
    zero: T,
    one: T,
): Matrix<T> {
    val matrix = arrayOfNulls<Array<T>>(rows + 1)
    matrix[matrix.lastIndex] = Array(rowStride + 1) { zero }.also { it[it.lastIndex] = one }
    this.values.forEachIndexed { index, ts ->
        matrix[index] = arrayOf(*get(index), zero)
    }
    @Suppress("UNCHECKED_CAST")
    return Matrix(matrix as Array<Array<T>>)
}
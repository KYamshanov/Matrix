package ru.kyamshanov.matrix

/**
 * Class represent matrix
 * @param values rows and columns of matrix
 *
 * @see Matrix.of
 * @see Matrix.transpose
 */
class Matrix<T : Number>(
    val values: Array<Array<T>>,
) {

    /**
     * The count elements in one row
     *
     * Important that each row in matrix has the same count of elements
     */
    val rowStride: Int

    /**
     * Count of row in matrix
     */
    val rows: Int = values.size

    init {
        check(values.isNotEmpty()) { "Array cannot be empty" }
        rowStride = values.first().size
        values.forEachIndexed { index, item -> check(item.size == rowStride) { "Line $index has incorrect size. Row stride is $rowStride but line size is ${item.size}" } }
    }

    /**
     * Get row by [index]
     */
    operator fun get(index: Int) = values[index]

    /** help func */
    override fun toString(): String {
        val str = StringBuilder()
        values.forEach { array ->
            str.appendLine(array.joinToString(","))
        }
        return str.toString()
    }

    /** help func */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Matrix<*>

        return values.contentDeepEquals(other.values)
    }

    /** help func */
    override fun hashCode(): Int {
        return values.contentDeepHashCode()
    }

    companion object {

        /**
         * Create matrix
         *
         * ```
         *      val matrix = Matrix.of(
         *         arrayOf(3f, -1f, 2f),
         *         arrayOf(4f, 2f, 0f),
         *         arrayOf(-5f, 6f, 1f),
         *     )
         * ```
         */
        inline fun <reified T : Number> of(vararg array: Array<T>) = Matrix(arrayOf(*array))

        /**
         * Create transpose
         *
         * ```
         * val t = Matrix.transpose(8f, 7f, 2f)
         * ```
         */
        inline fun <reified T : Number> transpose(vararg array: T): Matrix<T> {
            val transposeArray: Array<Array<T>> = Array(array.size) { arrayOf(array[0]) }

            array.forEachIndexed { index, ts ->
                transposeArray[index][0] = ts
            }

            return Matrix(transposeArray)
        }
    }
}
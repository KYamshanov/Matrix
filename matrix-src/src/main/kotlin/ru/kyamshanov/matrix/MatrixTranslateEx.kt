package ru.kyamshanov.matrix

@JvmName("translateFloat")
fun Matrix<Float>.translate(tX: Float, tY: Float, tZ: Float): Matrix<Float> {
    val translateMatrix: Matrix<Float> = Matrix.of(
        arrayOf(1f, 0f, 0f, tX),
        arrayOf(0f, 1f, 0f, tY),
        arrayOf(0f, 0f, 1f, tZ),
        arrayOf(0f, 0f, 0f, 1f),
    )

    return (translateMatrix * homogeneous().transpose()).transpose().euclidean()
}

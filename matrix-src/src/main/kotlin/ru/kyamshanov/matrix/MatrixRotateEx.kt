package ru.kyamshanov.matrix

import kotlin.math.cos
import kotlin.math.sin

@JvmName("rotateZFloat")
fun Matrix<Float>.rotateZ(degreesRadians: Float): Matrix<Float> {
    val rotateMatrix: Matrix<Float> = Matrix.of(
        arrayOf(cos(degreesRadians), -sin(degreesRadians), 0f, 0f),
        arrayOf(sin(degreesRadians), cos(degreesRadians), 0f, 0f),
        arrayOf(0f, 0f, 1f, 0f),
        arrayOf(0f, 0f, 0f, 1f),
    )

    return (rotateMatrix * homogeneous().transpose()).transpose().euclidean()
}

@JvmName("rotateYFloat")
fun Matrix<Float>.rotateY(degreesRadians: Float): Matrix<Float> {
    val rotateMatrix: Matrix<Float> = Matrix.of(
        arrayOf(cos(degreesRadians), 0f, sin(degreesRadians), 0f),
        arrayOf(0f, 1f, 0f, 0f),
        arrayOf(-sin(degreesRadians), 0f, cos(degreesRadians), 0f),
        arrayOf(0f, 0f, 0f, 1f),
    )

    return (rotateMatrix * homogeneous().transpose()).transpose().euclidean()
}

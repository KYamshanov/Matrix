package ru.kyamshanov.matrix

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class MatrixMathExKtTest {


    @Test
    fun timesFloat() {
        val matrix1 = Matrix.of(
            arrayOf(3f, -1f, 2f),
            arrayOf(4f, 2f, 0f),
            arrayOf(-5f, 6f, 1f),
        )

        val matrix2 = Matrix.transpose(8f, 7f, 2f)

        val expected = Matrix.transpose(21f, 46f, 4f)

        assertEquals(expected, matrix1 * matrix2)
    }

    @Test
    fun timesFloatWrong() {
        val matrix1 = Matrix.of(
            arrayOf(0f, -1f, 2f),
            arrayOf(4f, 2f, 0f),
            arrayOf(-5f, 6f, 1f),
        )

        val matrix2 = Matrix.transpose(8f, 7f, 2f)

        val expected = Matrix.transpose(21f, 46f, 4f)

        assertNotEquals(expected, matrix1 * matrix2)
    }

    @Test
    fun timesInt() {
        val matrix1 = Matrix.of(
            arrayOf(1, 2),
            arrayOf(3, 4)
        )

        val matrix2 = Matrix.of(
            arrayOf(5, 6),
            arrayOf(7, 8)
        )

        val expected = Matrix.of(
            arrayOf(19, 22),
            arrayOf(43, 50)
        )

        assertEquals(expected, matrix1 * matrix2)
    }

    @Test
    fun timesIntWrong() {
        val matrix1 = Matrix.of(
            arrayOf(6, 2),
            arrayOf(3, 4)
        )

        val matrix2 = Matrix.of(
            arrayOf(5, 6),
            arrayOf(7, 8)
        )

        val expected = Matrix.of(
            arrayOf(19, 22),
            arrayOf(43, 50)
        )

        assertNotEquals(expected, matrix1 * matrix2)
    }

    @Test
    fun transpose() {
        val matrix1 = Matrix.of(
            arrayOf(1, 2),
            arrayOf(3, 4)
        )

        val expected = Matrix.of(
            arrayOf(1, 3),
            arrayOf(2, 4)
        )

        assertEquals(expected, matrix1.transpose())
    }

    @Test
    fun transposeDouble() {
        val matrix1 = Matrix.of(
            arrayOf(1, 2),
            arrayOf(3, 4)
        )

        val expected = Matrix.of(
            arrayOf(1, 2),
            arrayOf(3, 4)
        )

        assertEquals(expected, matrix1.transpose().transpose())
    }

    @Test
    fun transposeWrong() {
        val matrix1 = Matrix.of(
            arrayOf(1, 2),
            arrayOf(3, 4)
        )

        val expected = Matrix.of(
            arrayOf(2, 1),
            arrayOf(4, 3)
        )

        assertNotEquals(expected, matrix1.transpose().transpose())
    }
}
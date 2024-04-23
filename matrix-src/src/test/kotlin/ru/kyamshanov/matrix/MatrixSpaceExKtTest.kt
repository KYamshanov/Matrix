package ru.kyamshanov.matrix

import kotlin.test.Test
import kotlin.test.assertEquals

class MatrixSpaceExKtTest {

    @Test
    fun projectiveSpaceFloat() {
        val matrix = Matrix.transpose(4, 5).projectiveSpace()
        val expect = Matrix.transpose(4, 5, 1)
        assertEquals(expect, matrix)
    }


}
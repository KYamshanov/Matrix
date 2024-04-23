import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import org.jetbrains.compose.ui.tooling.preview.Preview
import ru.kyamshanov.matrix.*
import kotlin.math.cos
import kotlin.math.sin

@Composable
@Preview
fun App() {
    var rad by remember { mutableStateOf(0.0) }
    LaunchedEffect(Unit) {
        while (isActive) {
            delay(100L)
            rad -= 5
        }
    }
    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

            var n = 0.10f
            var w = 0.2f
            var h = 0.2f
            var f = 10f

            val point = arrayOf(
                Matrix.of(arrayOf(1f, 1f, -5f)),
                Matrix.of(arrayOf(1f, -1f, -5f)),
                Matrix.of(arrayOf(-1f, -1f, -5f)),
                Matrix.of(arrayOf(-1f, 1f, -5f))
            )



            Canvas(modifier = Modifier.fillMaxSize()) {

                val canvasQuadrantSize = size / 2F

                point
                    .map {
                   //     it[0][2] += -4f
                        it.rotateY(Math.toRadians(rad).toFloat())
                    }
                    .map {
                        it.perspective(n, f, w, h)
                    }.forEach { p ->
                        drawCircle(
                            color = Color.Magenta,
                            center = p.toScreen(size.width.toInt(), size.height.toInt()),
                            radius = 5f
                        )
                    }
            }
        }
    }
}

private fun Matrix<Float>.toScreen(screenWidth: Int, screenHeight: Int): Offset =
    Offset(
        values[0][0] * screenWidth / 2 + screenWidth / 2,
        values[0][1] * screenHeight / 2 + screenHeight / 2
    )
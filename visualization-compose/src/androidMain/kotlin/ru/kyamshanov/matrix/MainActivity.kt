package ru.kyamshanov.matrix

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}


@Composable
@org.jetbrains.compose.ui.tooling.preview.Preview
fun App() {
    var rad by remember { mutableStateOf(0.0) }
    /*  LaunchedEffect(Unit) {
          while (isActive) {
              delay(100L)
              rad -= 5
          }
      }*/


    val sm = LocalContext.current!!.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val s = sm.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)


    val sv = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            val rotationMatrix = FloatArray(16)
            SensorManager.getRotationMatrixFromVector(
                rotationMatrix, event.values
            )
            val remappedRotationMatrix = FloatArray(16)


            // Convert to orientations
            val orientations = FloatArray(3)
            SensorManager.getOrientation(remappedRotationMatrix, orientations)
            for (i in 0..2) {
                orientations[i] = Math.toDegrees(orientations[i].toDouble()).toFloat()
            }

            rad = (-orientations[2]).toDouble()
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        }
    }
    sm.registerListener(sv, s, SensorManager.SENSOR_DELAY_FASTEST);


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
                        it.rotateY(rad.toFloat())
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
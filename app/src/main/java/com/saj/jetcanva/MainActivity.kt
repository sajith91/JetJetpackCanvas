package com.saj.jetcanva

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


import com.saj.jetcanva.ui.theme.CanvasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
             CanvasTheme{
                 MainScreen()
             }
        }
    }
}


@Composable
fun MainScreen(){
    val lines = remember {
        mutableListOf<Line>()
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(true){
                detectDragGestures { change, dragAmount ->
                    change.consume()

                    val line = Line(
                        start = change.position -dragAmount,
                        end = change.position
                    )
                    lines.add(line)
                }
            }
    ){
        lines.forEach {line->
            drawLine(
                color = line.color,
                start = line.start,
                end = line.end,
                strokeWidth = line.strokeWidth.toPx(),
                cap = StrokeCap.Round

            )

        }
    }
}

data class Line(
    val start: Offset,
    val end: Offset,
    val color: Color = Color.Black,
    val strokeWidth: Dp = 1.dp
)
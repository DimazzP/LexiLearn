import android.graphics.Bitmap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.lexilearn.ui.theme.cGray
import com.example.lexilearn.ui.theme.ctextGray

@Composable
fun DrawBox(
    modifier: Modifier = Modifier,
    onPathReady: (android.graphics.Path, androidx.compose.ui.geometry.Size) -> Unit
) {
    var path by remember { mutableStateOf(Path()) }
    var androidPath by remember { mutableStateOf(android.graphics.Path()) }
    var canvasSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }

    Canvas(
        modifier = modifier.background(cGray, shape = RoundedCornerShape(16.dp))
            .onGloballyPositioned { coordinates ->
                canvasSize = coordinates.size.toSize()
            }
            .pointerInput(Unit) {
                detectDragGestures(onDragStart = { offset ->
                    path.moveTo(offset.x, offset.y)
                    androidPath.moveTo(offset.x, offset.y)
                }, onDrag = { change, _ ->
                    change.consume()
                    path.lineTo(change.position.x, change.position.y)
                    androidPath.lineTo(change.position.x, change.position.y)
                    path = Path().also { it.addPath(path) }
                })
            }
    ) {
        drawPath(
            path = path,
            color = Color.Black,
            style = Stroke(width = 5f, cap = StrokeCap.Round, join = StrokeJoin.Round)
        )
    }

    onPathReady(androidPath, canvasSize)
}
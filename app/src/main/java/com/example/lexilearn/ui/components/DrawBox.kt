import android.graphics.Bitmap
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.GestureDetectorCompat
import com.example.lexilearn.ui.theme.cGray
import com.example.lexilearn.ui.theme.ctextGray

@Composable
fun DrawBox(
    modifier: Modifier = Modifier,
    onPathReady: (android.graphics.Path, androidx.compose.ui.geometry.Size) -> Unit,
    clearPath: Boolean,
    onClearPathHandled: () -> Unit
) {
    var path by remember { mutableStateOf(Path()) }
    var androidPath by remember { mutableStateOf(android.graphics.Path()) }
    var canvasSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }

    if (clearPath) {
        path = Path()
        androidPath = android.graphics.Path()
        onClearPathHandled()
    }

    Canvas(
        modifier = modifier
            .background(cGray, shape = RoundedCornerShape(16.dp))
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
        clipPath(Path().apply { addRect(Rect(Offset.Zero, size)) }) {
            drawPath(
                path = path,
                color = Color.Black,
                style = Stroke(width = 5f, cap = StrokeCap.Round, join = StrokeJoin.Round)
            )
        }
    }

    onPathReady(androidPath, canvasSize)
}

@Preview
@Composable
fun DrawBoxPreview() {
    val context = LocalContext.current
    var drawnPath: android.graphics.Path? = null
    var drawnSize: androidx.compose.ui.geometry.Size? = null

    DrawBox(
        modifier = Modifier.size(300.dp),
        onPathReady = { path, size ->
            drawnPath = path
            drawnSize = size
        },
        clearPath = false,
        onClearPathHandled = {}
    )

    // Simulate drawing on the canvas
    if (drawnPath != null && drawnSize != null) {
        val gestureDetector = GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDown(e: MotionEvent): Boolean {
                return true
            }

            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                e2?.let {
                    drawnPath?.lineTo(it.x, it.y)
                }
                return true
            }
        })

        AndroidView(
            factory = { context ->
                View(context).apply {
                    setOnTouchListener { _, event ->
                        gestureDetector.onTouchEvent(event)
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

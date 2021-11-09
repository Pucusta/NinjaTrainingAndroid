package hu.bme.aut.ninjatraining.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import hu.bme.aut.ninjatraining.model.Scene

class SceneView {

    private val paint: Paint = Paint()

    init {
        paint.color = Color.CYAN
    }

    fun draw(scene: Scene, canvas: Canvas) {
        canvas.drawRect(0F, 0F, scene.width, scene.height, paint)
    }

}
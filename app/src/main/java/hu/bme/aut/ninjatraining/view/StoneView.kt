package hu.bme.aut.ninjatraining.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import hu.bme.aut.ninjatraining.model.Stone

class StoneView {

    private val paint: Paint = Paint()

    init {
        paint.color = Color.GRAY
    }

    fun draw(stone: Stone, canvas: Canvas) {
        canvas.drawCircle(stone.position.x, stone.position.y, stone.radius, paint)
    }
}
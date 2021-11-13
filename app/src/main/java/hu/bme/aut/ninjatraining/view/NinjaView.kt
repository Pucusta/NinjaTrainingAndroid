package hu.bme.aut.ninjatraining.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import hu.bme.aut.ninjatraining.model.Ninja

class NinjaView {

    private val paint: Paint = Paint()

    init {
        paint.color = Color.BLACK
    }

    fun draw(ninja: Ninja, canvas: Canvas) {
        canvas.drawCircle(ninja.position.x, ninja.position.y, 50F, paint)
    }
}
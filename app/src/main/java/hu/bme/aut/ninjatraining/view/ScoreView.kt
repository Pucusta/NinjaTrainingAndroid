package hu.bme.aut.ninjatraining.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import hu.bme.aut.ninjatraining.model.Ninja

class ScoreView {

    private val paint: Paint = Paint()

    init {
        paint.color = Color.WHITE
        paint.textSize = 50F
    }

    fun draw(score: Int, canvas: Canvas) {
        canvas.drawText("Score: $score", 0F, 40F, paint)
    }
}
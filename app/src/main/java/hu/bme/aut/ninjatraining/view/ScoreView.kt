package hu.bme.aut.ninjatraining.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class ScoreView {

    private val paintWhite = Paint()
    private val paintBlack = Paint()

    init {
        paintWhite.color = Color.WHITE
        paintWhite.textSize = 100F
        paintBlack.color = Color.BLACK
        paintBlack.textSize = 100F
    }

    fun draw(score: Int, canvas: Canvas) {
        canvas.drawText("$score", 10F, 85F, paintBlack)
        canvas.drawText("$score", 5F, 80F, paintWhite)
    }
}
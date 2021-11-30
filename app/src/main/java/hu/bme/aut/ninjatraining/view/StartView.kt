package hu.bme.aut.ninjatraining.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import hu.bme.aut.ninjatraining.model.Scene

class StartView {

    private val paintWhite: Paint = Paint()
    private val paintBlack: Paint = Paint()

    init {
        paintWhite.color = Color.WHITE
        paintBlack.color = Color.BLACK
    }

    fun draw(scene: Scene, canvas: Canvas) {
        paintWhite.textSize = scene.width / 10
        paintBlack.textSize = scene.width / 10

        canvas.drawText("TOUCH TO START", scene.width / 10 + 5, scene.height / 2 + 5, paintBlack)
        canvas.drawText("TOUCH TO START", scene.width / 10, scene.height / 2, paintWhite)
    }
}
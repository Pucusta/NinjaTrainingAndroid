package hu.bme.aut.ninjatraining.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import hu.bme.aut.ninjatraining.model.Scene

class SceneView {

    private val paintCyan = Paint()
    private val paintGreen = Paint()
    private val paintBrown = Paint()

    init {
        paintCyan.color = Color.CYAN
        paintGreen.color = Color.GREEN
        paintBrown.color = Color.rgb(155, 118, 83)
    }

    fun draw(scene: Scene, canvas: Canvas) {
        canvas.drawRect(0F, 0F, scene.width, scene.height, paintCyan)
        canvas.drawRect(0F, scene.height*(4F/5F) + scene.width / 20, scene.width, scene.height, paintGreen)
        canvas.drawRect(0F, scene.height*(4F/5F) + 2 * (scene.width / 20), scene.width, scene.height, paintBrown)
    }

}
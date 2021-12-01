package hu.bme.aut.ninjatraining.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import hu.bme.aut.ninjatraining.model.Ninja

class NinjaView {

    private val paintBlack = Paint()
    private val paintSkin = Paint()

    init {
        paintBlack.color = Color.BLACK
        paintSkin.color = Color.rgb(255, 206, 123)
    }

    fun draw(ninja: Ninja, canvas: Canvas) {
        canvas.drawCircle(ninja.position.x, ninja.position.y, ninja.radius, paintBlack)

        var xFace = ninja.position.x - ninja.radius / 2
        var yFace = ninja.position.y - ninja.radius / 2
        //canvas.drawRect(xFace, yFace, xFace + ninja.radius, yFace + ninja.radius / 4, paintSkin)
        canvas.drawRoundRect(xFace, yFace, xFace + ninja.radius, yFace + ninja.radius / 4, yFace + ninja.radius / 4 / 2, yFace + ninja.radius / 4 / 2, paintSkin)
        var xEye1 = xFace + ninja.radius / 5
        var yEye1 = yFace + ninja.radius / 4 / 3
        canvas.drawRoundRect(xEye1, yEye1, xEye1 + ninja.radius / 5, yEye1 + ninja.radius / 4 / 3, yEye1 + ninja.radius / 4 / 3 / 2, yEye1 + ninja.radius / 4 / 3 / 2, paintBlack)

        var xEye2 = xFace + ninja.radius / 5 * 3
        var yEye2 = yFace + ninja.radius / 4 / 3
        canvas.drawRoundRect(xEye2, yEye2, xEye2 + ninja.radius / 5, yEye2 + ninja.radius / 4 / 3, yEye1 + ninja.radius / 4 / 3 / 2, yEye1 + ninja.radius / 4 / 3 / 2, paintBlack)
    }
}
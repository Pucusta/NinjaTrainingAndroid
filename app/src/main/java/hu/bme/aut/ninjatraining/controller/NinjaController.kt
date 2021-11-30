package hu.bme.aut.ninjatraining.controller

import android.graphics.Canvas
import hu.bme.aut.ninjatraining.model.Ninja
import hu.bme.aut.ninjatraining.model.Vec2
import hu.bme.aut.ninjatraining.view.NinjaView

class NinjaController(val width: Float, val height: Float) {

    private val ninja = Ninja(Vec2(width/2, height*(4F/5F)), Vec2(0F, 0F), width / 20)
    private val ninjaView = NinjaView()

    private val speedOfNinja = 25F

    fun draw(canvas: Canvas) {
        ninjaView.draw(ninja, canvas)
    }

    fun step() {
        ninja.step()
        if (ninja.position.x < 0) ninja.position.x = 0F
        else if (ninja.position.x > width) ninja.position.x = width
    }

    fun goRight() {
        ninja.velocity = Vec2(speedOfNinja, 0F)
    }

    fun goLeft() {
        ninja.velocity = Vec2(-speedOfNinja, 0F)
    }

    fun stayStill() {
        ninja.velocity = Vec2(0F, 0F)
    }

    fun getNinja() : Ninja {
        return ninja
    }
}
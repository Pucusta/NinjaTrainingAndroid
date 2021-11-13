package hu.bme.aut.ninjatraining.controller

import android.graphics.Canvas
import hu.bme.aut.ninjatraining.model.Scene
import hu.bme.aut.ninjatraining.view.SceneView

class SceneController(width: Float, height: Float) {

    private val scene: Scene = Scene(width, height)
    private val sceneView: SceneView = SceneView()

    fun draw(canvas: Canvas) {
        sceneView.draw(scene, canvas)
    }
}
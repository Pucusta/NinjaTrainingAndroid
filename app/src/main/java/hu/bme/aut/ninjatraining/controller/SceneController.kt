package hu.bme.aut.ninjatraining.controller

import android.graphics.Canvas
import hu.bme.aut.ninjatraining.model.Scene
import hu.bme.aut.ninjatraining.view.SceneView
import hu.bme.aut.ninjatraining.view.StartView

class SceneController(width: Float, height: Float) {

    private val scene = Scene(width, height)
    private val sceneView = SceneView()
    private val startView = StartView()

    fun draw(canvas: Canvas) {
        sceneView.draw(scene, canvas)

        if (!scene.gameStarted)
            startView.draw(scene, canvas)
    }

    fun setGameStarted(bool: Boolean) {
        scene.gameStarted = true
    }
}
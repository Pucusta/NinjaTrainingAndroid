package hu.bme.aut.ninjatraining

import android.graphics.Canvas
import android.util.Log
import android.view.SurfaceHolder

class Timer(var surfaceHolder: SurfaceHolder, var game: Game): Thread() {

    val FPS: Int = 60
    var avgFPS: Double = 0.0
    var running: Boolean = false
    var canvas: Canvas = Canvas()

    override fun run() {
        var startTime: Long = 0
        var timeMillis: Long = 0
        var waitTime: Long = 0
        var totalTime: Long = 0
        var frameCount: Int = 0
        var targetTime: Long = (1000/FPS).toLong()

        while (running) {
            startTime = System.nanoTime()

            try {
                canvas = surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    game.step()
                    game.invalidate()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    surfaceHolder.unlockCanvasAndPost(canvas)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000
            waitTime = targetTime - timeMillis

            if (waitTime > 0) {
                try {
                    sleep(waitTime)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            totalTime += System.nanoTime() - startTime
            frameCount++
            if (frameCount == FPS) {
                avgFPS = (1000 / ((totalTime / frameCount) / 1000000)).toDouble()
                frameCount = 0
                totalTime = 0
            }
        }
    }
}
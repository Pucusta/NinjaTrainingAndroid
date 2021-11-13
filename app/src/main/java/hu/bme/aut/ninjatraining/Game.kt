package hu.bme.aut.ninjatraining

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.view.SurfaceHolder
import android.view.SurfaceView
import hu.bme.aut.ninjatraining.controller.NinjaController
import hu.bme.aut.ninjatraining.controller.SceneController
import hu.bme.aut.ninjatraining.controller.ScoreController
import hu.bme.aut.ninjatraining.controller.StoneController

class Game(context: Context, val gameActivity: GameActivity) : SurfaceView(context), SurfaceHolder.Callback {

    private var thread: Timer

    private lateinit var ninjaController: NinjaController
    private lateinit var stoneController: StoneController
    private lateinit var sceneController: SceneController
    private lateinit var scoreController: ScoreController

    private var rightPressed = false
    private var leftPressed = false

    init {
        holder.addCallback(this)
        thread = Timer(holder, this)
        isFocusable = true
        setBackgroundColor(Color.CYAN)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        ninjaController = NinjaController(width.toFloat(), height.toFloat())
        stoneController = StoneController(width.toFloat(), height.toFloat())
        sceneController = SceneController(width.toFloat(), height.toFloat())
        scoreController = ScoreController()

        thread.running = true
        thread.start()

        scoreController.startCounting()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        try {
            thread.running = false
            thread.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun step() {
        stoneController.step()
        ninjaController.step()
        scoreController.step()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        sceneController.draw(canvas)
        stoneController.draw(canvas)
        ninjaController.draw(canvas)
        scoreController.draw(canvas)
        invalidate()
    }

    fun rightButtonPressed(){
        rightPressed = true
        ninjaController.goRight()
    }

    fun rightButtonReleased(){
        rightPressed = false
        if (leftPressed) ninjaController.goLeft()
        else ninjaController.stayStill()
    }

    fun leftButtonPressed(){
        leftPressed = true
        ninjaController.goLeft()
    }

    fun leftButtonReleased(){
        leftPressed = false
        if (rightPressed) ninjaController.goRight()
        else ninjaController.stayStill()
    }
}
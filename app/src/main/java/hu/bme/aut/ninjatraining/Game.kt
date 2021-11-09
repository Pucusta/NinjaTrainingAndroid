package hu.bme.aut.ninjatraining

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.view.SurfaceHolder
import android.view.SurfaceView
import hu.bme.aut.ninjatraining.model.Scene
import hu.bme.aut.ninjatraining.model.Stone
import hu.bme.aut.ninjatraining.model.Vec2
import hu.bme.aut.ninjatraining.view.SceneView
import hu.bme.aut.ninjatraining.view.StoneView

class Game(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    private var thread: Timer

    private val gravity: Vec2 = Vec2(0F, 1F)

    private var stone: Stone
    private val stoneView: StoneView = StoneView()
    private var scene: Scene
    private val sceneView: SceneView = SceneView()

    init {
        holder.addCallback(this)
        thread = Timer(holder, this)
        isFocusable = true
        setBackgroundColor(Color.CYAN)

        //TODO Ilyenkor a width és a height még nem elérhetőek
        stone = Stone(Vec2((width/2).toFloat(), (height/2).toFloat()))
        scene = Scene(width.toFloat(), height.toFloat())
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread.running = true
        thread.start()
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
        stone.velocity = stone.velocity + gravity
        stone.step()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        sceneView.draw(scene, canvas)
        stoneView.draw(stone, canvas)
    }
}
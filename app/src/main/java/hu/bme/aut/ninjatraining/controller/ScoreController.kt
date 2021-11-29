package hu.bme.aut.ninjatraining.controller

import android.graphics.Canvas
import hu.bme.aut.ninjatraining.view.ScoreView

class ScoreController {

    private val scoreView: ScoreView = ScoreView()
    private var startTime: Long = 0
    private var score = 0

    fun startCounting(){
        startTime = System.currentTimeMillis()
    }

    fun step(){
        score = ((System.currentTimeMillis() - startTime) / 1000).toInt()
    }

    fun draw(canvas: Canvas){
        scoreView.draw(score, canvas)
    }

    fun getScore(): Int {
        return score
    }
}
package hu.bme.aut.ninjatraining.controller

import android.graphics.Canvas
import hu.bme.aut.ninjatraining.model.Stone
import hu.bme.aut.ninjatraining.model.Vec2
import hu.bme.aut.ninjatraining.view.StoneView
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class StoneController(val width: Float, val height: Float) {

    private val stones: ArrayList<Stone> = arrayListOf()
    private val o = Object()
    private val stoneView = StoneView()
    private val maxNumOfStones = 20
    private var stepsSinceLastStone = 0

    private val gravity: Vec2 = Vec2(0F, 1F)

    fun draw(canvas: Canvas){
        synchronized(o) {
            for (stone in stones)
                stoneView.draw(stone, canvas)
        }
    }

    fun step(){
        synchronized(o) {

            generateStones()

            for (stone in stones) {
                stone.velocity += gravity
                stone.step()
                if (stone.position.y > height)
                    stones.remove(stone);
            }
        }

        stepsSinceLastStone += 1
    }

    fun generateStones() {
        if (stones.size < maxNumOfStones && stepsSinceLastStone >= 5) {
            stones.add(Stone(Vec2(Random.nextInt(0, width.toInt()).toFloat(), - (width / 25)), Vec2((Random.nextInt(0, 8) - 4).toFloat(), 0F), width / 25))
            stepsSinceLastStone = 0
        }
    }

    fun getStones() : List<Stone> {
        return stones
    }
}
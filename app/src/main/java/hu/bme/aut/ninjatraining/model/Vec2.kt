package hu.bme.aut.ninjatraining.model

data class Vec2(var x: Float = 0F, var y: Float = 0F) {

    operator fun plus(vec: Vec2) = Vec2(x + vec.x, y + vec.y)

    fun distance(vec: Vec2): Double {
        return Math.sqrt(Math.pow((x - vec.x).toDouble(), 2.0) + Math.pow((y - vec.y).toDouble(), 2.0))
    }
}

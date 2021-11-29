package hu.bme.aut.ninjatraining.model

abstract class GameObject(
    var position: Vec2 = Vec2(0F, 0F),
    var velocity: Vec2 = Vec2(0F, 0F),
    var radius: Float = 0F) {

    abstract fun step()

    fun intersectGameObject(obj: GameObject) : Boolean {
        val distance = position.distance(obj.position)
        return distance <= radius + obj.radius
    }
}
package hu.bme.aut.ninjatraining.model

class Ninja(position: Vec2, velocity: Vec2, radius: Float) : GameObject(position, velocity, radius){

    override fun step() {
        position += velocity
    }
}
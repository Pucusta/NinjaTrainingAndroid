package hu.bme.aut.ninjatraining.model

class Stone(position: Vec2, velocity: Vec2, radius: Float) : GameObject(position, velocity, radius) {

    override fun step() {
        position += velocity
    }
}

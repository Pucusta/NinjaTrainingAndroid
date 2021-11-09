package hu.bme.aut.ninjatraining.model

class Stone(var position: Vec2 = Vec2(0F, 0F), var velocity: Vec2 = Vec2(0F, 0F)) {

    fun step() {
        position = position + velocity
    }
}

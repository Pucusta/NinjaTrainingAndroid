package hu.bme.aut.ninjatraining

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import hu.bme.aut.ninjatraining.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    private lateinit var game: Game

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        game = Game(this, this)
        binding.gameViewLayout.addView(game)

        binding.rightButton.setOnTouchListener(object: View.OnTouchListener {
            override fun onTouch(view: View?, event: MotionEvent?): Boolean {
                if (event != null) {
                    when(event.action) {
                        MotionEvent.ACTION_DOWN -> game.rightButtonPressed()
                        MotionEvent.ACTION_UP -> game.rightButtonReleased()
                    }
                }

                return true
            }
        })

        binding.leftButton.setOnTouchListener(object: View.OnTouchListener {
            override fun onTouch(view: View?, event: MotionEvent?): Boolean {
                if (event != null) {
                    when(event.action) {
                        MotionEvent.ACTION_DOWN -> game.leftButtonPressed()
                        MotionEvent.ACTION_UP -> game.leftButtonReleased()
                    }
                }

                return true
            }
        })
    }
}
package hu.bme.aut.ninjatraining

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import hu.bme.aut.ninjatraining.adapter.ToplistAdapter
import hu.bme.aut.ninjatraining.data.ToplistDatabase
import hu.bme.aut.ninjatraining.data.ToplistItem
import hu.bme.aut.ninjatraining.databinding.ActivityGameBinding
import hu.bme.aut.ninjatraining.fragments.NewToplistItemDialogFragment
import kotlin.concurrent.thread

class GameActivity : AppCompatActivity(), NewToplistItemDialogFragment.NewToplistItemDialogListener{

    private lateinit var binding: ActivityGameBinding

    private lateinit var game: Game

    private lateinit var database: ToplistDatabase
    private lateinit var adapter: ToplistAdapter

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

        database = ToplistDatabase.getDatabase(applicationContext)
        adapter = ToplistAdapter()
    }

    override fun onToplistItemCreated(newItem: ToplistItem) {
        thread {
            val insertId = database.toplistItemDao().insert(newItem)
            newItem.id = insertId
            runOnUiThread {
                adapter.addItem(newItem)
            }
        }
    }

    fun endGame(score: Int) {
        val fragment = NewToplistItemDialogFragment.newInstance(score)
        fragment.show(supportFragmentManager, "NewToplistItemDialogFragment")
    }
}
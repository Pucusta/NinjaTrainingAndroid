package hu.bme.aut.ninjatraining

import android.annotation.SuppressLint
import android.content.Context
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

    var gameInitialized = false

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

    override fun onStart() {
        super.onStart()
        if (gameInitialized)
            game.startGame()
    }

    override fun onResume() {
        super.onResume()
        if (gameInitialized)
            game.startGame()
    }

    override fun onPause() {
        super.onPause()
        game.pauseGame()
    }

    override fun onStop() {
        super.onStop()
        game.pauseGame()
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

    override fun onReturnToGame() {
        recreate()
    }

    override fun onReturnToMenu() {
        finish()
    }

    fun endGame(score: Int) {
        val fragment = NewToplistItemDialogFragment.newInstance(score)
        fragment.show(supportFragmentManager, "NewToplistItemDialogFragment")
    }

    override fun saveLastPlayerName(name: String) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("lastPlayerName", name)
            apply()
        }
    }

    override fun loadLastPlayerName(): String {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return ""
        return sharedPref.getString("lastPlayerName", "").toString()
    }
}
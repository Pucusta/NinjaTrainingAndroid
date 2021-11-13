package hu.bme.aut.ninjatraining

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.ninjatraining.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            val profileIntent = Intent(this, GameActivity::class.java)
            startActivity(profileIntent)
        }
    }
}



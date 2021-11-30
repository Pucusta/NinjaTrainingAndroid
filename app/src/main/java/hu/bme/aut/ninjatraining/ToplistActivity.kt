package hu.bme.aut.ninjatraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.ninjatraining.adapter.ToplistAdapter
import hu.bme.aut.ninjatraining.data.ToplistDatabase
import hu.bme.aut.ninjatraining.databinding.ActivityToplistBinding
import kotlin.concurrent.thread

class ToplistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityToplistBinding

    private lateinit var database: ToplistDatabase
    private lateinit var adapter: ToplistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToplistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        title = "Toplist"

        database = ToplistDatabase.getDatabase(applicationContext)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = ToplistAdapter()
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = adapter
        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
            val items = database.toplistItemDao().getAll()
            runOnUiThread {
                adapter.update(items)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toplist, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all -> {
                thread {
                    val toplistItems = database.toplistItemDao().getAll()
                    for (toplistItem in toplistItems)
                        database.toplistItemDao().deleteItem(toplistItem)
                    loadItemsInBackground()
                }
                true
            }
            R.id.keep_top -> {
                thread {
                    val toplistItems = database.toplistItemDao().getAll().sortedByDescending { ToplistItem -> ToplistItem.score }
                    val itemsForDelete = toplistItems.subList(10, toplistItems.size)
                    for (item in itemsForDelete)
                        database.toplistItemDao().deleteItem(item)
                    loadItemsInBackground()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
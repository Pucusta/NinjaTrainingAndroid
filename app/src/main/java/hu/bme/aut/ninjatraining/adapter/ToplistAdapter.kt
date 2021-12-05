package hu.bme.aut.ninjatraining.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.ninjatraining.data.ToplistItem
import hu.bme.aut.ninjatraining.databinding.ItemToplistBinding

class ToplistAdapter : RecyclerView.Adapter<ToplistAdapter.ToplistViewHolder>(){

    private val toplistItems = mutableListOf<ToplistItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ToplistViewHolder (
        ItemToplistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ToplistViewHolder, position: Int) {
        val toplistItem = toplistItems[position]

        holder.binding.tvPlace.text = (position + 1).toString() + "."
        holder.binding.tvName.text = toplistItem.name
        holder.binding.tvScore.text = toplistItem.score.toString()
    }

    fun addItem(item: ToplistItem) {
        toplistItems.add(item)
        notifyItemInserted(toplistItems.size - 1)
    }

    fun update(toplistItems: List<ToplistItem>) {
        this.toplistItems.clear()
        this.toplistItems.addAll(toplistItems.sortedByDescending { ToplistItem -> ToplistItem.score })

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = toplistItems.size

    inner class ToplistViewHolder(val binding: ItemToplistBinding) : RecyclerView.ViewHolder(binding.root)
}
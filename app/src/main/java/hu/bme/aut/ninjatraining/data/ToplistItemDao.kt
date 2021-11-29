package hu.bme.aut.ninjatraining.data

import androidx.room.*

@Dao
interface ToplistItemDao {
    @Query("SELECT * FROM toplistitem")
    fun getAll(): List<ToplistItem>

    @Insert
    fun insert(shoppingItems: ToplistItem): Long

    @Update
    fun update(shoppingItem: ToplistItem)

    @Delete
    fun deleteItem(shoppingItem: ToplistItem)
}
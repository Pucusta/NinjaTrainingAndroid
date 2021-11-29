package hu.bme.aut.ninjatraining.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToplistItem::class], version = 1)
abstract class ToplistDatabase : RoomDatabase() {
    abstract fun toplistItemDao(): ToplistItemDao

    companion object {
        fun getDatabase(applicationContext: Context): ToplistDatabase {
            return Room.databaseBuilder(
                applicationContext,
                ToplistDatabase::class.java,
                "toplist"
            ).build();
        }
    }
}

package sl.danny.data.entity

import android.app.Application
import android.content.Context
import androidx.room.Room

object DatabaseMgr {
    private lateinit var db: AppDatabase

    fun init(context: Context) {
        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "my_db"
        ).build()
    }

    fun getAppDB(): AppDatabase = db
}
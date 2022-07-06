package sl.danny.data.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface JokeDao {
    @Query("select * from jokes")
    suspend fun getAll(): List<JokeEntity>

    @Insert
    suspend fun insertAll(vararg jokes: JokeEntity)
}
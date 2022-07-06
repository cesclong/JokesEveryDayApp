package sl.danny.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jokes")
data class JokeEntity(
    @ColumnInfo(name = "content") val content: String?,
    @ColumnInfo(name = "update_time") val updateTime: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uId: Int = 0

}

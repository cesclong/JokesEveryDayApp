package sl.danny.data.repository

import sl.danny.base.BaseRepository
import sl.danny.data.JokeDataSource
import sl.danny.entity.JokeBean


interface JokeRepository : BaseRepository<Int, List<JokeBean>>

class JokeRepositoryImpl(
    private val dataSource: JokeDataSource
) : JokeRepository {
    override suspend fun invoke(param: Int?): List<JokeBean> = dataSource.getJokes(param ?: 0)

}
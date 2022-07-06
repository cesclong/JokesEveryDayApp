package sl.danny.domain

import org.koin.core.annotation.Single
import sl.danny.base.BaseUseCase
import sl.danny.data.repository.JokeRepository
import sl.danny.bean.JokeBean

interface FetchJokeUseCase : BaseUseCase<Int, List<JokeBean>>


@Single
class FetchJokeUseCaseImpl(
    private val repository: JokeRepository
) : FetchJokeUseCase {
    override suspend fun invoke(param: Int?): List<JokeBean> {
        val page: Int = param ?: 0
        return repository(page)
    }
}



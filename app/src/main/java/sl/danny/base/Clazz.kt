package sl.danny.base

interface BaseUseCase<in Parameter, out Output> {
    suspend operator fun invoke(param: Parameter? = null): Output
}

interface BaseRepository<in Parameter, out Output> {
    suspend operator fun invoke(param: Parameter? = null): Output
}


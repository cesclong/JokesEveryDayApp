package sl.danny.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import sl.danny.entity.JokeResponseDataBean

interface Api {
    @GET("api/jokes/list")
    suspend fun getJokes(
        @Query("page") page: Int,
        @Query("app_id") app_id: String,
        @Query("app_secret") app_secret: String
    ): JokeResponseDataBean
}
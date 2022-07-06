package sl.danny.data

import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sl.danny.data.network.Api
import sl.danny.bean.JokeBean

interface JokeDataSource {
    suspend fun getJokes(page: Int): List<JokeBean>
}


@Single
class RemoteJokeDataSource(private val gson: Gson) : JokeDataSource {
    private val api by lazy {
        Retrofit.Builder().apply {
            baseUrl("https://www.mxnzp.com/")
            client(OkHttpClient.Builder().apply {
                addConverterFactory(GsonConverterFactory.create(gson))
            }.build())
        }.build().create(Api::class.java)
    }

    /**
     * app_id:rgihdrm0kslojqvm
    app_secret:WnhrK251TWlUUThqaVFWbG5OeGQwdz09
     */
    override suspend fun getJokes(page: Int): List<JokeBean> {
        return api.getJokes(
            page = page,
            app_id = "rgihdrm0kslojqvm",
            app_secret = "WnhrK251TWlUUThqaVFWbG5OeGQwdz09"
        ).data.list
    }

}
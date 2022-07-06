package sl.danny

import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import sl.danny.data.JokeDataSource
import sl.danny.data.repository.JokeRepository
import sl.danny.di.DIManager
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class Test1 : KoinTest {
    val dataSource by inject<JokeDataSource>()
    val repository by inject<JokeRepository>()
    val gson by inject<Gson>()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(DIManager.allDIModules())
    }

    private suspend fun fetchDataFromDataSource() = dataSource.getJokes(0)

    private suspend fun fetchDataFromRepository() = repository(0)

    @Test
    fun `joke test from data source`() = runTest {
        val result = fetchDataFromDataSource()
        println(gson.toJson(result))
        assertEquals(true, result.isNotEmpty())
    }

    @Test
    fun `joke test from repository`() = runTest {
        val result = fetchDataFromRepository()
        println(gson.toJson(result))
        assertEquals(true, result.isNotEmpty())
    }
}
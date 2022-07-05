package sl.danny.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import sl.danny.data.JokeDataSource
import sl.danny.data.RemoteJokeDataSource
import sl.danny.data.repository.JokeRepository
import sl.danny.data.repository.JokeRepositoryImpl
import sl.danny.network.ContextProvider
import sl.danny.network.IOContextProvider
import sl.danny.network.MainContextProvider

object DIManager {

    fun init(context: Context) {
        startKoin {
            androidLogger()
            androidContext(context)
            modules(diModules())
        }
    }

    private fun diModules() = module {
        single<ContextProvider>(named("main")) { MainContextProvider() }
        single<ContextProvider>(named("io")) { IOContextProvider() }

        single<Gson> {
            GsonBuilder().setLenient().create()
        }

        single<JokeDataSource> { RemoteJokeDataSource(get()) }
        single<JokeRepository> { JokeRepositoryImpl(get()) }
    }

}
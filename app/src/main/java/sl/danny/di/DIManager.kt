package sl.danny.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
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

    }

}
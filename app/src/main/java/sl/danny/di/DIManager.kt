package sl.danny.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.ksp.generated.module

object DIManager {

    fun init(context: Context) {
        startKoin {
            androidLogger()
            androidContext(context)
            modules(
                allDIModules()
            )
        }
    }

    fun allDIModules(): List<org.koin.core.module.Module> =
        mutableListOf<org.koin.core.module.Module>().apply {
            add(
                module {
                    single<Gson> { GsonBuilder().setLenient().create() }
                }
            )

            add(
                DIModules().module
            )

        }
}


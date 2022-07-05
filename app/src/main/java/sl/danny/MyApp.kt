package sl.danny

import android.app.Application
import sl.danny.di.DIManager

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        DIManager.init(this)
    }
}
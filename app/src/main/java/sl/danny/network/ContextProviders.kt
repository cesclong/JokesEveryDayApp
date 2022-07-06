package sl.danny.network

import kotlinx.coroutines.Dispatchers
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import kotlin.coroutines.CoroutineContext

interface ContextProvider {
    fun context(): CoroutineContext
}

@Single
@Named("main")
class MainContextProvider : ContextProvider {
    override fun context(): CoroutineContext = Dispatchers.Main
}

@Single
@Named("io")
class IOContextProvider : ContextProvider{
    override fun context(): CoroutineContext = Dispatchers.IO
}

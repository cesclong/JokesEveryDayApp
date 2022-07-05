package sl.danny.network

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface ContextProvider {
    fun context(): CoroutineContext
}

class MainContextProvider : ContextProvider {
    override fun context(): CoroutineContext = Dispatchers.Main
}

class IOContextProvider : ContextProvider{
    override fun context(): CoroutineContext = Dispatchers.IO
}

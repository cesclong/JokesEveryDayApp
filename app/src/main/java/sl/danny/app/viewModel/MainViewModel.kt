package sl.danny.app.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Named
import sl.danny.domain.FetchJokeUseCase
import sl.danny.entity.JokeBean
import sl.danny.network.ContextProvider

sealed class MainUIAction {
    object Fetch : MainUIAction()
}

data class MainUIState(
    var loading: MutableState<Boolean> = mutableStateOf(false),
    val dataList: MutableState<MutableList<JokeBean>> = mutableStateOf(mutableListOf())
)

@KoinViewModel
class MainViewModel(
    @Named("main") private val mainContextProvider: ContextProvider,
    @Named("io") private val ioContextProvider: ContextProvider,
    private val useCase: FetchJokeUseCase,
    private val gson: Gson
) : ViewModel() {
    private val channel = Channel<MainUIAction>(Channel.UNLIMITED)

    val uiState by mutableStateOf(MainUIState())

    init {
        viewModelScope.launch(mainContextProvider.context()) {
            channel.consumeAsFlow().collectLatest {
                when (it) {
                    is MainUIAction.Fetch -> fetchRemote()
                }
            }
        }
    }

    private fun fetchRemote() {
        viewModelScope.launch(mainContextProvider.context()) {
            uiState.loading.value = true
            val list = withContext(ioContextProvider.context()) {
                useCase(0)
            }

            uiState.dataList.value.addAll(list)

            uiState.loading.value = false
        }

    }


    fun sendUIAction(action: MainUIAction) {
        channel.trySend(action)
    }


}
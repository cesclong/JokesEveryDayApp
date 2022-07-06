package sl.danny.app.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sl.danny.app.viewModel.MainUIAction
import sl.danny.app.viewModel.MainViewModel
import sl.danny.entity.JokeBean


@Composable
fun MainUIView(viewModel: MainViewModel) {
    val list = viewModel.uiState.dataList.value
    val isLoading = viewModel.uiState.loading.value

    if (isLoading) {
        Text(text = "Loading.....")
    } else {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            items(list) { bean ->
                MainItemView(bean = bean)
            }
        }
    }



    LaunchedEffect("onCreate") {
        viewModel.sendUIAction(MainUIAction.Fetch)
    }
}

@Composable
fun MainItemView(bean: JokeBean) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = bean.content,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth()
            )



            Text(
                text = bean.updateTime,
                fontSize = 12.sp,
                color = Color.Red,
                modifier = Modifier.fillMaxWidth()
            )

        }


    }

}
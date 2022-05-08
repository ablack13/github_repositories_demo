package ablack13.github.explorer.ui.screen.repositories

import ablack13.github.explorer.presentation.model.ProgressUi
import ablack13.github.explorer.presentation.model.RepositoryUi
import ablack13.github.explorer.ui.composable.Dimen
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage

@Composable
fun RepositoriesListContent(
    modifier: Modifier = Modifier,
    query: String,
    data: List<RepositoryUi>,
    progress: ProgressUi,
    onRetry: () -> Unit,
    onFetchNewDataAction: () -> Unit
) {
    Box(modifier = modifier) {
        if (data.isEmpty() && progress == ProgressUi.IDLE)
            if (query.isEmpty())
                EnterQueryEmptyList()
            else
                NotFoundHolder(query = query)
        else
            ContentView(
                query = query,
                data = data,
                progress = progress,
                onRetry = onRetry,
                onFetchNewDataAction = onFetchNewDataAction
            )
    }
}

@Composable
private fun EnterQueryEmptyList(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Enter query to find repositories")
    }
}

@Composable
private fun ContentView(
    query: String,
    data: List<RepositoryUi>,
    progress: ProgressUi,
    onRetry: () -> Unit,
    onFetchNewDataAction: () -> Unit
) {
    if (data.isEmpty())
        NotFoundHolder(query = query)
    else
        List(
            data = data,
            progress = progress,
            onRetry = onRetry,
            onFetchNewDataAction = onFetchNewDataAction
        )
}

@Composable
private fun NotFoundHolder(query: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Repositories for `$query` were not found")
    }
}

@Composable
private fun List(
    modifier: Modifier = Modifier,
    data: List<RepositoryUi>,
    progress: ProgressUi,
    onRetry: () -> Unit,
    onFetchNewDataAction: () -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Dimen.padding2),
        contentPadding = PaddingValues(
            horizontal = Dimen.padding2,
            vertical = Dimen.padding2
        ),
    ) {
        items(items = data) {
            Item(model = it)
        }

        item(key = progress) {
            when (progress) {
                ProgressUi.IDLE -> {
                    onFetchNewDataAction()
                }
                ProgressUi.RETRY -> {
                    Retry(onRetry = onRetry)
                }
                ProgressUi.LOADING -> {
                    Loading()
                }
            }
        }
    }
}

@Composable
private fun Item(model: RepositoryUi) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .padding(all = Dimen.padding2)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.DarkGray)) {
                            append("Owner: ")
                        }
                        withStyle(style = SpanStyle(color = Color.White)) {
                            append(model.owner)
                        }
                    },
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(50)
                            )
                            .padding(Dimen.padding1),
                        text = if (model.isPrivate) "Private" else "Public",
                        textAlign = TextAlign.Center,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        maxLines = 1
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = Dimen.padding2)
                        .size(100.dp)
                        .clip(RoundedCornerShape(Dimen.padding4))
                ) {
                    SubcomposeAsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = model.image,
                        loading = {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                CircularProgressIndicator(
                                    color = Color.Gray,
                                    modifier = Modifier.size(Dimen.padding8)
                                )
                            }
                        },
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                }
                Text(
                    text = model.name,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(start = Dimen.padding3)
                        .weight(1f)
                )
            }
        }
    }
}

@Composable
private fun Retry(onRetry: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Something went wrong",
            color = Color.Red,
            fontSize = 20.sp,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            onClick = onRetry,
            modifier = Modifier
                .weight(0.5f)
                .padding(4.dp)
        ) {
            Text(text = "Retry")
        }
    }
}

@Composable
private fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Loading")
    }
}
package ablack13.github.explorer.ui.screen.repositories

import ablack13.github.explorer.presentation.model.ProgressUi
import ablack13.github.explorer.presentation.model.RepositoryUi
import ablack13.github.explorer.presentation.viewmodel.RepositoriesListViewContract
import ablack13.github.explorer.presentation.viewmodel.RepositoriesListViewModel
import ablack13.github.explorer.ui.composable.*
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import org.koin.androidx.compose.getViewModel

@Composable
fun RepositoriesListScreen(viewModel: RepositoriesListViewModel = getViewModel()) {
    OnDisappear {
        viewModel.onCleared()
    }

    val state = viewModel.getViewState()
    val ctx = LocalContext.current

    viewModel.observeActionState {
        when (it) {
            is RepositoriesListViewContract.Action.Error -> {
                Toast.makeText(ctx, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    Scaffold(
        topBar = {
            Header(
                query = state.searchQuery,
                onQueryChanged = {
                    viewModel.obtainIntent(
                        RepositoriesListViewContract.Intent.Search(query = it)
                    )
                },
                modifier = Modifier.padding(
                    start = Dimen.padding6,
                    end = Dimen.padding6,
                    top = Dimen.padding4,
                    bottom = Dimen.padding4
                )
            )
        },
        content = {
            Content(
                query = state.searchQuery,
                progress = state.progress,
                data = state.data,
                onRetry = {
                    viewModel.obtainIntent(RepositoriesListViewContract.Intent.Retry)
                },
                onFetchNewDataAction = {
                    viewModel.obtainIntent(RepositoriesListViewContract.Intent.FetchNewData)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = Dimen.padding4,
                        end = Dimen.padding4,
                    )
            )
        },
    )
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChanged: (String) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        RepositoriesListHeader(
            query = query,
            onQueryChanged = onQueryChanged
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    query: String,
    data: List<RepositoryUi>,
    progress: ProgressUi,
    onRetry: () -> Unit,
    onFetchNewDataAction: () -> Unit
) {
    RepositoriesListContent(
        modifier = modifier,
        query = query,
        progress = progress,
        data = data,
        onRetry = onRetry,
        onFetchNewDataAction = onFetchNewDataAction
    )
}
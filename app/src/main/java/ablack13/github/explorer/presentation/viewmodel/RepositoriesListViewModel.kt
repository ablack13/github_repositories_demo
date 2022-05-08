package ablack13.github.explorer.presentation.viewmodel

import ablack13.github.explorer.domain.interactor.RepositoriesLoadByPageGetInteractor
import ablack13.github.explorer.domain.interactor.RepositoriesLoadGetInteractor
import ablack13.github.explorer.domain.util.DispatcherProvider
import ablack13.github.explorer.presentation.common.BaseViewModel
import ablack13.github.explorer.presentation.mapper.RepositoryUiMapper
import ablack13.github.explorer.presentation.model.ProgressUi
import kotlinx.coroutines.launch

class RepositoriesListViewModel(
    private val repositoriesLoadGetInteractor: RepositoriesLoadGetInteractor,
    private val repositoriesLoadByPageGetInteractor: RepositoriesLoadByPageGetInteractor,
    private val repositoryUiMapper: RepositoryUiMapper,
    dispatcherProvider: DispatcherProvider
) :
    BaseViewModel<RepositoriesListViewContract.Intent, RepositoriesListViewContract.State, RepositoriesListViewContract.Action>(
        dispatcherProvider = dispatcherProvider,
        state = RepositoriesListViewContract.State()
    ) {

    override fun obtainIntent(intent: RepositoriesListViewContract.Intent) {
        when (intent) {
            is RepositoriesListViewContract.Intent.FetchNewData -> fetchData()
            is RepositoriesListViewContract.Intent.Search -> searchRepositories(query = intent.query)
            is RepositoriesListViewContract.Intent.Retry -> retryFetchData()
        }
    }

    private fun fetchData() {
        vmScope.launch(dispatcherProvider.default) {
            updateState { copy(progress = ProgressUi.LOADING) }
            repositoriesLoadByPageGetInteractor.exec(
                query = getState().searchQuery,
                page = getState().page + 1
            )
                .map { it.map { model -> repositoryUiMapper.fromModel(model = model) } }
                .onSuccess {
                    updateState {
                        copy(
                            progress = ProgressUi.IDLE,
                            page = page + 1,
                            data = data + it
                        )
                    }
                }
                .onFailure {
                    emitAction(action = RepositoriesListViewContract.Action.Error(it.message))
                    updateState {
                        copy(progress = ProgressUi.RETRY)
                    }
                }
        }
    }

    private fun searchRepositories(query: String) {
        vmScope.launch(dispatcherProvider.default) {
            repositoriesLoadGetInteractor.exec(query = query)
                .map { it.map { model -> repositoryUiMapper.fromModel(model = model) } }
                .onSuccess {
                    updateState {
                        copy(
                            progress = ProgressUi.IDLE,
                            page = 1, //reset page indicator to first page
                            searchQuery = query,
                            data = it
                        )
                    }
                }
                .onFailure {
                    emitAction(action = RepositoriesListViewContract.Action.Error(it.message))
                    updateState {
                        copy(
                            progress = ProgressUi.RETRY,
                            searchQuery = query
                        )
                    }
                }
        }
    }

    private fun retryFetchData() {
        with(getState()) {
            if (data.isEmpty())
                searchRepositories(query = searchQuery)
            else
                fetchData()
        }
    }
}
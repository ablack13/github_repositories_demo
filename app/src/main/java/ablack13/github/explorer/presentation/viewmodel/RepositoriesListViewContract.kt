package ablack13.github.explorer.presentation.viewmodel

import ablack13.github.explorer.presentation.common.BaseAction
import ablack13.github.explorer.presentation.common.BaseIntent
import ablack13.github.explorer.presentation.common.BaseState
import ablack13.github.explorer.presentation.model.ProgressUi
import ablack13.github.explorer.presentation.model.RepositoryUi

object RepositoriesListViewContract {
    sealed class Intent : BaseIntent {
        object FetchNewData : Intent()
        object Retry : Intent()
        class Search(val query: String) : Intent()
    }

    data class State(
        val progress: ProgressUi = ProgressUi.IDLE,
        val searchQuery: String = "",
        val page: Int = 1,
        val data: List<RepositoryUi> = emptyList()
    ) : BaseState

    sealed class Action : BaseAction {
        class Error(val message: String?) : Action()
    }
}
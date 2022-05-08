package ablack13.github.explorer.di.scope.app

import ablack13.github.explorer.presentation.viewmodel.RepositoriesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module

internal fun Module.viewModels() {
    viewModel {
        RepositoriesListViewModel(
            repositoriesLoadGetInteractor = get(),
            repositoriesLoadByPageGetInteractor = get(),
            repositoryUiMapper = get(),
            dispatcherProvider = get()
        )
    }
}
package ablack13.github.explorer.di.scope.app

import ablack13.github.explorer.domain.interactor.RepositoriesLoadByPageGetInteractor
import ablack13.github.explorer.domain.interactor.RepositoriesLoadGetInteractor
import org.koin.core.module.Module

internal fun Module.interactors() {
    factory { RepositoriesLoadGetInteractor(repoRepository = get()) }
    factory { RepositoriesLoadByPageGetInteractor(repoRepository = get()) }
}
package ablack13.github.explorer.di.scope.app

import ablack13.github.explorer.data.repository.RepoRepositoryImpl
import ablack13.github.explorer.domain.repository.RepoRepository
import org.koin.core.module.Module

internal fun Module.repositories() {
    factory<RepoRepository> {
        RepoRepositoryImpl(repositoryDataSource = get())
    }
}
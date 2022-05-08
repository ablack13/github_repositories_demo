package ablack13.github.explorer.di.scope.app

import ablack13.github.explorer.data.datasource.RepositoriesDataSourceImpl
import ablack13.github.explorer.domain.datasource.RepositoryDataSource
import org.koin.core.module.Module

internal fun Module.dataSources() {
    single<RepositoryDataSource> {
        RepositoriesDataSourceImpl(
            webService = get(),
            repositoryModelMapper = get()
        )
    }
}
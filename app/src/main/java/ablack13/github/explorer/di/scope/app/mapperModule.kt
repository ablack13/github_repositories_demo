package ablack13.github.explorer.di.scope.app

import ablack13.github.explorer.data.mapper.RepositoryModelMapper
import ablack13.github.explorer.presentation.mapper.RepositoryUiMapper
import org.koin.core.module.Module

internal fun Module.mappers() {
    single { RepositoryModelMapper() }
    single { RepositoryUiMapper() }
}
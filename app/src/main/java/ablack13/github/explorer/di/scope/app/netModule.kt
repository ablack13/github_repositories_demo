package ablack13.github.explorer.di.scope.app

import ablack13.github.explorer.data.net.RestClient
import ablack13.github.explorer.data.net.WebService
import org.koin.core.module.Module

internal fun Module.network(isInDebug: Boolean) {
    single { RestClient(isInDebugMode = isInDebug) }
    single { WebService(restClient = get()) }
}
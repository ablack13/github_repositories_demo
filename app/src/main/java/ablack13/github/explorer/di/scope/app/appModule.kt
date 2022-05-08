package ablack13.github.explorer.di.scope.app

import android.content.Context
import org.koin.core.module.Module
import org.koin.dsl.module

internal fun appModule(isInDebug: Boolean, appContext: Context): Module =
    module {
        util(context = appContext)
        network(isInDebug = isInDebug)
        mappers()
        dataSources()
        repositories()
        interactors()
        viewModels()
    }
package ablack13.github.explorer.di.scope.app

import ablack13.github.explorer.data.util.DispatcherProviderImpl
import ablack13.github.explorer.domain.util.DispatcherProvider
import android.content.Context
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module

fun Module.util(context: Context) {
    single { context }
    single<DispatcherProvider> {
        DispatcherProviderImpl(
            default = Dispatchers.Default,
            main = Dispatchers.Main,
            io = Dispatchers.IO
        )
    }
}
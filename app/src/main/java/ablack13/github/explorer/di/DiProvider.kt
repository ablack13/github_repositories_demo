package ablack13.github.explorer.di

import ablack13.github.explorer.di.scope.app.appModule
import android.content.Context
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

object DiProvider {
    //this instance can be used for manually load/unload modules after engine init
    private lateinit var koinApp: KoinApplication

    fun startEngine(appContext: Context, isInDebug: Boolean) {
        koinApp = startKoin {
            modules(
                appModule(
                    isInDebug = isInDebug,
                    appContext = appContext
                )
            )
        }
    }

    //Manual module load/unload modules AREN'T USED. Added just for a sample how its can be used
    fun loadModule(module: Module) {
        koinApp.modules(module)
    }

    fun unloadModule(module: Module) {
        koinApp.unloadModules(module = module)
    }
}
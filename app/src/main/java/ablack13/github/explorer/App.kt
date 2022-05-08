package ablack13.github.explorer

import ablack13.github.explorer.di.DiProvider
import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        DiProvider.startEngine(
            appContext = applicationContext,
            isInDebug = BuildConfig.DEBUG
        )
    }
}
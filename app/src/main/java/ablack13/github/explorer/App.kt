package ablack13.github.explorer

import ablack13.github.explorer.di.DIProvider
import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        DIProvider.startEngine(
            appContext = applicationContext,
            isInDebug = BuildConfig.DEBUG
        )
    }
}
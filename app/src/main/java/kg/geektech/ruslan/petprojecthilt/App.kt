package kg.geektech.ruslan.petprojecthilt

import android.app.Application
import kg.geektech.ruslan.petprojecthilt.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            androidFileProperties()

            modules(listOf(
                viewModelModule,
                networkModule,
                repositoryModule
            ))
        }

    }
}

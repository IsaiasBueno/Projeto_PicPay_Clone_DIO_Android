package com.isaiasbueno.projeto_picpay_clone_dio_android

import android.app.Application
import com.isaiasbueno.projeto_picpay_clone_dio_android.di.daoModule
import com.isaiasbueno.projeto_picpay_clone_dio_android.di.repositoryModule
import com.isaiasbueno.projeto_picpay_clone_dio_android.di.serviceModule
import com.isaiasbueno.projeto_picpay_clone_dio_android.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(viewModelModule, serviceModule, repositoryModule, daoModule)
        }
    }
}
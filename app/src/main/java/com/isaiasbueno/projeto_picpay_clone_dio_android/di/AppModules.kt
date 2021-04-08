package com.isaiasbueno.projeto_picpay_clone_dio_android.di

import com.isaiasbueno.projeto_picpay_clone_dio_android.ComponentesViewModel
import com.isaiasbueno.projeto_picpay_clone_dio_android.repository.TransacaoRepository
import com.isaiasbueno.projeto_picpay_clone_dio_android.repository.TransacaoRepositoryImpl
import com.isaiasbueno.projeto_picpay_clone_dio_android.services.ApiService
import com.isaiasbueno.projeto_picpay_clone_dio_android.services.RetrofitService
import com.isaiasbueno.projeto_picpay_clone_dio_android.ui.ajuste.AjusteViewModel
import com.isaiasbueno.projeto_picpay_clone_dio_android.ui.home.HomeViewModel
import com.isaiasbueno.projeto_picpay_clone_dio_android.ui.login.LoginViewModel
import com.isaiasbueno.projeto_picpay_clone_dio_android.ui.pagar.PagarViewModel
import com.isaiasbueno.projeto_picpay_clone_dio_android.ui.transacao.TransacaoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
val viewModelModule = module {
    viewModel { ComponentesViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { PagarViewModel(get()) }
    viewModel { AjusteViewModel() }
    viewModel { TransacaoViewModel(get()) }
    viewModel { LoginViewModel(get()) }
}
val serviceModule = module {
    single { RetrofitService.create<ApiService>() }
}
val repositoryModule = module {
    single<TransacaoRepository> { TransacaoRepositoryImpl(get(), get()) }
}
val daoModule = module {
    single { AppDatabase.getInstance(androidContext()).transacaoDAO() }
}
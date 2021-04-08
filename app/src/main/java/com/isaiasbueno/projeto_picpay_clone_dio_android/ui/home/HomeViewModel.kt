package com.isaiasbueno.projeto_picpay_clone_dio_android.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isaiasbueno.projeto_picpay_clone_dio_android.data.State
import com.isaiasbueno.projeto_picpay_clone_dio_android.data.Transacao
import com.isaiasbueno.projeto_picpay_clone_dio_android.data.UsuarioLogado
import com.isaiasbueno.projeto_picpay_clone_dio_android.repository.TransacaoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class HomeViewModel(private val repository: TransacaoRepository) : ViewModel() {
    val saldoState = MutableLiveData<State<Double>>()
    val transacaoState = MutableLiveData<State<List<Transacao>>>()
    init {
        val login = UsuarioLogado.usuario.login
        obterSaldo(login)
        obterHistorico(login)
    }
    private fun obterHistorico(login: String) {
        viewModelScope.launch(Dispatchers.IO) {
            transacaoState.postValue(State.Loading())
            try {
                val historico = repository.getTransacoes(login)
                transacaoState.postValue(State.Success(historico))
            } catch (e: Exception) {
                transacaoState.postValue(State.Error(e))
            }
        }
    }
    private fun obterSaldo(login: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saldoState.postValue(State.Loading())
            try {
                val novoSaldo = repository.getSaldo(login).saldo
                UsuarioLogado.setSaldo(novoSaldo)
                saldoState.postValue(State.Success(novoSaldo))
            } catch (e: Exception) {
                saldoState.postValue(State.Error(e))
            }
        }
    }
}
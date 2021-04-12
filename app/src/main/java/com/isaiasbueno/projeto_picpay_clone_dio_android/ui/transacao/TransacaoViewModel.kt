package com.isaiasbueno.projeto_picpay_clone_dio_android.ui.transacao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isaiasbueno.projeto_picpay_clone_dio_android.data.Transacao
import com.isaiasbueno.projeto_picpay_clone_dio_android.services.ApiService
import kotlinx.coroutines.launch
class TransacaoViewModel(private val apiService: ApiService) : ViewModel() {
    private val _transferencia = MutableLiveData<Transacao>()
    val transacao: LiveData<Transacao> = _transferencia
    val onError = MutableLiveData<String>()
    fun realizaTransferencia(transacao: Transacao) {
        viewModelScope.launch {
            try {
                val transferenciaRealziada = apiService.realizarTransacao(transacao)
                _transferencia.value = transferenciaRealziada
            } catch (e: Exception) {
                onError.value = "Não Foi possível fazer transferência"
            }
        }
    }
}
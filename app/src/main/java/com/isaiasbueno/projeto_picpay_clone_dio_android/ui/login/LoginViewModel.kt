package com.isaiasbueno.projeto_picpay_clone_dio_android.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isaiasbueno.projeto_picpay_clone_dio_android.data.Login
import com.isaiasbueno.projeto_picpay_clone_dio_android.data.State
import com.isaiasbueno.projeto_picpay_clone_dio_android.data.Usuario
import com.isaiasbueno.projeto_picpay_clone_dio_android.data.UsuarioLogado
import com.isaiasbueno.projeto_picpay_clone_dio_android.services.ApiService
import kotlinx.coroutines.launch
class LoginViewModel(private val apiService: ApiService) : ViewModel() {
    val usuarioState = MutableLiveData<State<Usuario>>()
    fun login(login: Login) {
        usuarioState.value = State.Loading()
        viewModelScope.launch {
            try {
                val token = apiService.autenticar(login)
                UsuarioLogado.token = token
                val usuario = apiService.getUsuario(login.usuario)
                UsuarioLogado.usuario = usuario
                usuarioState.value = State.Success(usuario)
            } catch (e: Exception) {
                usuarioState.value = State.Error(e)
            }
        }
    }
}
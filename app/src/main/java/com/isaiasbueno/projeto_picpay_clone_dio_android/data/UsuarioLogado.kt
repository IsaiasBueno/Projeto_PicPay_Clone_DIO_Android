package com.isaiasbueno.projeto_picpay_clone_dio_android.data

object UsuarioLogado {
    lateinit var token: Token
    lateinit var usuario: Usuario
    fun setSaldo(saldo: Double) {
        usuario.saldo = saldo
    }
    fun isLogado(): Boolean = this::token.isInitialized
    fun isNaoLogado(): Boolean = !isLogado()
}
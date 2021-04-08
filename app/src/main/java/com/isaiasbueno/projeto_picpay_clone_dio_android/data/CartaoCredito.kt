package com.isaiasbueno.projeto_picpay_clone_dio_android.data

data class CartaoCredito(
    val bandeira: BandeiraCartao = BandeiraCartao.VISA,
    val numeroCartao: String = "",
    val nomeTitular: String = "",
    val dataExpiracao: String = "",
    val codigoSeguranca: String = "123",
    val numeroToken: String = "",
    val usuario: Usuario = Usuario(),
    val isSalvar: Boolean = false
)

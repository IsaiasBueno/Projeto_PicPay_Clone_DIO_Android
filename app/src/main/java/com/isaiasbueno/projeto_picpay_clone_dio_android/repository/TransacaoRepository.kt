package com.isaiasbueno.projeto_picpay_clone_dio_android.repository

import com.isaiasbueno.projeto_picpay_clone_dio_android.data.*
import com.isaiasbueno.projeto_picpay_clone_dio_android.services.ApiService
interface TransacaoRepository {
    suspend fun getSaldo(login: String): Usuario
    suspend fun getTransacoes(login: String): List<Transacao>
}
class TransacaoRepositoryImpl(
    private val apiService: ApiService,
    private val transacaoDAO: TransacaoDAO
) : TransacaoRepository {
    override suspend fun getSaldo(login: String): Usuario = apiService.getSaldo(login)
    override suspend fun getTransacoes(login: String): List<Transacao> {
        val transacoes = apiService.getTransacoes(login).content.toModel()
        transacaoDAO.save(transacoes.toLocal())
        return transacoes
    }
}
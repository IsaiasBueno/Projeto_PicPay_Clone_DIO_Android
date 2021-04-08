package com.isaiasbueno.projeto_picpay_clone_dio_android.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.isaiasbueno.projeto_picpay_clone_dio_android.Componentes
import com.isaiasbueno.projeto_picpay_clone_dio_android.ComponentesViewModel
import com.isaiasbueno.projeto_picpay_clone_dio_android.R
import com.isaiasbueno.projeto_picpay_clone_dio_android.data.Login
import com.isaiasbueno.projeto_picpay_clone_dio_android.data.State
import com.isaiasbueno.projeto_picpay_clone_dio_android.data.UsuarioLogado
import com.isaiasbueno.projeto_picpay_clone_dio_android.extension.esconder
import com.isaiasbueno.projeto_picpay_clone_dio_android.extension.getString
import com.isaiasbueno.projeto_picpay_clone_dio_android.extension.mostrar
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val componentesViewModel: ComponentesViewModel by sharedViewModel()
    private val viewModel: LoginViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentesViewModel.temComponentes = Componentes(bottomNavigation = false)
        configurarBotaoLogin()
        observarEstadosUsuario()
    }
    private fun observarEstadosUsuario() {
        viewModel.usuarioState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is State.Loading -> {
                    progressBar.mostrar()
                }
                is State.Success -> {
                    progressBar.esconder()
                    val direcao = LoginFragmentDirections.actionLoginFragmentToNavigationHome()
                    findNavController().navigate(direcao)
                }
                is State.Error -> {
                    progressBar.esconder()
                    Toast.makeText(
                        requireContext(),
                        "Falha ao autenticar",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
    private fun configurarBotaoLogin() {
        buttonLogin.setOnClickListener {
            val usuario = textInputLayoutUsuario.getString()
            val senha = textInputLayoutSenha.getString()
            val login = Login(usuario, senha)
            viewModel.login(login)
        }
    }
}
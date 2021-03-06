package com.isaiasbueno.projeto_picpay_clone_dio_android.ui.pagar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.isaiasbueno.projeto_picpay_clone_dio_android.Componentes
import com.isaiasbueno.projeto_picpay_clone_dio_android.ComponentesViewModel
import com.isaiasbueno.projeto_picpay_clone_dio_android.R
import com.isaiasbueno.projeto_picpay_clone_dio_android.data.Usuario
import com.isaiasbueno.projeto_picpay_clone_dio_android.extension.desaparecer
import com.isaiasbueno.projeto_picpay_clone_dio_android.extension.mostrar
import kotlinx.android.synthetic.main.fragment_pagar.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
class PagarFragment : Fragment() {
    private val componentesViewModel: ComponentesViewModel by sharedViewModel()
    private val pagarViewModel: PagarViewModel by viewModel()
    private val controlador by lazy { findNavController() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pagar, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentesViewModel.temComponentes = Componentes(bottomNavigation = true)
        observarContatos()
        observarLoading()
    }
    private fun observarLoading() {
        pagarViewModel.onLoading.observe(viewLifecycleOwner, Observer { onLoading ->
            if (onLoading) {
                progressBarPagar.mostrar()
                recyclerView.desaparecer()
            } else {
                progressBarPagar.desaparecer()
                recyclerView.mostrar()
            }
        })
    }
    private fun observarContatos() {
        pagarViewModel.contatos.observe(viewLifecycleOwner, Observer {
            configuraRecyclerView(it)
        })
    }
    private fun configuraRecyclerView(usuarios: List<Usuario>) {
        recyclerView.adapter = PagarAdapter(usuarios) {
            val direcao = PagarFragmentDirections.actionNavigationPagarToNavigationTransferencia(it)
            controlador.navigate(direcao)
        }
    }
}
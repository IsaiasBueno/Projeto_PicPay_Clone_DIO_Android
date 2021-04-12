package com.isaiasbueno.projeto_picpay_clone_dio_android.extensions

import java.text.NumberFormat
import java.util.*
fun Double?.formatarMoeda(): String = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this) ?: "R$ 0,00"
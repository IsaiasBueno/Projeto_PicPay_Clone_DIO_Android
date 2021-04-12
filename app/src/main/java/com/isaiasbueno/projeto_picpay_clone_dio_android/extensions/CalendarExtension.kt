package com.isaiasbueno.projeto_picpay_clone_dio_android.extensions

import java.text.SimpleDateFormat
import java.util.*
const val DATE_TIME_FORMAT_US = "yyyy-MM-dd'T'HH:mm:ss"
fun Calendar.formatar(format: String = DATE_TIME_FORMAT_US): String {
    val simpleDateFormat = SimpleDateFormat(format, Locale("pt", "BR"))
    return simpleDateFormat.format(time) ?: ""
}
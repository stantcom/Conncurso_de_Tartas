package com.example.conncurso_de_tartas.core.ex

import android.app.Activity
import android.widget.Toast

fun Activity.showLongToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
package com.d34th.movies.ui.extensions

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnack(message:String){
    Snackbar.make(requireView(),message,Snackbar.LENGTH_SHORT).show()
}
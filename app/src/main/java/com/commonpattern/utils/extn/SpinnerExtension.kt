package com.biteMe.extn

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.databinding.adapters.AdapterViewBindingAdapter

fun Spinner.onSelected(listner: (AdapterView<*>?,Int) -> Unit){
    this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            listner(parent,position)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {

        }
    }
}
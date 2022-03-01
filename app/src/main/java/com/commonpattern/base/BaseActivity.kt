package com.commonpattern.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseActivity<B: ViewBinding>: AppCompatActivity(){
    protected lateinit var binding : B
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= getActivityBinding()
        setContentView(binding.root)
        inits()
        initCtrls()
        observers()
    }

    abstract fun getActivityBinding() : B
    abstract fun inits()
    abstract fun initCtrls()
    abstract fun observers()
}
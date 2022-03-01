package com.commonpattern.ui.fragment.restro

import android.view.LayoutInflater
import android.view.ViewGroup
import com.commonpattern.base.BaseFragment
import com.commonpattern.databinding.FragmentRestroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestroFragment : BaseFragment<FragmentRestroBinding>() {
    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentRestroBinding = FragmentRestroBinding.inflate(inflater,container,false)

    override fun inits() {
    }

    override fun initCtrls() {
    }

    override fun observers() {
    }
}
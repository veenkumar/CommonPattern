package com.commonpattern.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.commonpattern.R
import com.commonpattern.base.BaseActivity
import com.commonpattern.databinding.ActivityMainBinding
import com.commonpattern.repository.remote.BaseUrlHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), View.OnClickListener {
    override fun getActivityBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun inits() {
    }

    override fun initCtrls() {
        binding.mainToolbar.ivBookmark.setOnClickListener(this)
    }

    override fun observers() {
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBookmark ->{
                val navController=Navigation.findNavController(this,R.id.fragment)
                navController.navigateUp()
                navController.navigate(R.id.action_homeFragment_to_restroFragment) }
        }
    }


}
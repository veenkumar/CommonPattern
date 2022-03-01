package com.commonpattern.ui.fragment.home


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.biteMe.extn.text
import com.commonpattern.R
import com.commonpattern.base.BaseFragment
import com.commonpattern.databinding.FragmentHomeBinding
import com.commonpattern.ui.adapter.RestaurantAdapter
import com.commonpattern.utils.application.CustomLoader.Companion.hideLoader
import com.commonpattern.utils.application.CustomLoader.Companion.showLoader
import com.commonpattern.utils.application.ErrorUtil
import com.commonpattern.utils.application.Resource
import com.commonpattern.repository.local.RestaurantDao
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment  : BaseFragment<FragmentHomeBinding>(), View.OnClickListener {

    @Inject
    lateinit var dao: RestaurantDao

    private val viewModel : HomeViewModel by viewModels()
    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater,container,false)

    override fun inits() {
    }

    override fun initCtrls() {
        binding.btnSearch.setOnClickListener(this)
    }

    override fun observers() {
        lifecycleScope.launch {
            viewModel.searchResposne.collect{
                when(it){
                    is Resource.Loading ->{ showLoader(requireActivity()) }
                    is Resource.Success -> {
                        hideLoader()
                        binding.rvRestro.layoutManager=LinearLayoutManager(requireActivity())
                        binding.rvRestro.adapter=RestaurantAdapter(requireActivity(),it.value.restaurants,dao)
                    }
                    is Resource.Failure ->{
                        hideLoader()
                        ErrorUtil.handlerGeneralError(requireActivity(),it.throwable!!)
                    }
                    else -> {  }
                    }
                }
        }
    }

    override fun onClick(v: View?) {
     when(v?.id){
            R.id.btnSearch -> {
                viewModel.search(binding.edSearch.text())
            }
      }
    }
}
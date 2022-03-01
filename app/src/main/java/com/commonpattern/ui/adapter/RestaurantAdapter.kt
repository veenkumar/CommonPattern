package com.commonpattern.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.commonpattern.R
import com.commonpattern.databinding.AdapterRestroBinding
import com.commonpattern.model.response.success.ResturantResponse
import com.commonpattern.utils.application.CustomLoader.Companion.hideLoader
import com.commonpattern.utils.application.CustomLoader.Companion.showLoader
import com.commonpattern.repository.local.RestaurantDao
import kotlinx.coroutines.*


class RestaurantAdapter (var context: Context, var allRestro: MutableList<ResturantResponse>?,var dao : RestaurantDao) : RecyclerView.Adapter<RestaurantAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantAdapter.MyViewHolder {
        val binding = AdapterRestroBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: RestaurantAdapter.MyViewHolder, position: Int) {
        holder.binding.setVariable(BR.data,allRestro?.get(position))
        holder.binding.executePendingBindings()


    }
    override fun getItemCount(): Int = allRestro?.size?:0
    inner class MyViewHolder(var binding: AdapterRestroBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            binding.ivBook.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when(v?.id){
                R.id.ivBook ->{
                    GlobalScope.launch {
                        coroutineScope {
                            withContext(Dispatchers.Main){
                                showLoader(context as Activity)
                            }

                            withContext(Dispatchers.IO){
                                dao.insert(allRestro?.get(adapterPosition)?.restaurant)
                            }
                            withContext(Dispatchers.Main){
                                hideLoader()
                            }
                        }
                    }
                }
            }
        }
    }
}
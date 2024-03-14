package com.example.amanshopping.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.amanshopping.data.Products
import com.example.amanshopping.databinding.SpecialRvItemBinding

class SpecialProductAdapter: RecyclerView.Adapter<SpecialProductAdapter.SpecialProductViewHolder>() {

    inner class SpecialProductViewHolder(private val binding:SpecialRvItemBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("CheckResult")
        fun  bind(products: Products){
            binding.apply{

                    Glide.with(itemView)
                    .load(products.image)
                    .into(imgAd)
                tvAdName.text = products.title
                tvAdPrice.text = products.price

            }
        }
    }

  private  val diffcallback = object: DiffUtil.ItemCallback<Products>(){
        override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,diffcallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialProductViewHolder {
          return SpecialProductViewHolder(
              SpecialRvItemBinding.inflate(
                  LayoutInflater.from(parent.context),parent,false)
              )

    }

    override fun getItemCount(): Int {
        return differ.currentList.size


    }

    override fun onBindViewHolder(holder: SpecialProductViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)
    }
}
package com.nevrmd.barkbark.presentation.breed

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nevrmd.barkbark.databinding.BreedItemBinding
import com.nevrmd.barkbark.domain.BreedEntity

class BreedAdapter(
    private val breedList: List<BreedEntity>,
    private val listener: OnBreedClickListener,
) : RecyclerView.Adapter<BreedAdapter.ViewHolderClass>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val binding = BreedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderClass(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = breedList[position]
        with(holder) {
            tvBreedName.text = currentItem.name
            binding.root.setOnClickListener { listener.onClick(currentItem) }
            binding.root.setOnLongClickListener { listener.onLongClick(currentItem); true }
        }
    }

    override fun getItemCount(): Int = breedList.size

    class ViewHolderClass(val binding: BreedItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvBreedName: TextView = binding.tvBreedName
    }
}

interface OnBreedClickListener {

    fun onClick(breed: BreedEntity)
    fun onLongClick(breed: BreedEntity)
}
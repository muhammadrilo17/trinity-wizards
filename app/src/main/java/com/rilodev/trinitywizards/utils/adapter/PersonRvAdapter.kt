package com.rilodev.trinitywizards.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rilodev.core.domain.model.PersonModel
import com.rilodev.trinitywizards.databinding.ContactItemBinding
import java.lang.StringBuilder

class PersonRvAdapter : ListAdapter<PersonModel, PersonRvAdapter.ViewHolder>(DIFF_CALLBACK) {
    var onItemClick: ((PersonModel) -> Unit)? = null

    inner class ViewHolder(private val binding: ContactItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(personModel: PersonModel) {
            binding.fullName.text = StringBuilder("${personModel.firstName} ${personModel.lastName}")
        }

        init {
            binding.root.setOnClickListener { _ ->
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = ContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PersonModel>() {
            override fun areItemsTheSame(oldItem: PersonModel, newItem: PersonModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PersonModel, newItem: PersonModel): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
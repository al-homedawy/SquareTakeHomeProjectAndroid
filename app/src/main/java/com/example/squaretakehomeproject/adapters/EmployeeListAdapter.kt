package com.example.squaretakehomeproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.squaretakehomeproject.R
import com.example.squaretakehomeproject.databinding.EmployeeListItemBinding
import com.example.squaretakehomeproject.models.EmployeeModel
import com.squareup.picasso.Picasso

class EmployeeListAdapter : ListAdapter<EmployeeModel, EmployeeViewHolder>(DiffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding = EmployeeListItemBinding.inflate(
            LayoutInflater.from(
                parent.context
            )
        )
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(
            getItem(
                position
            )
        )
    }

    companion object DiffUtils : DiffUtil.ItemCallback<EmployeeModel>() {
        override fun areItemsTheSame(oldItem: EmployeeModel, newItem: EmployeeModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: EmployeeModel, newItem: EmployeeModel): Boolean {
            return oldItem.uuid == newItem.uuid &&
                    oldItem.fullName == newItem.fullName &&
                    oldItem.biography == newItem.biography &&
                    oldItem.emailAddress == newItem.emailAddress &&
                    oldItem.phoneNumber == newItem.phoneNumber &&
                    oldItem.photoUrlLarge == newItem.photoUrlLarge &&
                    oldItem.photoUrlSmall == newItem.photoUrlSmall &&
                    oldItem.team == newItem.team &&
                    oldItem.type == newItem.type
        }

    }
}

class EmployeeViewHolder(
    private val binding: EmployeeListItemBinding
) : ViewHolder(binding.root) {

    fun bind(model: EmployeeModel) {
        Picasso
            .get()
            .load(model.photoUrlSmall)
            .placeholder(R.drawable.ic_photo_placeholder)
            .into(binding.employeePhoto)
        binding.employeeName.text = model.fullName
        binding.employeeTeam.text = model.team?.value
        binding.employeeType.text = model.type?.value
    }
}
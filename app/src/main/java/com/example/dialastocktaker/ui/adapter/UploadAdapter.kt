package com.example.dialastocktaker.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dialastocktaker.databinding.ItemUploadBinding
import com.example.dialastocktaker.data.model.UploadItem
import java.text.SimpleDateFormat
import java.util.Locale

class UploadAdapter(private val onUploadClick: (String) -> Unit) : ListAdapter<UploadItem, UploadAdapter.UploadViewHolder>(UploadDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UploadViewHolder {
        val binding = ItemUploadBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UploadViewHolder(binding, onUploadClick)
    }

    override fun onBindViewHolder(holder: UploadViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class UploadViewHolder(
        private val binding: ItemUploadBinding,
        private val onUploadClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(upload: UploadItem) {
            val dateFormat = SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault())

            // User info
            binding.tvName.text = upload.name
            binding.tvRole.text = upload.role
            binding.tvContactNumber.text = upload.contactNumber
            binding.tvLoginCode.text = upload.loginCode

            // Upload info
            binding.tvUploadId.text = upload.id
            binding.tvJobId.text = "Job: ${upload.jobId}"
            binding.tvLocation.text = upload.location
            binding.tvTimestamp.text = dateFormat.format(upload.timestamp)
            binding.tvItemCount.text = "${upload.itemCount} items"
            binding.tvStatus.text = upload.status

            // Status color
            val statusColor = when (upload.status) {
                "Pending" -> android.graphics.Color.parseColor("#f39c12") // Orange
                "Uploaded" -> android.graphics.Color.parseColor("#2ecc71") // Green
                "Failed" -> android.graphics.Color.parseColor("#e74c3c") // Red
                else -> android.graphics.Color.GRAY
            }
            binding.tvStatus.setTextColor(statusColor)

            // Upload button
            if (upload.status == "Pending") {
                binding.btnUpload.visibility = android.view.View.VISIBLE
                binding.btnUpload.setOnClickListener {
                    onUploadClick(upload.id)
                }
            } else {
                binding.btnUpload.visibility = android.view.View.GONE
            }
        }

    }

    class UploadDiffCallback : DiffUtil.ItemCallback<UploadItem>() {
        override fun areItemsTheSame(oldItem: UploadItem, newItem: UploadItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UploadItem, newItem: UploadItem): Boolean {
            return oldItem == newItem
        }
    }
}

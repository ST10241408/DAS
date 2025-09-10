package com.example.dialastocktaker.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dialastocktaker.databinding.ItemJobBinding
import com.example.dialastocktaker.data.model.JobItem
import java.text.SimpleDateFormat
import java.util.Locale

class JobAdapter : ListAdapter<JobItem, JobAdapter.JobViewHolder>(JobDiffCallback()), Filterable {
    private var originalList = listOf<JobItem>()
    private var statusFilter: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = ItemJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: List<JobItem>?) {
        originalList = list ?: emptyList()
        filter.filter(null)
    }

    fun setStatusFilter(status: String?) {
        statusFilter = status
        filter.filter(null)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.lowercase(Locale.getDefault())

                val filteredList = originalList.filter { job ->
                    val matchesQuery = query.isNullOrEmpty() ||
                            job.id.lowercase(Locale.getDefault()).contains(query) ||
                            job.location.lowercase(Locale.getDefault()).contains(query)

                    val matchesStatus = statusFilter.isNullOrEmpty() || job.status == statusFilter

                    matchesQuery && matchesStatus
                }

                val results = FilterResults()
                results.values = filteredList
                return results
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                super@JobAdapter.submitList(results?.values as? List<JobItem> ?: emptyList())
            }
        }
    }

    class JobViewHolder(private val binding: ItemJobBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(job: JobItem) {
            val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

            binding.tvJobId.text = job.id
            binding.tvLocation.text = job.location
            binding.tvDate.text = dateFormat.format(job.date)
            binding.tvTeam.text = job.team
            binding.tvStatus.text = job.status

            // Set status color
            val context = binding.root.context
            val statusColor = when (job.status) {
                "Scheduled" -> android.graphics.Color.parseColor("#3498db") // Blue
                "In Progress" -> android.graphics.Color.parseColor("#f39c12") // Orange
                "Completed" -> android.graphics.Color.parseColor("#2ecc71") // Green
                else -> android.graphics.Color.GRAY
            }
            binding.tvStatus.setTextColor(statusColor)
        }
    }

    class JobDiffCallback : DiffUtil.ItemCallback<JobItem>() {
        override fun areItemsTheSame(oldItem: JobItem, newItem: JobItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: JobItem, newItem: JobItem): Boolean {
            return oldItem == newItem
        }
    }
}

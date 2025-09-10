package com.example.dialastocktaker.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dialastocktaker.databinding.ItemReportBinding
import com.example.dialastocktaker.data.model.ReportItem
import java.text.SimpleDateFormat
import java.util.Locale

class ReportAdapter : ListAdapter<ReportItem, ReportAdapter.ReportViewHolder>(ReportDiffCallback()), Filterable {
    private var originalList = listOf<ReportItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val binding = ItemReportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReportViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: List<ReportItem>?) {
        originalList = list ?: emptyList()
        super.submitList(list)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.lowercase(Locale.getDefault())

                val filteredList = if (query.isNullOrEmpty()) {
                    originalList
                } else {
                    originalList.filter { report ->
                        report.title.lowercase(Locale.getDefault()).contains(query) ||
                                report.id.lowercase(Locale.getDefault()).contains(query) ||
                                report.jobId.lowercase(Locale.getDefault()).contains(query)
                    }
                }

                val results = FilterResults()
                results.values = filteredList
                return results
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                super@ReportAdapter.submitList(results?.values as? List<ReportItem> ?: emptyList())
            }
        }
    }

    class ReportViewHolder(private val binding: ItemReportBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(report: ReportItem) {
            val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

            binding.tvReportTitle.text = report.title
            binding.tvReportId.text = report.id
            binding.tvJobId.text = "Job: ${report.jobId}"
            binding.tvDate.text = dateFormat.format(report.date)
            binding.tvFileSize.text = report.fileSize

            binding.btnDownload.setOnClickListener {
                Toast.makeText(binding.root.context, "Downloading ${report.title}...", Toast.LENGTH_SHORT).show()
            }

            binding.btnView.setOnClickListener {
                Toast.makeText(binding.root.context, "Viewing ${report.title}...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    class ReportDiffCallback : DiffUtil.ItemCallback<ReportItem>() {
        override fun areItemsTheSame(oldItem: ReportItem, newItem: ReportItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ReportItem, newItem: ReportItem): Boolean {
            return oldItem == newItem
        }
    }
}

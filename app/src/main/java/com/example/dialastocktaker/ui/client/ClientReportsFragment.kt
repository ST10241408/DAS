package com.example.dialastocktaker.ui.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dialastocktaker.databinding.FragmentClientReportsBinding
import com.example.dialastocktaker.ui.adapter.ReportAdapter
import com.example.dialastocktaker.data.model.ReportItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ClientReportsFragment : Fragment() {
    private var _binding: FragmentClientReportsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ReportAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientReportsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadMockData()

        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = ReportAdapter()
        binding.recyclerViewReports.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewReports.adapter = adapter
    }

    private fun loadMockData() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val reports = listOf(
            ReportItem(
                id = "REP-2023-045",
                title = "Main Warehouse Inventory Report",
                jobId = "ST-2023-045",
                date = dateFormat.parse("2023-05-16") ?: Date(),
                fileSize = "2.4 MB"
            ),
            ReportItem(
                id = "REP-2023-042",
                title = "Main Warehouse Variance Analysis",
                jobId = "ST-2023-042",
                date = dateFormat.parse("2023-04-29") ?: Date(),
                fileSize = "1.8 MB"
            ),
            ReportItem(
                id = "REP-2023-040",
                title = "Quarterly Inventory Summary",
                jobId = "ST-2023-040",
                date = dateFormat.parse("2023-04-15") ?: Date(),
                fileSize = "3.2 MB"
            ),
            ReportItem(
                id = "REP-2023-038",
                title = "Retail Store #8 Inventory Report",
                jobId = "ST-2023-038",
                date = dateFormat.parse("2023-03-22") ?: Date(),
                fileSize = "1.5 MB"
            ),
            ReportItem(
                id = "REP-2023-035",
                title = "Distribution Center Inventory Report",
                jobId = "ST-2023-035",
                date = dateFormat.parse("2023-03-10") ?: Date(),
                fileSize = "2.1 MB"
            )
        )

        adapter.submitList(reports)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

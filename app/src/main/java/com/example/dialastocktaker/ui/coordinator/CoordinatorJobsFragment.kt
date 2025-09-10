package com.example.dialastocktaker.ui.coordinator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dialastocktaker.databinding.FragmentCoordinatorJobsBinding
import com.example.dialastocktaker.ui.adapter.JobAdapter
import com.example.dialastocktaker.data.model.JobItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CoordinatorJobsFragment : Fragment() {
    private var _binding: FragmentCoordinatorJobsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: JobAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoordinatorJobsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadMockData()

        binding.btnAssignTeam.setOnClickListener {
            Toast.makeText(requireContext(), "Assign Team functionality would appear here", Toast.LENGTH_SHORT).show()
        }

        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })

        binding.chipGroup.setOnCheckedStateChangeListener { _, _ ->
            filterJobs()
        }
    }

    private fun setupRecyclerView() {
        adapter = JobAdapter()
        binding.recyclerViewJobs.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewJobs.adapter = adapter
    }

    private fun loadMockData() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val jobs = listOf(
            JobItem(
                id = "ST-2023-045",
                location = "Main Warehouse, 123 Storage St",
                date = dateFormat.parse("2023-05-15") ?: Date(),
                team = "Team Alpha",
                status = "Completed"
            ),
            JobItem(
                id = "ST-2023-048",
                location = "Retail Store #12, 456 Market Ave",
                date = dateFormat.parse("2023-05-20") ?: Date(),
                team = "Team Beta",
                status = "In Progress"
            ),
            JobItem(
                id = "ST-2023-052",
                location = "Distribution Center, 789 Logistics Blvd",
                date = dateFormat.parse("2023-06-01") ?: Date(),
                team = "Team Charlie",
                status = "Scheduled"
            ),
            JobItem(
                id = "ST-2023-055",
                location = "Outlet Store #5, 321 Discount Ln",
                date = dateFormat.parse("2023-06-10") ?: Date(),
                team = "Team Delta",
                status = "Scheduled"
            )
        )

        adapter.submitList(jobs)
    }

    private fun filterJobs() {
        val filter = when {
            binding.chipScheduled.isChecked -> "Scheduled"
            binding.chipInProgress.isChecked -> "In Progress"
            binding.chipCompleted.isChecked -> "Completed"
            else -> null
        }

        adapter.setStatusFilter(filter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

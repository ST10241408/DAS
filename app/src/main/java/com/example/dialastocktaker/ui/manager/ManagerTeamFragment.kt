package com.example.dialastocktaker.ui.manager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dialastocktaker.databinding.FragmentManagerTeamBinding
import com.example.dialastocktaker.ui.adapter.UserAdapter
import com.example.dialastocktaker.data.model.UserItem

class ManagerTeamFragment : Fragment() {
    private var _binding: FragmentManagerTeamBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManagerTeamBinding.inflate(inflater, container, false)
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

        binding.chipGroup.setOnCheckedStateChangeListener { _, _ ->
            filterUsers()
        }
    }

    private fun setupRecyclerView() {
        adapter = UserAdapter { userId, email ->
            Toast.makeText(requireContext(), "User ID: $userId, Email: $email clicked", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerViewTeam.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTeam.adapter = adapter
    }

    private fun loadMockData() {
        val users = listOf(
            UserItem(
                id = 3,
                name = "Coordinator User",
                role = "coordinator",
                contactNumber = "3456789012",
                loginCode = "CRD-0011223344",
                email = "coordinator@example.com",
                status = "Active"
            ),
            UserItem(
                id = 4,
                name = "Scanner User",
                role = "scanner",
                contactNumber = "4567890123",
                loginCode = "SCN-0987654321",
                email = "scanner@example.com",
                status = "Active"
            ),
            UserItem(
                id = 5,
                name = "Stocktaker User",
                role = "stocktaker",
                contactNumber = "5678901234",
                loginCode = "STK-1234567890",
                email = "stocktaker@example.com",
                status = "Active"
            ),
            UserItem(
                id = 6,
                name = "Group Leader",
                role = "stocktaker",
                contactNumber = "6789012345",
                loginCode = "STK-3456789012",
                email = "groupleader@example.com",
                status = "Active"
            ),
            UserItem(
                id = 9,
                name = "Sarah Johnson",
                role = "stocktaker",
                contactNumber = "9012345678",
                loginCode = "STK-5678901234",
                email = "sarah.johnson@example.com",
                status = "Active"
            ),
            UserItem(
                id = 10,
                name = "Michael Brown",
                role = "scanner",
                contactNumber = "0123456789",
                loginCode = "SCN-6789012345",
                email = "michael.brown@example.com",
                status = "Active"
            )
        )

        adapter.submitList(users)
    }



    private fun filterUsers() {
        val roleFilter = when {
            binding.chipCoordinator.isChecked -> "coordinator"
            binding.chipScanner.isChecked -> "scanner"
            binding.chipStocktaker.isChecked -> "stocktaker"
            else -> null
        }

        adapter.setRoleFilter(roleFilter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

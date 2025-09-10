package com.example.dialastocktaker.ui.coordinator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dialastocktaker.databinding.FragmentCoordinatorTeamBinding
import com.example.dialastocktaker.ui.adapter.UserAdapter
import com.example.dialastocktaker.data.model.UserItem

class CoordinatorTeamFragment : Fragment() {
    private var _binding: FragmentCoordinatorTeamBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoordinatorTeamBinding.inflate(inflater, container, false)
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
                id = 4,
                name = "Scanner User",
                role = "scanner",
                contactNumber = "4567890123",
                loginCode = "SCN-0987654321",
                status = "Active",
                email = "scanner@example.com"
            ),
            UserItem(
                id = 5,
                name = "Stocktaker User",
                role = "stocktaker",
                contactNumber = "5678901234",
                loginCode = "STK-1234567890",
                status = "Active",
                email = "stocktaker@example.com"
            ),
            UserItem(
                id = 6,
                name = "Group Leader",
                role = "stocktaker",
                contactNumber = "6789012345",
                loginCode = "STK-3456789012",
                status = "Active",
                email = "leader@example.com"
            ),
            UserItem(
                id = 9,
                name = "Sarah Johnson",
                role = "stocktaker",
                contactNumber = "9012345678",
                loginCode = "STK-5678901234",
                status = "Active",
                email = "sarah.johnson@example.com"
            ),
            UserItem(
                id = 10,
                name = "Michael Brown",
                role = "scanner",
                contactNumber = "0123456789",
                loginCode = "SCN-6789012345",
                status = "Active",
                email = "michael.brown@example.com"
            )
        )

        adapter.submitList(users)
    }


    private fun filterUsers() {
        val roleFilter = when {
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

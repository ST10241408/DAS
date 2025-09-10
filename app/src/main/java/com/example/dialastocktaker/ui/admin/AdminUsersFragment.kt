package com.example.dialastocktaker.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dialastocktaker.databinding.FragmentAdminUsersBinding
import com.example.dialastocktaker.ui.adapter.UserAdapter
import com.example.dialastocktaker.data.model.UserItem

class AdminUsersFragment : Fragment() {
    private var _binding: FragmentAdminUsersBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadMockData()

        binding.btnAddUser.setOnClickListener {
            Toast.makeText(requireContext(), "Add User functionality would appear here", Toast.LENGTH_SHORT).show()
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
            filterUsers()
        }
    }

    private fun setupRecyclerView() {
        adapter = UserAdapter { userId, email ->
            Toast.makeText(requireContext(), "User ID: $userId, Email: $email clicked", Toast.LENGTH_SHORT).show()
        }

        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUsers.adapter = adapter
    }

    private fun loadMockData() {
        val users = listOf(
            UserItem(
                id = 1,
                name = "Admin User",
                role = "admin",
                contactNumber = "1234567890",
                loginCode = "ADM-00000001",
                status = "Active",
                email = "admin@example.com"
            ),
            UserItem(
                id = 2,
                name = "Manager User",
                role = "manager",
                contactNumber = "2345678901",
                loginCode = "MGR-00000022",
                status = "Active",
                email = "manager@example.com"
            ),
            UserItem(
                id = 3,
                name = "Coordinator User",
                role = "coordinator",
                contactNumber = "3456789012",
                loginCode = "CRD-0011223344",
                status = "Active",
                email = "coordinator@example.com"
            ),
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
                id = 7,
                name = "Client User",
                role = "client",
                contactNumber = "7890123456",
                loginCode = "CLT-00000001",
                status = "Active",
                email = "client@example.com"
            ),
            UserItem(
                id = 8,
                name = "New Applicant",
                role = "stocktaker",
                contactNumber = "8901234567",
                loginCode = "STK-9876543210",
                status = "Pending",
                email = "applicant@example.com"
            )
        )

        adapter.submitList(users)
    }

    private fun filterUsers() {
        val roleFilter = when {
            binding.chipAdmin.isChecked -> "admin"
            binding.chipManager.isChecked -> "manager"
            binding.chipCoordinator.isChecked -> "coordinator"
            binding.chipScanner.isChecked -> "scanner"
            binding.chipStocktaker.isChecked -> "stocktaker"
            binding.chipClient.isChecked -> "client"
            else -> null
        }

        adapter.setRoleFilter(roleFilter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

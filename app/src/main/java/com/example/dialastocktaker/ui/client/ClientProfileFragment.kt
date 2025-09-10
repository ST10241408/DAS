package com.example.dialastocktaker.ui.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dialastocktaker.databinding.FragmentClientProfileBinding

class ClientProfileFragment : Fragment() {
    private var _binding: FragmentClientProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load mock data
        binding.etName.setText("Client User")
        binding.etEmail.setText("client@example.com")
        binding.etPhone.setText("7890123456")
        binding.etCompany.setText("ABC Retail Ltd")
        binding.etAddress.setText("123 Business Park, Suite 456")

        binding.switchEmailNotifications.isChecked = true
        binding.switchDarkMode.isChecked = false

        binding.btnSaveProfile.setOnClickListener {
            Toast.makeText(requireContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show()
        }

        binding.btnChangePassword.setOnClickListener {
            // Show change password dialog
            Toast.makeText(requireContext(), "Password change functionality would appear here", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

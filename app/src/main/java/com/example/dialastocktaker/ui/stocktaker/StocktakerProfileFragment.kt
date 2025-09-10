package com.example.dialastocktaker.ui.stocktaker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dialastocktaker.databinding.FragmentStocktakerProfileBinding

class StocktakerProfileFragment : Fragment() {
    private var _binding: FragmentStocktakerProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStocktakerProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load mock data
        binding.etName.setText("Stocktaker User")
        binding.etEmail.setText("stocktaker@example.com")
        binding.etPhone.setText("5678901234")
        binding.etLoginCode.setText("STK-1234567890")

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

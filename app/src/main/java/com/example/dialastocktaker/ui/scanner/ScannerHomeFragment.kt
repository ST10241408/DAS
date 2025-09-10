package com.example.dialastocktaker.ui.scanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dialastocktaker.databinding.FragmentScannerHomeBinding

class ScannerHomeFragment : Fragment() {
    private var _binding: FragmentScannerHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScannerHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup UI with mock data
        binding.tvTodayJobsCount.text = "1"
        binding.tvScansCompletedCount.text = "156"
        binding.tvPendingUploadsCount.text = "3"

        // Recent activity
        val activities = listOf(
            "Scanned 42 items at Retail Store #12",
            "Uploaded scan data for Job #ST-2023-045",
            "New job assigned for June 1st",
            "Scan device battery low - please charge",
            "Updated scanning procedure available"
        )

        val activityList = binding.layoutRecentActivity
        activities.forEach { activity ->
            val activityView = layoutInflater.inflate(
                com.example.dialastocktaker.R.layout.item_activity,
                activityList,
                false
            )
            activityView.findViewById<android.widget.TextView>(com.example.dialastocktaker.R.id.tvActivity).text = activity
            activityList.addView(activityView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

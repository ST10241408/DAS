package com.example.dialastocktaker.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dialastocktaker.databinding.FragmentAdminHomeBinding

class AdminHomeFragment : Fragment() {
    private var _binding: FragmentAdminHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup UI with mock data
        binding.tvActiveJobsCount.text = "12"
        binding.tvTotalUsersCount.text = "48"
        binding.tvPendingRequestsCount.text = "5"

        // Recent activity
        val activities = listOf(
            "New user registration: John Smith (Stocktaker)",
            "Job #ST-2023-048 status updated to 'In Progress'",
            "New message from Client: ABC Retail Ltd",
            "Report for Job #ST-2023-045 has been generated",
            "New stocktake request from XYZ Distribution"
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

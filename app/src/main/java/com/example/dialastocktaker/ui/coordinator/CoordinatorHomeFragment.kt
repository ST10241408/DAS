package com.example.dialastocktaker.ui.coordinator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dialastocktaker.databinding.FragmentCoordinatorHomeBinding

class CoordinatorHomeFragment : Fragment() {
    private var _binding: FragmentCoordinatorHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoordinatorHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup UI with mock data
        binding.tvTodayJobsCount.text = "2"
        binding.tvTeamMembersCount.text = "12"
        binding.tvPendingTasksCount.text = "5"

        // Recent activity
        val activities = listOf(
            "Job #ST-2023-048 started at Retail Store #12",
            "New team member check-in: Michael Brown",
            "Attendance updated for Team Beta",
            "Job #ST-2023-045 completed successfully",
            "New job assigned to your team for June 1st"
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

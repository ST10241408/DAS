package com.example.dialastocktaker.ui.manager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dialastocktaker.databinding.FragmentManagerHomeBinding

class ManagerHomeFragment : Fragment() {
    private var _binding: FragmentManagerHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManagerHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup UI with mock data
        binding.tvActiveJobsCount.text = "8"
        binding.tvTeamMembersCount.text = "24"
        binding.tvCompletedJobsCount.text = "42"

        // Recent activity
        val activities = listOf(
            "Job #ST-2023-048 status updated to 'In Progress'",
            "New team member assigned: Sarah Johnson",
            "Report for Job #ST-2023-045 has been generated",
            "Team performance review scheduled for next week",
            "New stocktake request assigned to your team"
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

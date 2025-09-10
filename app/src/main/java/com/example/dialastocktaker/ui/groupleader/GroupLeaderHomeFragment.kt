package com.example.dialastocktaker.ui.groupleader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dialastocktaker.databinding.FragmentGroupLeaderHomeBinding

class GroupLeaderHomeFragment : Fragment() {
    private var _binding: FragmentGroupLeaderHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGroupLeaderHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup UI with mock data
        binding.tvTodayJobsCount.text = "1"
        binding.tvTeamMembersCount.text = "5"
        binding.tvCompletedJobsCount.text = "12"

        // Recent activity
        val activities = listOf(
            "Team assigned to Job #ST-2023-048 at Retail Store #12",
            "New message from Admin",
            "Team performance review completed",
            "Job #ST-2023-045 completed successfully",
            "New team member added: Sarah Johnson"
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

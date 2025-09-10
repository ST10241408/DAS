package com.example.dialastocktaker.ui.stocktaker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dialastocktaker.databinding.FragmentStocktakerHomeBinding

class StocktakerHomeFragment : Fragment() {
    private var _binding: FragmentStocktakerHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStocktakerHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup UI with mock data
        binding.tvTodayJobsCount.text = "1"
        binding.tvCompletedJobsCount.text = "8"
        binding.tvUnreadMessagesCount.text = "2"

        // Recent activity
        val activities = listOf(
            "Assigned to Job #ST-2023-048 at Retail Store #12",
            "Completed Job #ST-2023-045 at Main Warehouse",
            "New message from Coordinator",
            "Schedule updated for next week",
            "Performance review scheduled for next month"
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

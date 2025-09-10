package com.example.dialastocktaker.ui.manager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dialastocktaker.databinding.FragmentManagerMessagesBinding
import com.example.dialastocktaker.ui.adapter.MessageAdapter
import com.example.dialastocktaker.data.model.MessageItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ManagerMessagesFragment : Fragment() {
    private var _binding: FragmentManagerMessagesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManagerMessagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadMockData()

        binding.btnSend.setOnClickListener {
            val message = binding.etMessage.text.toString()
            if (message.isNotEmpty()) {
                val newMessage = MessageItem(
                    id = System.currentTimeMillis(),
                    senderId = 2, // Manager ID
                    senderName = "You",
                    receiverId = 1, // Admin ID
                    receiverName = "Admin",
                    content = message,
                    timestamp = Date(),
                    isFromCurrentUser = true
                )

                val currentList = adapter.currentList.toMutableList()
                currentList.add(0, newMessage)
                adapter.submitList(currentList)

                binding.etMessage.text.clear()
                binding.recyclerViewMessages.smoothScrollToPosition(0)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = MessageAdapter()
        binding.recyclerViewMessages.layoutManager = LinearLayoutManager(requireContext()).apply {
            reverseLayout = true
            stackFromEnd = true
        }
        binding.recyclerViewMessages.adapter = adapter
    }

    private fun loadMockData() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val messages = listOf(
            MessageItem(
                id = 5,
                senderId = 1,
                senderName = "Admin",
                receiverId = 2,
                receiverName = "Manager",
                content = "Please review the team performance for last month and prepare a report.",
                timestamp = dateFormat.parse("2023-05-25 14:30") ?: Date(),
                isFromCurrentUser = false
            ),
            MessageItem(
                id = 4,
                senderId = 2,
                senderName = "Manager",
                receiverId = 1,
                receiverName = "Admin",
                content = "I've assigned Team Beta to the retail store stocktake next week.",
                timestamp = dateFormat.parse("2023-05-24 10:15") ?: Date(),
                isFromCurrentUser = true
            ),
            MessageItem(
                id = 3,
                senderId = 3,
                senderName = "Coordinator",
                receiverId = 2,
                receiverName = "Manager",
                content = "We need two more stocktakers for the job on June 1st.",
                timestamp = dateFormat.parse("2023-05-23 16:45") ?: Date(),
                isFromCurrentUser = false
            ),
            MessageItem(
                id = 2,
                senderId = 2,
                senderName = "Manager",
                receiverId = 3,
                receiverName = "Coordinator",
                content = "I'll review the staffing needs and get back to you tomorrow.",
                timestamp = dateFormat.parse("2023-05-23 17:20") ?: Date(),
                isFromCurrentUser = true
            ),
            MessageItem(
                id = 1,
                senderId = 1,
                senderName = "Admin",
                receiverId = 2,
                receiverName = "Manager",
                content = "New client onboarded: XYZ Distribution. They'll need a stocktake next month.",
                timestamp = dateFormat.parse("2023-05-20 09:30") ?: Date(),
                isFromCurrentUser = false
            )
        )

        adapter.submitList(messages)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.example.dialastocktaker.ui.coordinator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dialastocktaker.databinding.FragmentCoordinatorMessagesBinding
import com.example.dialastocktaker.ui.adapter.MessageAdapter
import com.example.dialastocktaker.data.model.MessageItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CoordinatorMessagesFragment : Fragment() {
    private var _binding: FragmentCoordinatorMessagesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoordinatorMessagesBinding.inflate(inflater, container, false)
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
                    senderId = 3, // Coordinator ID
                    senderName = "You",
                    receiverId = 2, // Manager ID
                    receiverName = "Manager",
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
                senderId = 2,
                senderName = "Manager",
                receiverId = 3,
                receiverName = "Coordinator",
                content = "I'll review the staffing needs and get back to you tomorrow.",
                timestamp = dateFormat.parse("2023-05-23 17:20") ?: Date(),
                isFromCurrentUser = false
            ),
            MessageItem(
                id = 4,
                senderId = 3,
                senderName = "Coordinator",
                receiverId = 2,
                receiverName = "Manager",
                content = "We need two more stocktakers for the job on June 1st.",
                timestamp = dateFormat.parse("2023-05-23 16:45") ?: Date(),
                isFromCurrentUser = true
            ),
            MessageItem(
                id = 3,
                senderId = 5,
                senderName = "Stocktaker User",
                receiverId = 3,
                receiverName = "Coordinator",
                content = "I'll be 15 minutes late to tomorrow's job due to traffic.",
                timestamp = dateFormat.parse("2023-05-19 18:30") ?: Date(),
                isFromCurrentUser = false
            ),
            MessageItem(
                id = 2,
                senderId = 3,
                senderName = "Coordinator",
                receiverId = 5,
                receiverName = "Stocktaker User",
                content = "Thanks for letting me know. Please check in when you arrive.",
                timestamp = dateFormat.parse("2023-05-19 18:45") ?: Date(),
                isFromCurrentUser = true
            ),
            MessageItem(
                id = 1,
                senderId = 1,
                senderName = "Admin",
                receiverId = 3,
                receiverName = "Coordinator",
                content = "Please ensure all team members are briefed on the new counting procedure.",
                timestamp = dateFormat.parse("2023-05-15 09:30") ?: Date(),
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

package com.example.dialastocktaker.ui.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dialastocktaker.databinding.FragmentClientMessagesBinding
import com.example.dialastocktaker.ui.adapter.MessageAdapter
import com.example.dialastocktaker.data.model.MessageItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ClientMessagesFragment : Fragment() {
    private var _binding: FragmentClientMessagesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientMessagesBinding.inflate(inflater, container, false)
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
                    senderId = 7, // Client ID
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
                receiverId = 7,
                receiverName = "Client User",
                content = "Your next stocktake is scheduled for June 1st. Please confirm if this works for you.",
                timestamp = dateFormat.parse("2023-05-25 14:30") ?: Date(),
                isFromCurrentUser = false
            ),
            MessageItem(
                id = 4,
                senderId = 7,
                senderName = "Client User",
                receiverId = 1,
                receiverName = "Admin",
                content = "Thank you for the report. I have a question about the variance on page 5.",
                timestamp = dateFormat.parse("2023-05-24 10:15") ?: Date(),
                isFromCurrentUser = true
            ),
            MessageItem(
                id = 3,
                senderId = 1,
                senderName = "Admin",
                receiverId = 7,
                receiverName = "Client User",
                content = "The report for your recent stocktake is now available. You can download it from the Reports section.",
                timestamp = dateFormat.parse("2023-05-23 16:45") ?: Date(),
                isFromCurrentUser = false
            ),
            MessageItem(
                id = 2,
                senderId = 3,
                senderName = "Coordinator",
                receiverId = 7,
                receiverName = "Client User",
                content = "We've completed the stocktake at your main warehouse. The team found everything in good order.",
                timestamp = dateFormat.parse("2023-05-20 18:20") ?: Date(),
                isFromCurrentUser = false
            ),
            MessageItem(
                id = 1,
                senderId = 7,
                senderName = "Client User",
                receiverId = 1,
                receiverName = "Admin",
                content = "Hello, I'd like to schedule a stocktake for next month.",
                timestamp = dateFormat.parse("2023-05-15 09:30") ?: Date(),
                isFromCurrentUser = true
            )
        )

        adapter.submitList(messages)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

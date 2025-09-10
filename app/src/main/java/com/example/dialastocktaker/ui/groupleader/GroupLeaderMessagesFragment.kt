package com.example.dialastocktaker.ui.groupleader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dialastocktaker.databinding.FragmentGroupLeaderMessagesBinding
import com.example.dialastocktaker.ui.adapter.MessageAdapter
import com.example.dialastocktaker.data.model.MessageItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GroupLeaderMessagesFragment : Fragment() {
    private var _binding: FragmentGroupLeaderMessagesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGroupLeaderMessagesBinding.inflate(inflater, container, false)
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
                    senderId = 6, // Group Leader ID
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
                receiverId = 6,
                receiverName = "Group Leader",
                content = "Please ensure all team members check in on time tomorrow.",
                timestamp = dateFormat.parse("2023-05-19 14:30") ?: Date(),
                isFromCurrentUser = false
            ),
            MessageItem(
                id = 4,
                senderId = 6,
                senderName = "Group Leader",
                receiverId = 1,
                receiverName = "Admin",
                content = "We've completed the stocktake at the main warehouse. The team found everything in good order.",
                timestamp = dateFormat.parse("2023-05-20 18:20") ?: Date(),
                isFromCurrentUser = true
            ),
            MessageItem(
                id = 3,
                senderId = 5,
                senderName = "Stocktaker User",
                receiverId = 6,
                receiverName = "Group Leader",
                content = "Got it. I'll be there on time.",
                timestamp = dateFormat.parse("2023-05-19 16:25") ?: Date(),
                isFromCurrentUser = false
            ),
            MessageItem(
                id = 2,
                senderId = 6,
                senderName = "Group Leader",
                receiverId = 5,
                receiverName = "Stocktaker User",
                content = "You'll be working in section A3 tomorrow.",
                timestamp = dateFormat.parse("2023-05-19 16:20") ?: Date(),
                isFromCurrentUser = true
            ),
            MessageItem(
                id = 1,
                senderId = 3,
                senderName = "Coordinator",
                receiverId = 6,
                receiverName = "Group Leader",
                content = "Your team has been assigned to the retail store job tomorrow.",
                timestamp = dateFormat.parse("2023-05-19 09:30") ?: Date(),
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

package com.example.dialastocktaker.ui.scanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dialastocktaker.databinding.FragmentScannerMessagesBinding
import com.example.dialastocktaker.ui.adapter.MessageAdapter
import com.example.dialastocktaker.data.model.MessageItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ScannerMessagesFragment : Fragment() {
    private var _binding: FragmentScannerMessagesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScannerMessagesBinding.inflate(inflater, container, false)
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
                    senderId = 4, // Scanner ID
                    senderName = "You",
                    receiverId = 3, // Coordinator ID
                    receiverName = "Coordinator",
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
                senderId = 3,
                senderName = "Coordinator",
                receiverId = 4,
                receiverName = "Scanner",
                content = "Please ensure all scan data is uploaded before leaving the site today.",
                timestamp = dateFormat.parse("2023-05-20 09:15") ?: Date(),
                isFromCurrentUser = false
            ),
            MessageItem(
                id = 4,
                senderId = 4,
                senderName = "Scanner",
                receiverId = 3,
                receiverName = "Coordinator",
                content = "I'll make sure everything is uploaded before we leave.",
                timestamp = dateFormat.parse("2023-05-20 09:20") ?: Date(),
                isFromCurrentUser = true
            ),
            MessageItem(
                id = 3,
                senderId = 6,
                senderName = "Group Leader",
                receiverId = 4,
                receiverName = "Scanner",
                content = "We need to focus on the stockroom after lunch.",
                timestamp = dateFormat.parse("2023-05-20 12:30") ?: Date(),
                isFromCurrentUser = false
            ),
            MessageItem(
                id = 2,
                senderId = 4,
                senderName = "Scanner",
                receiverId = 6,
                receiverName = "Group Leader",
                content = "Got it. I'll head there after my break.",
                timestamp = dateFormat.parse("2023-05-20 12:35") ?: Date(),
                isFromCurrentUser = true
            ),
            MessageItem(
                id = 1,
                senderId = 3,
                senderName = "Coordinator",
                receiverId = 4,
                receiverName = "Scanner",
                content = "Don't forget to charge your scanning device tonight.",
                timestamp = dateFormat.parse("2023-05-19 17:30") ?: Date(),
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

package com.example.dialastocktaker.ui.scanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dialastocktaker.databinding.FragmentScannerUploadsBinding
import com.example.dialastocktaker.ui.adapter.UploadAdapter
import com.example.dialastocktaker.data.model.UploadItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ScannerUploadsFragment : Fragment() {
    private var _binding: FragmentScannerUploadsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: UploadAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScannerUploadsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadMockData()

        binding.btnUploadAll.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Upload All functionality would appear here",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setupRecyclerView() {
        adapter = UploadAdapter { uploadId ->
            Toast.makeText(
                requireContext(),
                "Uploading scan data for $uploadId",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.recyclerViewUploads.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewUploads.adapter = adapter
    }

    private fun loadMockData() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val uploads = listOf(
            UploadItem(
                id = "SCAN-2023-048-1",
                jobId = "ST-2023-048",
                location = "Retail Store #12, Aisle 1-5",
                timestamp = dateFormat.parse("2023-05-20 10:15")?.time
                    ?: System.currentTimeMillis(),
                itemCount = 42,
                status = "Pending",
                name = "John Doe",
                role = "Scanner",
                contactNumber = "0712345678",
                loginCode = "SCN001"
            ),
            UploadItem(
                id = "SCAN-2023-048-2",
                jobId = "ST-2023-048",
                location = "Retail Store #12, Aisle 6-10",
                timestamp = dateFormat.parse("2023-05-20 11:30")?.time
                    ?: System.currentTimeMillis(),
                itemCount = 38,
                status = "Pending",
                name = "John Doe",
                role = "Scanner",
                contactNumber = "0712345678",
                loginCode = "SCN001"
            ),
            UploadItem(
                id = "SCAN-2023-048-3",
                jobId = "ST-2023-048",
                location = "Retail Store #12, Stockroom",
                timestamp = dateFormat.parse("2023-05-20 13:45")?.time
                    ?: System.currentTimeMillis(),
                itemCount = 76,
                status = "Pending",
                name = "John Doe",
                role = "Scanner",
                contactNumber = "0712345678",
                loginCode = "SCN001"
            ),
            UploadItem(
                id = "SCAN-2023-045-1",
                jobId = "ST-2023-045",
                location = "Main Warehouse, Zone A",
                timestamp = dateFormat.parse("2023-05-15 09:20")?.time
                    ?: System.currentTimeMillis(),
                itemCount = 124,
                status = "Uploaded",
                name = "Jane Smith",
                role = "Scanner",
                contactNumber = "0823456789",
                loginCode = "SCN002"
            ),
            UploadItem(
                id = "SCAN-2023-045-2",
                jobId = "ST-2023-045",
                location = "Main Warehouse, Zone B",
                timestamp = dateFormat.parse("2023-05-15 11:45")?.time
                    ?: System.currentTimeMillis(),
                itemCount = 98,
                status = "Uploaded",
                name = "Jane Smith",
                role = "Scanner",
                contactNumber = "0823456789",
                loginCode = "SCN002"
            )
        )

        adapter.submitList(uploads)
    }
}
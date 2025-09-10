package com.example.dialastocktaker.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dialastocktaker.databinding.ItemUserBinding
import com.example.dialastocktaker.data.model.UserItem
import java.util.Locale

class UserAdapter(private val onUserClick: (Long, String) -> Unit) : ListAdapter<UserItem, UserAdapter.UserViewHolder>(
    UserViewHolder.UserDiffCallback()
), Filterable {
    private var originalList = listOf<UserItem>()
    private var roleFilter: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, onUserClick)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: List<UserItem>?) {
        originalList = list ?: emptyList()
        filter.filter(null)
    }

    fun setRoleFilter(role: String?) {
        roleFilter = role
        filter.filter(null)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.lowercase(Locale.getDefault())

                val filteredList = originalList.filter { user ->
                    val matchesQuery = query.isNullOrEmpty() ||
                            user.name.lowercase(Locale.getDefault()).contains(query) ||
                            user.loginCode.lowercase(Locale.getDefault()).contains(query) ||
                            user.contactNumber.contains(query)

                    val matchesRole = roleFilter.isNullOrEmpty() || user.role == roleFilter

                    matchesQuery && matchesRole
                }

                val results = FilterResults()
                results.values = filteredList
                return results
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                super@UserAdapter.submitList(results?.values as? List<UserItem> ?: emptyList())
            }
        }
    }

    class UserViewHolder(
        private val binding: ItemUserBinding,
        private val onUserClick: (Long, String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserItem) {
            binding.tvName.text = user.name
            binding.tvUserEmail.text = user.email
            binding.tvUserLoginCode.text = "Login Code: ${user.loginCode}"
            binding.tvUserRole.text = user.role.replaceFirstChar { it.uppercase() }
            binding.tvUserPhone.text = user.contactNumber
            binding.tvUserStatus.text = user.status

            // Status color
            val statusColor = when (user.status) {
                "Active" -> android.graphics.Color.parseColor("#2ecc71") // Green
                "Pending" -> android.graphics.Color.parseColor("#f39c12") // Orange
                "Inactive" -> android.graphics.Color.parseColor("#e74c3c") // Red
                else -> android.graphics.Color.GRAY
            }
            binding.tvUserStatus.setTextColor(statusColor)

            binding.root.setOnClickListener {
                onUserClick(user.id, user.email)  // pass email too
            }
        }
        class UserDiffCallback : DiffUtil.ItemCallback<UserItem>() {
            override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}

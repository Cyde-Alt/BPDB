package com.example.bpbdapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bpbdapp.databinding.ItemMemoBinding
import java.text.SimpleDateFormat
import java.util.*

class MemoAdapter(private val memos: List<Memo>) :
    RecyclerView.Adapter<MemoAdapter.MemoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val binding = ItemMemoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.bind(memos[position])
    }

    override fun getItemCount() = memos.size

    class MemoViewHolder(private val binding: ItemMemoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(memo: Memo) {
            binding.memoTitle.text = memo.title
            binding.memoMessage.text = memo.message
            binding.memoTimestamp.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date(memo.timestamp))
            binding.memoStatus.text = memo.status

            itemView.setOnClickListener {
                val sharedPref = itemView.context.getSharedPreferences("BPBDApp", Context.MODE_PRIVATE)
                val userRole = sharedPref.getString("user_role", "operator")

                if (userRole == "kepala bidang" && memo.status == "dilaporkan") {
                    val intent = Intent(itemView.context, MemoReviewActivity::class.java)
                    // TODO: Pass memo ID to the activity
                    itemView.context.startActivity(intent)
                } else {
                    val intent = Intent(itemView.context, MemoDetailActivity::class.java)
                    // TODO: Pass memo ID to the activity
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}

package com.example.bpbdapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bpbdapp.databinding.ItemTaskBinding

class TaskAdapter(private val tasks: List<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount() = tasks.size

    class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.taskTitle.text = task.title
            binding.taskLocation.text = task.location
            binding.taskStatus.text = task.status
            binding.disasterType.text = task.disaster_type

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, TaskDetailActivity::class.java)
                intent.putExtra("TASK_ID", task.id)
                itemView.context.startActivity(intent)
            }
        }
    }
}

package com.example.bpbdapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bpbdapp.databinding.ActivityChatBinding
import android.content.Intent
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val database = Firebase.database
    private val storage = Firebase.storage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chatId = intent.getStringExtra("chat_id") ?: return
        val messagesRef = database.getReference("chats").child(chatId).child("messages")

        // TODO: Set up RecyclerView and adapter to listen for new messages

        binding.sendButton.setOnClickListener {
            val messageText = binding.messageInput.text.toString()
            if (messageText.isNotEmpty()) {
                val message = mapOf(
                    "text" to messageText,
                    "sender_id" to "user_id_1", // TODO: Get current user ID
                    "timestamp" to System.currentTimeMillis()
                )
                messagesRef.push().setValue(message)
                binding.messageInput.text.clear()
            }
        }

        binding.attachButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            startActivityForResult(intent, RC_ATTACH_FILE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_ATTACH_FILE && resultCode == RESULT_OK) {
            val fileUri = data?.data
            if (fileUri != null) {
                val storageRef = storage.reference.child("chat_files/${fileUri.lastPathSegment}")
                storageRef.putFile(fileUri)
                    .addOnSuccessListener {
                        // TODO: Send a message with the file URL
                    }
            }
        }
    }

    companion object {
        private const val RC_ATTACH_FILE = 123
    }
}

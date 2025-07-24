package com.example.bpbdapp

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Refreshed token: $token")
        sendRegistrationToServer(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d(TAG, "From: ${remoteMessage.from}")

        // Handle message within 10 seconds
        sendNotification(remoteMessage)
    }

import android.app.PendingIntent
import android.content.Intent

    private fun sendNotification(remoteMessage: RemoteMessage) {
        val title = remoteMessage.notification?.title
        val messageBody = remoteMessage.notification?.body
        val data = remoteMessage.data

        val intent = Intent(this, MainActivity::class.java) // Default intent

        // Check for data payload to create a specific intent
        when (data["type"]) {
            "new_task", "task_approval" -> {
                val taskId = data["task_id"]
                if (taskId != null) {
                    intent.setClass(this, TaskDetailActivity::class.java)
                    intent.putExtra("TASK_ID", taskId)
                }
            }
            // TODO: Add cases for other notification types like 'new_memo', 'chat_message', etc.
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)

        val channelId = "default_channel_id"
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
                "Default Channel",
                NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(System.currentTimeMillis().toInt(), notificationBuilder.build())
    }

    private fun sendRegistrationToServer(token: String) {
        // This should be handled more gracefully, perhaps with a work manager
        // or after login, but for now, we'll make a direct call.
        // This requires a mechanism to know if a user is logged in and to get their auth token.
        // For now, we just log it.
        Log.d(TAG, "Token needs to be sent to server: $token")
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
}

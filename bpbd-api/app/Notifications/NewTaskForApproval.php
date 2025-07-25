<?php

namespace App\Notifications;

use Illuminate\Bus\Queueable;
use Illuminate\Contracts\Queue\ShouldQueue;
use Illuminate\Notifications\Notification;
use Kreait\Firebase\Messaging\CloudMessage;

class NewTaskForApproval extends Notification implements ShouldQueue
{
    use Queueable;

    protected $task;

    public function __construct($task)
    {
        $this->task = $task;
    }

    public function via($notifiable)
    {
        return ['fcm'];
    }

    public function toFcm($notifiable)
    {
        if (!$notifiable->fcm_token) {
            return;
        }

        return CloudMessage::withTarget('token', $notifiable->fcm_token)
            ->withNotification([
                'title' => 'Persetujuan Tugas Baru',
                'body' => "Tugas baru '{$this->task->title}' memerlukan persetujuan Anda.",
            ])
            ->withData([
                'task_id' => $this->task->id,
                'type' => 'task_approval'
            ]);
    }
}

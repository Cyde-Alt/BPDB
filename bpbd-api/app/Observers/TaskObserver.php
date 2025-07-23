<?php

namespace App\Observers;

use App\Models\ActivityLog;
use App\Models\Task;
use Kreait\Firebase\Messaging\CloudMessage;
use Kreait\Firebase\Messaging\Notification;

class TaskObserver
{
    public function created(Task $task)
    {
        $this->logActivity('created', $task);

        // Send notification to pimpinan
        // TODO: Get pimpinan's device token
        $deviceToken = 'pimpinan_device_token';
        $message = CloudMessage::withTarget('token', $deviceToken)
            ->withNotification(Notification::create('Tugas Baru', 'Tugas baru telah dibuat dan perlu persetujuan Anda.'))
            ->withData(['task_id' => $task->id]);

        try {
            $messaging = app('firebase.messaging');
            $messaging->send($message);
        } catch (\Exception $e) {
            // Log error
        }
    }

    public function updated(Task $task)
    {
        $this->logActivity('updated', $task);
    }

    public function deleted(Task $task)
    {
        $this->logActivity('deleted', $task);
    }

    protected function logActivity(string $activity, Task $task)
    {
        ActivityLog::create([
            'user_id' => auth()->id(),
            'activity' => $activity,
            'loggable_id' => $task->id,
            'loggable_type' => Task::class,
        ]);
    }
}

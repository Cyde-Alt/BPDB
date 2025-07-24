<?php

namespace App\Observers;

use App\Models\ActivityLog;
use App\Models\Task;
use Kreait\Firebase\Messaging\CloudMessage;
use Kreait\Firebase\Messaging\Notification;

use App\Models\Member;
use App\Notifications\NewTaskAssigned;
use App\Notifications\NewTaskForApproval;

class TaskObserver
{
    public function created(Task $task)
    {
        $this->logActivity('created', $task);

        // Notify leaders for approval
        $leaders = Member::where('role', 'pimpinan')->get();
        foreach ($leaders as $leader) {
            $leader->notify(new NewTaskForApproval($task));
        }
    }

    public function updated(Task $task)
    {
        $this->logActivity('updated', $task);

        // If the task is approved, notify assigned members
        if ($task->wasChanged('status') && $task->status === 'disetujui') {
            foreach ($task->members as $member) {
                $member->notify(new NewTaskAssigned($task));
            }
        }
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

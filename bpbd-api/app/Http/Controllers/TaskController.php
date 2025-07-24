<?php

namespace App\Http\Controllers;

use App\Http\Requests\StoreTaskRequest;
use App\Http\Requests\UpdateTaskRequest;
use App\Models\Task;
use Illuminate\Http\Request;

class TaskController extends Controller
{
    public function index()
    {
        return Task::with('members')->get();
    }

use Illuminate\Support\Facades\DB;

    public function store(StoreTaskRequest $request)
    {
        return DB::transaction(function () use ($request) {
            $task = Task::create($request->validated());

            if ($request->has('member_ids')) {
                $task->members()->attach($request->member_ids);
            }

            return $task;
        });
    }

    public function show(Task $task)
    {
        return $task->load('members');
    }

    public function update(UpdateTaskRequest $request, Task $task)
    {
        return DB::transaction(function () use ($request, $task) {
            $task->update($request->validated());

            if ($request->has('member_ids')) {
                $task->members()->sync($request->member_ids);
            }

            return $task;
        });
    }

    public function destroy(Task $task)
    {
        $task->delete();

        return response()->json(null, 204);
    }

    public function approve(Request $request, Task $task)
    {
        $task->update([
            'status' => 'disetujui',
            'approved_by' => $request->user()->id,
        ]);

        return response()->json(['message' => 'Task approved']);
    }

    public function reject(Request $request, Task $task)
    {
        $task->update(['status' => 'ditolak']);

        return response()->json(['message' => 'Task rejected']);
    }

    public function report(Request $request, Task $task)
    {
        $request->validate([
            'report' => 'required',
        ]);

        // TODO: Handle file upload
        $task->update([
            'status' => 'dilaporkan',
            'report' => $request->report,
        ]);

        return response()->json(['message' => 'Report submitted']);
    }

    public function archive(Request $request, Task $task)
    {
        $task->update(['status' => 'diarsipkan']);

        return response()->json(['message' => 'Task archived']);
    }
}

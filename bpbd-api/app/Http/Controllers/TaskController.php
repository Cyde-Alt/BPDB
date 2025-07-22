<?php

namespace App\Http\Controllers;

use App\Models\Task;
use Illuminate\Http\Request;

class TaskController extends Controller
{
    public function index()
    {
        return Task::with('members')->get();
    }

    public function store(Request $request)
    {
        $request->validate([
            'title' => 'required',
            'location' => 'required',
            'disaster_type' => 'required',
            'status' => 'required|in:diterima,dikerjakan,selesai',
        ]);

        $task = Task::create($request->all());

        if ($request->has('member_ids')) {
            $task->members()->attach($request->member_ids);
        }

        return $task;
    }

    public function show(Task $task)
    {
        return $task->load('members');
    }

    public function update(Request $request, Task $task)
    {
        $request->validate([
            'title' => 'required',
            'location' => 'required',
            'disaster_type' => 'required',
            'status' => 'required|in:diterima,dikerjakan,selesai',
        ]);

        $task->update($request->all());

        if ($request->has('member_ids')) {
            $task->members()->sync($request->member_ids);
        }

        return $task;
    }

    public function destroy(Task $task)
    {
        $task->delete();

        return response()->json(null, 204);
    }
}

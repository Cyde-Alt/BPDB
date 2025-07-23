<?php

namespace App\Http\Controllers;

use App\Http\Requests\StoreMemoRequest;
use App\Http\Requests\UpdateMemoRequest;
use App\Models\Memo;
use Illuminate\Http\Request;

class MemoController extends Controller
{
    public function index()
    {
        return Memo::all();
    }

    public function store(StoreMemoRequest $request)
    {
        $memo = Memo::create($request->validated());

        if ($request->recipient_type === 'individu' && $request->has('recipient_ids')) {
            $memo->recipients()->attach($request->recipient_ids);
        }

        return $memo;
    }

    public function show(Memo $memo)
    {
        return $memo;
    }

    public function update(UpdateMemoRequest $request, Memo $memo)
    {
        $memo->update($request->validated());

        return $memo;
    }

    public function destroy(Memo $memo)
    {
        $memo->delete();

        return response()->json(null, 204);
    }

    public function confirm(Request $request, Memo $memo)
    {
        $memo->recipients()->updateExistingPivot($request->user()->id, ['read_at' => now()]);

        return response()->json(['message' => 'Memo confirmed']);
    }

    public function report(Request $request, Memo $memo)
    {
        $request->validate([
            'report' => 'required',
        ]);

        // TODO: Handle file upload
        $memo->update(['status' => 'dilaporkan']);

        // TODO: Save report details

        return response()->json(['message' => 'Report submitted']);
    }
}

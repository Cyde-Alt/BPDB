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
        $memo->update([
            'status' => 'dilaporkan',
            'report' => $request->report,
        ]);

        return response()->json(['message' => 'Report submitted']);
    }

    public function approve(Request $request, Memo $memo)
    {
        $memo->update([
            'status' => 'disetujui',
            'approver_id' => $request->user()->id,
        ]);

        return response()->json(['message' => 'Memo approved']);
    }

    public function reject(Request $request, Memo $memo)
    {
        $memo->update(['status' => 'ditolak']);

        return response()->json(['message' => 'Memo rejected']);
    }

    public function forward(Request $request, Memo $memo)
    {
        // TODO: Add logic to forward to pimpinan
        return response()->json(['message' => 'Memo forwarded']);
    }

    public function archive(Request $request, Memo $memo)
    {
        $memo->update(['status' => 'diarsipkan']);

        return response()->json(['message' => 'Memo archived']);
    }
}

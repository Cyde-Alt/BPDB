<?php

namespace App\Http\Controllers;

use App\Models\Memo;
use Illuminate\Http\Request;

class MemoController extends Controller
{
    public function index()
    {
        return Memo::all();
    }

    public function store(Request $request)
    {
        $request->validate([
            'title' => 'required',
            'message' => 'required',
        ]);

        return Memo::create($request->all());
    }

    public function show(Memo $memo)
    {
        return $memo;
    }

    public function update(Request $request, Memo $memo)
    {
        $request->validate([
            'title' => 'required',
            'message' => 'required',
        ]);

        $memo->update($request->all());

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

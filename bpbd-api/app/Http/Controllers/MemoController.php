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
}

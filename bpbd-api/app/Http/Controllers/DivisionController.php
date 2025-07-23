<?php

namespace App\Http\Controllers;

use App\Models\Division;
use Illuminate\Http\Request;

class DivisionController extends Controller
{
    public function index()
    {
        return Division::with('members')->get();
    }

    public function store(Request $request)
    {
        $request->validate([
            'name' => 'required|unique:divisions',
        ]);

        return Division::create($request->all());
    }

    public function show(Division $division)
    {
        $user = auth()->user();
        if ($user->role === 'kepala bidang' && $user->division_id !== $division->id) {
            return response()->json(['message' => 'Unauthorized'], 403);
        }

        return $division->load('members');
    }

    public function update(Request $request, Division $division)
    {
        $request->validate([
            'name' => 'required|unique:divisions,name,' . $division->id,
        ]);

        $division->update($request->all());

        return $division;
    }

    public function destroy(Division $division)
    {
        $division->delete();

        return response()->json(null, 204);
    }
}

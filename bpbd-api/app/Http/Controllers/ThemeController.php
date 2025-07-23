<?php

namespace App\Http\Controllers;

use App\Models\Theme;
use Illuminate\Http\Request;

class ThemeController extends Controller
{
    public function index()
    {
        return Theme::all();
    }

    public function store(Request $request)
    {
        $request->validate([
            'name' => 'required|unique:themes',
            'primary_color' => 'required',
            'secondary_color' => 'required',
        ]);

        return Theme::create($request->all());
    }

    public function show(Theme $theme)
    {
        return $theme;
    }

    public function update(Request $request, Theme $theme)
    {
        $request->validate([
            'name' => 'required|unique:themes,name,' . $theme->id,
            'primary_color' => 'required',
            'secondary_color' => 'required',
        ]);

        $theme->update($request->all());

        return $theme;
    }

    public function destroy(Theme $theme)
    {
        $theme->delete();

        return response()->json(null, 204);
    }

    public function setActive(Request $request, Theme $theme)
    {
        Theme::where('is_active', true)->update(['is_active' => false]);
        $theme->update(['is_active' => true]);

        return response()->json(['message' => 'Theme activated']);
    }

    public function getActive()
    {
        return Theme::where('is_active', true)->first();
    }
}

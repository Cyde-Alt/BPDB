<?php

namespace App\Http\Controllers;

use App\Models\News;
use Illuminate\Http\Request;

class NewsController extends Controller
{
    public function index()
    {
        return News::all();
    }

    public function store(Request $request)
    {
        $request->validate([
            'title' => 'required',
            'summary' => 'required',
        ]);

        return News::create($request->all());
    }

    public function show(News $news)
    {
        return $news;
    }

    public function update(Request $request, News $news)
    {
        $request->validate([
            'title' => 'required',
            'summary' => 'required',
        ]);

        $news->update($request->all());

        return $news;
    }

    public function destroy(News $news)
    {
        $news->delete();

        return response()->json(null, 204);
    }
}

<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class BaseController extends Controller
{
    protected $model;

    public function index()
    {
        return $this->model::all();
    }

    public function show($id)
    {
        return $this->model::findOrFail($id);
    }

    public function destroy($id)
    {
        $this->model::findOrFail($id)->delete();

        return response()->json(null, 204);
    }
}

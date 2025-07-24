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

    public function store(Request $request)
    {
        // Basic validation, can be overridden in child controllers
        $validatedData = $request->validate($this->model->getValidationRules());

        $item = $this->model::create($validatedData);

        return response()->json($item, 201);
    }

    public function show($id)
    {
        return $this->model::findOrFail($id);
    }

    public function update(Request $request, $id)
    {
        $item = $this->model::findOrFail($id);

        // Basic validation, can be overridden in child controllers
        $validatedData = $request->validate($this->model->getValidationRules());

        $item->update($validatedData);

        return response()->json($item, 200);
    }

    public function destroy($id)
    {
        $this->model::findOrFail($id)->delete();

        return response()->json(null, 204);
    }
}

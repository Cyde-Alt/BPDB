<?php

namespace App\Http\Controllers;

use App\Models\Report;
use Illuminate\Http\Request;

class ReportController extends Controller
{
    public function index()
    {
        return Report::all();
    }

    public function store(Request $request)
    {
        $request->validate([
            'reporter_name' => 'required',
            'reporter_phone' => 'required',
            'latitude' => 'required|numeric',
            'longitude' => 'required|numeric',
            'description' => 'required',
        ]);

        return Report::create($request->all());
    }

    public function show(Report $report)
    {
        return $report;
    }

    public function update(Request $request, Report $report)
    {
        $request->validate([
            'status' => 'required|in:diterima,diverifikasi,ditindaklanjuti',
        ]);

        $report->update($request->all());

        return $report;
    }

    public function destroy(Report $report)
    {
        $report->delete();

        return response()->json(null, 204);
    }
}

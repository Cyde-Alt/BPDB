<?php

use App\Http\Controllers\MemberController;
use App\Http\Controllers\MemoController;
use App\Http\Controllers\NewsController;
use App\Http\Controllers\ReportController;
use App\Http\Controllers\TaskController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::middleware(['auth:sanctum', 'role:super admin'])->group(function () {
    Route::apiResource('members', MemberController::class);
});

Route::middleware(['auth:sanctum', 'role:super admin,pimpinan,kepala bidang,sekretaris,bendahara'])->group(function () {
    Route::get('/members', [MemberController::class, 'index']);
    Route::get('/members/{member}', [MemberController::class, 'show']);
});

Route::apiResource('tasks', TaskController::class)->middleware(['auth:sanctum', 'role:super admin,kepala bidang']);
Route::apiResource('memos', MemoController::class)->middleware(['auth:sanctum', 'role:super admin,pimpinan']);
Route::post('/memos/{memo}/confirm', [MemoController::class, 'confirm'])->middleware('auth:sanctum');
Route::post('/memos/{memo}/report', [MemoController::class, 'report'])->middleware('auth:sanctum');
Route::apiResource('news', NewsController::class)->middleware(['auth:sanctum', 'role:super admin,pimpinan']);
Route::apiResource('reports', ReportController::class)->middleware(['auth:sanctum', 'role:super admin,pimpinan']);

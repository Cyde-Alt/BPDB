<?php

use App\Http\Controllers\MemberController;
use App\Http\Controllers\MemoController;
use App\Http\Controllers\NewsController;
use App\Http\Controllers\ReportController;
use App\Http\Controllers\TaskController;
use App\Http\Controllers\ThemeController;
use App\Http\Controllers\DivisionController;
use App\Http\Controllers\FinanceController;
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

Route::apiResource('members', MemberController::class)->middleware(['auth:sanctum', 'role:super admin,sekretaris']);

Route::middleware(['auth:sanctum', 'role:super admin,pimpinan,kepala bidang,sekretaris,bendahara'])->group(function () {
    Route::get('/members', [MemberController::class, 'index']);
    Route::get('/members/{member}', [MemberController::class, 'show']);
});

Route::apiResource('tasks', TaskController::class)->middleware(['auth:sanctum', 'role:super admin,kepala bidang,sekretaris']);
Route::post('/tasks/{task}/approve', [TaskController::class, 'approve'])->middleware(['auth:sanctum', 'role:super admin,pimpinan']);
Route::post('/tasks/{task}/reject', [TaskController::class, 'reject'])->middleware(['auth:sanctum', 'role:super admin,pimpinan']);
Route::post('/tasks/{task}/report', [TaskController::class, 'report'])->middleware('auth:sanctum');
Route::post('/tasks/{task}/archive', [TaskController::class, 'archive'])->middleware(['auth:sanctum', 'role:super admin,sekretaris']);
Route::apiResource('themes', ThemeController::class)->middleware(['auth:sanctum', 'role:super admin']);
Route::post('/themes/{theme}/activate', [ThemeController::class, 'setActive'])->middleware(['auth:sanctum', 'role:super admin']);
Route::get('/themes/active', [ThemeController::class, 'getActive']);
Route::apiResource('memos', MemoController::class)->middleware(['auth:sanctum', 'role:super admin,pimpinan']);
Route::post('/memos/{memo}/confirm', [MemoController::class, 'confirm'])->middleware('auth:sanctum');
Route::post('/memos/{memo}/report', [MemoController::class, 'report'])->middleware('auth:sanctum');
Route::post('/memos/{memo}/approve', [MemoController::class, 'approve'])->middleware(['auth:sanctum', 'role:pimpinan']);
Route::post('/memos/{memo}/reject', [MemoController::class, 'reject'])->middleware(['auth:sanctum', 'role:pimpinan']);
Route::post('/memos/{memo}/forward', [MemoController::class, 'forward'])->middleware(['auth:sanctum', 'role:kepala bidang']);
Route::post('/memos/{memo}/archive', [MemoController::class, 'archive'])->middleware(['auth:sanctum', 'role:sekretaris']);
Route::apiResource('divisions', DivisionController::class)->middleware(['auth:sanctum', 'role:super admin,operator']);
Route::apiResource('news', NewsController::class)->middleware(['auth:sanctum', 'role:super admin,pimpinan,operator']);
Route::apiResource('reports', ReportController::class)->middleware(['auth:sanctum', 'role:super admin,pimpinan']);

Route::prefix('finance')->middleware(['auth:sanctum', 'role:super admin,bendahara,pimpinan'])->group(function () {
    // TODO: Add finance routes
});

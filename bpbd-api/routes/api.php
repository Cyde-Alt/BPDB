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

Route::apiResource('members', MemberController::class);
Route::apiResource('tasks', TaskController::class);
Route::apiResource('memos', MemoController::class);
Route::apiResource('news', NewsController::class);
Route::apiResource('reports', ReportController::class);

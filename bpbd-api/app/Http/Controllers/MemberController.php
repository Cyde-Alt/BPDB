<?php

namespace App\Http\Controllers;

use App\Models\Member;
use Illuminate\Http\Request;

class MemberController extends Controller
{
    public function index()
    {
        return Member::all();
    }

    public function store(Request $request)
    {
        $request->validate([
            'name' => 'required',
            'status' => 'required|in:siaga,bertugas,non-aktif',
        ]);

        return Member::create($request->all());
    }

    public function show(Member $member)
    {
        return $member;
    }

    public function update(Request $request, Member $member)
    {
        $request->validate([
            'name' => 'required',
            'status' => 'required|in:siaga,bertugas,non-aktif',
        ]);

        $member->update($request->all());

        return $member;
    }

    public function destroy(Member $member)
    {
        $member->delete();

        return response()->json(null, 204);
    }

    public function updateFcmToken(Request $request)
    {
        $request->validate([
            'fcm_token' => 'required|string',
        ]);

        $member = auth()->user();
        $member->fcm_token = $request->fcm_token;
        $member->save();

        return response()->json(['message' => 'FCM token updated successfully']);
    }
}

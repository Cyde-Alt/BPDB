<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Memo extends Model
{
    use HasFactory;

    protected $fillable = [
        'title',
        'message',
        'recipient_type',
        'status',
        'creator_id',
        'approver_id',
        'report',
        'attachment_url',
    ];

    public function recipients()
    {
        return $this->belongsToMany(Member::class, 'memo_recipients');
    }

    public static function getValidationRules()
    {
        return [
            'title' => 'required|string|max:255',
            'message' => 'required|string',
            'recipient_type' => 'required|string',
            'status' => 'sometimes|string',
            'creator_id' => 'required|exists:members,id',
        ];
    }
}

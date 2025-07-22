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
    ];

    public function recipients()
    {
        return $this->belongsToMany(Member::class, 'memo_recipients');
    }
}

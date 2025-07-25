<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class ActivityLog extends Model
{
    use HasFactory;

    protected $fillable = [
        'user_id',
        'activity',
        'loggable_id',
        'loggable_type',
    ];

    public function user()
    {
        return $this->belongsTo(Member::class, 'user_id');
    }

    public function loggable()
    {
        return $this->morphTo();
    }
}

<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Task extends Model
{
    use HasFactory;

    protected $fillable = [
        'title',
        'location',
        'disaster_type',
        'status',
        'created_by',
        'approved_by',
        'report',
        'attachment_url',
    ];

    public function members()
    {
        return $this->belongsToMany(Member::class, 'task_assignments');
    }
}

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
    ];

    public function members()
    {
        return $this->belongsToMany(Member::class, 'task_assignments');
    }
}

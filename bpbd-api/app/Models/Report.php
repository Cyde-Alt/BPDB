<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Report extends Model
{
    use HasFactory;

    protected $fillable = [
        'reporter_name',
        'reporter_phone',
        'latitude',
        'longitude',
        'description',
        'image_url',
        'status',
    ];
}

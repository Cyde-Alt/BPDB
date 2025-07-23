<?php

namespace App\Http\Controllers;

use App\Models\News;

class NewsController extends BaseController
{
    public function __construct()
    {
        $this->model = new News();
    }
}

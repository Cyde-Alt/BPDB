<?php

namespace App\Http\Controllers;

use App\Models\Budget;
use Illuminate\Http\Request;

class BudgetController extends BaseController
{
    public function __construct()
    {
        $this->model = new Budget();
    }
}

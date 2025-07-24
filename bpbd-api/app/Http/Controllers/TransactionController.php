<?php

namespace App\Http\Controllers;

use App\Models\Transaction;
use Illuminate\Http\Request;

class TransactionController extends BaseController
{
    public function __construct()
    {
        $this->model = new Transaction();
    }
}

<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class ThemeSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('themes')->insert([
            [
                'name' => 'Default BPBD',
                'primary_color' => '#005A9E',
                'secondary_color' => '#F58220',
                'is_active' => true,
            ],
            [
                'name' => 'Kemerdekaan',
                'primary_color' => '#FF0000',
                'secondary_color' => '#FFFFFF',
                'is_active' => false,
            ],
            [
                'name' => 'Idul Fitri',
                'primary_color' => '#008000',
                'secondary_color' => '#FFD700',
                'is_active' => false,
            ],
        ]);
    }
}

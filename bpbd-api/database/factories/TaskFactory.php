<?php

namespace Database\Factories;

use App\Models\Member;
use Illuminate\Database\Eloquent\Factories\Factory;

class TaskFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            'title' => $this->faker->sentence,
            'location' => $this->faker->address,
            'disaster_type' => $this->faker->word,
            'status' => 'diajukan',
            'created_by' => Member::factory(),
        ];
    }
}

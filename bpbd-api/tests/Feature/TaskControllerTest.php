<?php

namespace Tests\Feature;

use App\Models\Member;
use App\Models\Task;
use Illuminate\Foundation\Testing\RefreshDatabase;
use Tests\TestCase;

class TaskControllerTest extends TestCase
{
    use RefreshDatabase;

    public function test_can_create_task()
    {
        $member = Member::factory()->create();
        $this->actingAs($member);

        $response = $this->postJson('/api/tasks', [
            'title' => 'Test Task',
            'location' => 'Test Location',
            'disaster_type' => 'Test Disaster',
            'status' => 'diajukan',
            'created_by' => $member->id,
        ]);

        $response->assertStatus(201);
        $this->assertDatabaseHas('tasks', ['title' => 'Test Task']);
    }
}

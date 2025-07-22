<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateTasksTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('tasks', function (Blueprint $table) {
            $table->id();
            $table->string('title');
            $table->string('location');
            $table->string('disaster_type');
            $table->enum('status', ['diajukan', 'disetujui', 'ditolak', 'dilaksanakan', 'dilaporkan', 'selesai', 'diarsipkan'])->default('diajukan');
            $table->foreignId('created_by')->constrained('members');
            $table->foreignId('approved_by')->nullable()->constrained('members');
            $table->text('report')->nullable();
            $table->string('attachment_url')->nullable();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('tasks');
    }
}

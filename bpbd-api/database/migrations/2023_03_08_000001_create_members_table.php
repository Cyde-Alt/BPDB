<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateMembersTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('members', function (Blueprint $table) {
            $table->id();
            $table->string('name');
            $table->string('email')->unique();
            $table->timestamp('email_verified_at')->nullable();
            $table->string('password');
            $table->enum('role', ['operator', 'kepala bidang', 'sekretaris', 'bendahara', 'pimpinan', 'super admin', 'anggota'])->default('anggota');
            $table->foreignId('division_id')->nullable()->constrained();
            $table->enum('placement_status', ['diajukan', 'disetujui', 'ditolak'])->nullable();
            $table->enum('status', ['siaga', 'bertugas', 'non-aktif']);
            $table->rememberToken();
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
        Schema::dropIfExists('members');
    }
}

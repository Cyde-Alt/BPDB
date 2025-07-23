<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateMemosTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('memos', function (Blueprint $table) {
            $table->id();
            $table->string('title');
            $table->text('message');
            $table->enum('recipient_type', ['semua', 'individu', 'bidang']);
            $table->foreignId('creator_id')->constrained('members');
            $table->foreignId('approver_id')->nullable()->constrained('members');
            $table->enum('status', ['diajukan', 'disetujui', 'ditolak', 'terkirim', 'dibaca', 'dilaporkan', 'selesai', 'diarsipkan'])->default('diajukan');
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
        Schema::dropIfExists('memos');
    }
}

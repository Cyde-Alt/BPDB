# BPBDApp

Aplikasi Android untuk manajemen anggota Badan Penanggulangan Bencana Daerah (BPBD).

## Fitur

*   **Informasi Cuaca**: Menampilkan informasi cuaca terkini.
*   **Berita**: Menampilkan berita terkini terkait kebencanaan.
*   **Manajemen Anggota**: Menambah, mengubah, dan menghapus data anggota. Menampilkan daftar anggota beserta status (siaga, bertugas, non-aktif).
*   **Manajemen Perintah Tugas**: Membuat perintah tugas baru dengan detail seperti lokasi, jenis bencana, dan anggota yang ditugaskan. Mengirimkan notifikasi kepada anggota yang ditugaskan. Melacak status pengerjaan tugas (diterima, dikerjakan, selesai).
*   **Memo Penugasan Darurat**: Fitur untuk mengirimkan memo singkat dan darurat kepada seluruh atau sebagian anggota. Notifikasi darurat yang menonjol di perangkat anggota.

## Arsitektur

Aplikasi ini terdiri dari dua bagian:

1.  **Aplikasi Android**: Dibangun dengan Kotlin dan arsitektur MVVM. Bertanggung jawab untuk menampilkan antarmuka pengguna dan berinteraksi dengan API.
2.  **API**: Dibangun dengan PHP dan framework Laravel. Bertanggung jawab untuk menghubungkan aplikasi Android dengan database MySQL.

## Cara Menjalankan

### Aplikasi Android

1.  Buka proyek di Android Studio.
2.  Jalankan aplikasi di emulator atau perangkat Android.
3.  Pastikan API berjalan dan dapat diakses oleh aplikasi.
4.  Login dengan akun yang valid.
5.  Gunakan navigasi bawah untuk mengakses fitur-fitur yang tersedia.

### API

1.  Buka proyek di direktori `bpbd-api`.
2.  Jalankan `composer install` untuk menginstal dependensi.
3.  Salin `.env.example` ke `.env` dan konfigurasikan database.
4.  Jalankan `php artisan migrate` untuk membuat tabel di database.
5.  Jalankan `php artisan serve` untuk menjalankan server API.

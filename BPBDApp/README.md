# BPBDApp

Aplikasi Android untuk manajemen anggota Badan Penanggulangan Bencana Daerah (BPBD).

## Fitur

*   **Manajemen Anggota**: Super admin dan sekretaris dapat menambah, mengubah, dan menghapus anggota. Pimpinan dapat melihat daftar anggota dan jabatan.
*   **Manajemen Perintah Tugas**: Alur kerja lengkap untuk perintah tugas, termasuk pengajuan, persetujuan, pelaporan, dan pengarsipan.
*   **Memo Penugasan Darurat**: Alur kerja lengkap untuk memo penugasan darurat, termasuk pengiriman, konfirmasi, pelaporan, dan pelacakan status.
*   **Role dan Hak Akses**: Sistem role dan hak akses untuk setiap pengguna.
*   **Pelaporan dari Masyarakat**: Memungkinkan masyarakat umum untuk melaporkan kejadian bencana.
*   **Informasi Cuaca**: Menampilkan informasi cuaca terkini.
*   **Berita**: Menampilkan berita terkini terkait kebencanaan.

## Role dan Hak Akses

*   **Super Admin**: Memiliki akses penuh ke semua fitur dan data.
*   **Pimpinan**:
    *   Melihat semua data (anggota, tugas, memo, berita, laporan).
    *   Menyetujui atau menolak perintah tugas.
    *   Membuat dan mengirim memo.
*   **Kepala Bidang**:
    *   Melihat anggota dan tugas.
    *   Membuat, mengubah, dan menghapus tugas.
*   **Sekretaris**:
    *   Mengelola anggota (menambah, mengubah, menghapus).
    *   Membuat dan mengajukan perintah tugas.
    *   Melihat memo.
    *   Mengarsipkan tugas.
*   **Bendahara**:
    *   Melihat anggota.
*   **Operator**:
    *   Melihat tugas yang ditugaskan kepadanya.
    *   Mengirimkan laporan tugas.

## Arsitektur

Aplikasi ini terdiri dari dua bagian:

1.  **Aplikasi Android**: Dibangun dengan Kotlin dan arsitektur MVVM. Menggunakan Retrofit untuk komunikasi jaringan.
2.  **API**: Dibangun dengan PHP dan framework Laravel. Bertanggung jawab untuk menghubungkan aplikasi Android dengan database MySQL.

## Cara Menjalankan

### Prasyarat

*   Android Studio
*   XAMPP atau WAMP (atau lingkungan server lokal lainnya dengan PHP, MySQL, dan Composer)
*   Git

### API

1.  **Unduh Kode**: Kloning repositori ini ke komputer Anda.
2.  **Buka Direktori API**: Buka terminal dan arahkan ke direktori `bpbd-api`.
3.  **Instal Dependensi**: Jalankan `composer install`. Jika Anda diminta memasukkan token, lihat [dokumentasi GitHub](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token) untuk membuat personal access token.
4.  **Konfigurasi Database**: Salin `.env.example` ke `.env` dan konfigurasikan koneksi database Anda.
5.  **Buat Tabel**: Jalankan `php artisan migrate` untuk membuat tabel di database Anda.
6.  **Jalankan Server**: Jalankan `php artisan serve` untuk memulai server API.

### Aplikasi Android

1.  **Buka Proyek**: Buka direktori `BPBDApp` di Android Studio.
2.  **Konfigurasi Alamat IP API**: Di dalam kelas `ApiClient`, ubah `BASE_URL` menjadi alamat IP lokal Anda (misalnya, `http://192.168.1.10:8000/api/`).
3.  **Jalankan Aplikasi**: Jalankan aplikasi di emulator Android atau perangkat fisik.

## Pembaruan Aplikasi

*   **Google Play Store**: Cara yang direkomendasikan untuk mendistribusikan dan memperbarui aplikasi.
*   **Pembaruan Dalam Aplikasi**: Implementasikan fitur pembaruan dalam aplikasi untuk memberi tahu pengguna tentang versi baru.
*   **Manual**: Untuk pengembangan, instal versi baru secara manual melalui Android Studio.

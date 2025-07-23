# BPBDApp

Aplikasi Android untuk manajemen anggota Badan Penanggulangan Bencana Daerah (BPBD).

## Fitur

*   **Live Chat**: Komunikasi *real-time* antar pengguna, termasuk *chat* grup, percakapan pribadi, dan pengiriman berkas.
*   **Log Aktivitas**: Semua aktivitas penting dicatat dan dapat dilihat oleh Super Admin.
*   **Notifikasi Komprehensif**: Notifikasi *push* untuk berbagai peristiwa penting, seperti tugas baru, persetujuan, dan laporan.
*   **Memo Penugasan Darurat**: Alur kerja yang kompleks untuk memo, termasuk pembuatan oleh Pimpinan atau Kepala Bidang, persetujuan, konfirmasi, pelaporan, peninjauan, dan pengarsipan.
*   **Penempatan Bidang**: Operator dapat menempatkan anggota baru ke dalam bidang tertentu dan mengajukannya kepada Pimpinan untuk persetujuan.
*   **Kustomisasi Tema**: Super admin dapat mengubah skema warna aplikasi.
*   **Pembuatan ID Card**: Super admin dan operator dapat mendesain dan menghasilkan ID Card untuk anggota.
*   **Manajemen Anggota**: Super admin dan sekretaris dapat menambah, mengubah, dan menghapus anggota. Pimpinan dapat melihat daftar anggota dan jabatan.
*   **Manajemen Perintah Tugas**: Alur kerja lengkap untuk perintah tugas, termasuk pengajuan, persetujuan, pelaporan, dan pengarsipan.
*   **Role dan Hak Akses**: Sistem role dan hak akses untuk setiap pengguna.
*   **Pelaporan dari Masyarakat**: Memungkinkan masyarakat umum untuk melaporkan kejadian bencana.
*   **Informasi Cuaca**: Menampilkan informasi cuaca terkini.
*   **Berita**: Menampilkan berita terkini terkait kebencanaan.

## Role dan Hak Akses

*   **Super Admin**: Memiliki akses penuh ke semua fitur dan data, termasuk kustomisasi tema, log aktivitas, dan manajemen grup *chat*.
*   **Pimpinan**:
    *   Melihat semua data.
    *   Menyetujui atau menolak perintah tugas, penempatan anggota, dan memo.
    *   Membuat dan mengirim memo.
*   **Kepala Bidang**:
    *   Melihat anggota dan tugas di bidangnya.
    *   Membuat, mengubah, dan menghapus tugas di bidangnya.
    *   Membuat dan mengirim memo ke anggotanya.
    *   Meninjau dan meneruskan laporan memo.
*   **Sekretaris**:
    *   Mengelola anggota.
    *   Membuat dan mengajukan perintah tugas.
    *   Melihat memo.
    *   Mengarsipkan tugas dan memo.
*   **Bendahara**:
    *   Melihat anggota.
*   **Operator**:
    *   Menempatkan anggota baru ke dalam bidang.
    *   Melihat tugas yang ditugaskan kepadanya.
    *   Mengirimkan laporan tugas.
    *   Membuat ID Card.
*   **Anggota**:
    *   Melihat tugas dan memo yang ditugaskan kepadanya.
    *   Mengirimkan laporan tugas dan memo.

## Arsitektur

Aplikasi ini terdiri dari dua bagian:

1.  **Aplikasi Android**: Dibangun dengan Kotlin dan arsitektur MVVM. Menggunakan Retrofit untuk komunikasi jaringan dan Firebase (Realtime Database, Storage, Cloud Messaging) untuk fitur *real-time*.
2.  **API**: Dibangun dengan PHP dan framework Laravel. Bertanggung jawab untuk menghubungkan aplikasi Android dengan database MySQL.

## Instalasi dan Konfigurasi (Pengembangan Lokal)

### Prasyarat

*   Android Studio (versi terbaru direkomendasikan)
*   Lingkungan server lokal seperti XAMPP atau WAMP (dengan PHP, MySQL, dan Composer)
*   Node.js dan NPM
*   Git

### Backend (API)

1.  **Kloning Repositori**:
    ```bash
    git clone <URL_REPOSITORI_ANDA>
    cd <NAMA_DIREKTORI_PROYEK>/bpbd-api
    ```

2.  **Instal Dependensi**:
    ```bash
    composer install
    npm install
    ```
    *Catatan: Jika Composer meminta token GitHub, lihat [dokumentasi GitHub](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token) untuk membuatnya.*

3.  **Konfigurasi Lingkungan**:
    *   Salin file `.env.example` menjadi `.env`: `cp .env.example .env`
    *   Buat kunci aplikasi: `php artisan key:generate`
    *   Buka file `.env` dan konfigurasikan koneksi database Anda (DB_DATABASE, DB_USERNAME, DB_PASSWORD).
    *   Unduh file `google-services.json` dari konsol Firebase dan letakkan di direktori `storage/app`.

4.  **Database**:
    *   Buat database baru di MySQL sesuai dengan nama yang Anda masukkan di `.env`.
    *   Jalankan migrasi dan seeder: `php artisan migrate --seed`

5.  **Jalankan Server**:
    ```bash
    php artisan serve
    ```
    API akan berjalan di `http://127.0.0.1:8000`.

### Frontend (Aplikasi Android)

1.  **Buka Proyek**: Buka direktori `BPBDApp` di Android Studio.
2.  **Konfigurasi Firebase**: Unduh file `google-services.json` dari konsol Firebase Anda dan letakkan di direktori `BPBDApp/app`.
3.  **Konfigurasi Alamat IP API**:
    *   Buka file `BPBDApp/app/src/main/java/com/example/bpbdapp/ApiClient.kt`.
    *   Ubah nilai `BASE_URL` menjadi alamat IP lokal Anda dan port server API (misalnya, `http://192.168.1.10:8000/api/`). Pastikan perangkat atau emulator Anda dapat mengakses alamat ini.
4.  **Jalankan Aplikasi**: Klik tombol "Run" di Android Studio untuk menginstal dan menjalankan aplikasi di emulator atau perangkat fisik.

## Instalasi dan Konfigurasi (Produksi/Hosting)

### Prasyarat Server

*   Server web (Nginx atau Apache)
*   PHP (versi sesuai `composer.json`)
*   MySQL
*   Composer
*   Git
*   Supervisor (untuk menjalankan queue worker jika diperlukan)

### Backend (API)

1.  **Kloning Repositori**: Kloning repositori ke direktori server Anda (misalnya, `/var/www/bpbd-api`).
2.  **Instal Dependensi**:
    ```bash
    composer install --optimize-autoloader --no-dev
    ```
3.  **Konfigurasi Lingkungan**:
    *   Salin file `.env.example` menjadi `.env`.
    *   Isi semua variabel lingkungan yang diperlukan untuk produksi, termasuk kredensial database dan kunci API. Pastikan `APP_ENV` diatur ke `production` dan `APP_DEBUG` ke `false`.
    *   Buat kunci aplikasi: `php artisan key:generate`
4.  **Optimalkan Laravel**:
    ```bash
    php artisan config:cache
    php artisan route:cache
    php artisan view:cache
    ```
5.  **Database**: Jalankan migrasi: `php artisan migrate --force` (gunakan `--force` untuk produksi).
6.  **Konfigurasi Web Server**: Konfigurasikan Nginx atau Apache untuk menunjuk ke direktori `public` dari proyek Laravel Anda. Pastikan untuk mengatur izin file dan folder yang benar.
7.  **Atur Supervisor**: Jika Anda menggunakan antrian (queues), konfigurasikan Supervisor untuk menjalankan `php artisan queue:work` secara terus-menerus.

### Frontend (Aplikasi Android)

1.  **Konfigurasi Alamat IP API**: Ubah `BASE_URL` di `ApiClient.kt` menjadi URL domain API produksi Anda.
2.  **Buat Signed APK/App Bundle**:
    *   Di Android Studio, buka `Build > Generate Signed Bundle / APK...`.
    *   Ikuti petunjuk untuk membuat *keystore* baru atau menggunakan yang sudah ada.
    *   Buat *signed APK* atau *App Bundle*.
3.  **Publikasikan ke Google Play Store**: Unggah file *App Bundle* Anda ke Google Play Console dan ikuti proses publikasi.

## Pembaruan Aplikasi

*   **Google Play Store**: Cara yang direkomendasikan untuk mendistribusikan dan memperbarui aplikasi.
*   **Pembaruan Dalam Aplikasi**: Implementasikan fitur pembaruan dalam aplikasi untuk memberi tahu pengguna tentang versi baru.
*   **Manual**: Untuk pengembangan, instal versi baru secara manual melalui Android Studio.

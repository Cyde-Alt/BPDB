# BPBDApp

Aplikasi Android untuk manajemen anggota Badan Penanggulangan Bencana Daerah (BPBD).

## Fitur

*   **Manajemen Berita**: Super admin, pimpinan, dan operator dapat mengelola berita.
*   **Manajemen Keuangan**: Bendahara dapat mengelola anggaran, mencatat transaksi, dan mengajukan permintaan pengeluaran. Pimpinan dapat menyetujui permintaan dan melihat laporan keuangan.
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

## Role dan Hak Akses

*   **Super Admin**: Memiliki akses penuh ke semua fitur dan data, termasuk kustomisasi tema, log aktivitas, dan manajemen grup *chat*.
*   **Pimpinan**:
    *   Melihat semua data, termasuk laporan keuangan.
    *   Menyetujui atau menolak perintah tugas, penempatan anggota, dan memo.
    *   Membuat dan mengirim memo dan berita.
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
    *   Mengelola keuangan (anggaran, transaksi).
    *   Mengajukan permintaan pengeluaran.
    *   Melihat anggota.
*   **Operator**:
    *   Mengelola berita.
    *   Menempatkan anggota baru ke dalam bidang.
    *   Melihat tugas yang ditugaskan kepadanya.
    *   Mengirimkan laporan tugas.
    *   Membuat ID Card.
*   **Anggota**:
    *   Melihat tugas dan memo yang ditugaskan kepadanya.
    *   Mengirimkan laporan tugas dan memo.

## Arsitektur

Aplikasi ini terdiri dari dua bagian:

1.  **Aplikasi Android**: Dibangun dengan Kotlin, mengikuti arsitektur MVVM (Model-View-ViewModel) dengan Komponen Arsitektur Android (ViewModel, LiveData). Menggunakan Retrofit untuk komunikasi jaringan dan Firebase (Realtime Database, Storage, Cloud Messaging) untuk fitur *real-time*.
2.  **API**: Dibangun dengan PHP dan framework Laravel (versi 8.x). Bertanggung jawab untuk menghubungkan aplikasi Android dengan database MySQL.

## Arsitektur Aplikasi Android

Aplikasi ini secara aktif direfaktor untuk menggunakan arsitektur MVVM secara konsisten. Komponen utama dari arsitektur ini adalah:

*   **View**: Aktivitas dan Fragmen yang bertanggung jawab untuk menampilkan UI.
*   **ViewModel**: Menyimpan dan mengelola data terkait UI dengan cara yang sadar siklus hidup. ViewModel memungkinkan data bertahan dari perubahan konfigurasi seperti rotasi layar.
*   **Repository**: (Dalam pengembangan) Bertindak sebagai satu sumber kebenaran untuk data, mengelola sumber data (jaringan, cache, dll.).
*   **Retrofit**: Klien HTTP yang aman jenis untuk Android dan Java, digunakan untuk melakukan panggilan jaringan ke API.

## Instalasi dan Konfigurasi (Pengembangan Lokal)

### Prasyarat

*   Android Studio (versi terbaru direkomendasikan)
*   Lingkungan server lokal seperti XAMPP atau WAMP (dengan PHP, MySQL, dan Composer)
*   Node.js dan NPM
*   Git

### Backend (API)

Pastikan Anda telah berhasil menginstal dan menjalankan backend (API) sesuai dengan petunjuk di file `bpbd-api/README.md` sebelum melanjutkan ke langkah berikutnya. API harus berjalan di `http://127.0.0.1:8000`.

### Frontend (Aplikasi Android)

**1. Buka Proyek di Android Studio**

*   Buka Android Studio.
*   Pilih `File > Open...` atau `Open an Existing Project`.
*   Arahkan ke direktori tempat Anda mengkloning repositori, lalu pilih direktori `BPBDApp` dan klik "OK".
*   Tunggu hingga Android Studio selesai melakukan sinkronisasi dan membangun proyek.

**2. Konfigurasi Firebase**

*   Unduh file `google-services.json` Anda dari konsol Firebase.
*   Salin file ini dan tempelkan ke dalam direktori `BPBDApp/app/`.

**3. Konfigurasi Alamat IP API**

Ini adalah langkah **paling penting** untuk menghubungkan aplikasi ke API lokal Anda.

*   **Jika menggunakan Emulator Android**:
    *   Buka file `BPBDApp/app/build.gradle`.
    *   Temukan baris `buildConfigField "String", "API_URL", "\"http://10.0.2.2:8000/api/\""` di dalam `debug`. Alamat `10.0.2.2` adalah alamat khusus yang digunakan emulator untuk terhubung ke `localhost` komputer Anda. Seharusnya ini sudah berfungsi tanpa perubahan.
*   **Jika menggunakan Perangkat Android Fisik**:
    *   Pastikan perangkat Android Anda dan komputer Anda terhubung ke **jaringan Wi-Fi yang sama**.
    *   Temukan alamat IP lokal komputer Anda.
        *   **Di Windows**: Buka Command Prompt dan ketik `ipconfig`. Cari alamat "IPv4 Address".
        *   **Di macOS/Linux**: Buka Terminal dan ketik `ifconfig` atau `ip a`. Cari alamat IP Anda (biasanya di bawah `en0` atau `wlan0`). Alamatnya akan terlihat seperti `192.168.1.x`.
    *   Buka file `BPBDApp/app/build.gradle`.
    *   Ubah nilai `API_URL` di dalam `debug` menjadi alamat IP lokal Anda. **Jangan lupa sertakan port `8000` dan `/api/` di akhir.**
        ```groovy
        // Contoh perubahan
        buildConfigField "String", "API_URL", "\"http://192.168.1.10:8000/api/\""
        ```
    *   Klik "Sync Now" di Android Studio setelah melakukan perubahan.

**4. Jalankan Aplikasi**

*   Pilih perangkat (emulator atau perangkat fisik Anda) dari daftar perangkat yang tersedia di Android Studio.
*   Klik tombol "Run" (ikon segitiga hijau) untuk menginstal dan menjalankan aplikasi.

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

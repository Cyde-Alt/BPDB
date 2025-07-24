# BPBD API

API untuk aplikasi manajemen BPBD.

## Endpoint

### Members

*   `GET /api/members` - Mendapatkan semua anggota
*   `POST /api/members` - Membuat anggota baru
*   `GET /api/members/{member}` - Mendapatkan anggota tertentu
*   `PUT/PATCH /api/members/{member}` - Memperbarui anggota tertentu
*   `DELETE /api/members/{member}` - Menghapus anggota tertentu

### Tasks

*   `GET /api/tasks` - Mendapatkan semua tugas
*   `POST /api/tasks` - Membuat tugas baru
*   `GET /api/tasks/{task}` - Mendapatkan tugas tertentu
*   `PUT/PATCH /api/tasks/{task}` - Memperbarui tugas tertentu
*   `DELETE /api/tasks/{task}` - Menghapus tugas tertentu

### Memos

*   `GET /api/memos` - Mendapatkan semua memo
*   `POST /api/memos` - Membuat memo baru
*   `GET /api/memos/{memo}` - Mendapatkan memo tertentu
*   `PUT/PATCH /api/memos/{memo}` - Memperbarui memo tertentu
*   `DELETE /api/memos/{memo}` - Menghapus memo tertentu

### News

*   `GET /api/news` - Mendapatkan semua berita
*   `POST /api/news` - Membuat berita baru
*   `GET /api/news/{news}` - Mendapatkan berita tertentu
*   `PUT/PATCH /api/news/{news}` - Memperbarui berita tertentu
*   `DELETE /api/news/{news}` - Menghapus berita tertentu

### Finance

*   `GET /api/finance/budgets` - Mendapatkan semua anggaran
*   `POST /api/finance/budgets` - Membuat anggaran baru
*   `GET /api/finance/budgets/{budget}` - Mendapatkan anggaran tertentu
*   `PUT/PATCH /api/finance/budgets/{budget}` - Memperbarui anggaran tertentu
*   `DELETE /api/finance/budgets/{budget}` - Menghapus anggaran tertentu
*   `GET /api/finance/transactions` - Mendapatkan semua transaksi
*   `POST /api/finance/transactions` - Membuat transaksi baru
*   `GET /api/finance/transactions/{transaction}` - Mendapatkan transaksi tertentu
*   `PUT/PATCH /api/finance/transactions/{transaction}` - Memperbarui transaksi tertentu
*   `DELETE /api/finance/transactions/{transaction}` - Menghapus transaksi tertentu

## Instalasi (Pengembangan Lokal)

Bagian ini akan memandu Anda melalui proses instalasi API di lingkungan pengembangan lokal Anda.

### 1. Prasyarat

Sebelum memulai, pastikan Anda telah menginstal perangkat lunak berikut di komputer Anda:

*   **Lingkungan Server Lokal**:
    *   **XAMPP** atau perangkat lunak serupa yang menyertakan:
        *   **PHP**: Versi 7.3 atau 8.0+. Anda bisa memeriksa versi dengan perintah `php -v`.
        *   **MySQL**: Untuk database.
    *   *Rekomendasi*: Pastikan direktori PHP Anda ditambahkan ke PATH sistem Anda agar Anda dapat menjalankan perintah `php` dari direktori mana pun.
*   **Composer**: Manajer dependensi untuk PHP. Anda bisa memeriksa apakah sudah terinstal dengan `composer --version`. Jika belum, unduh dari [getcomposer.org](https://getcomposer.org/).
*   **Git**: Sistem kontrol versi. Periksa dengan `git --version`.

### 2. Kloning Repositori

Buka terminal atau command prompt Anda dan kloning repositori ini ke lokasi yang Anda inginkan.

```bash
git clone <URL_REPOSITORI_ANDA>
```

### 3. Instalasi Backend (API)

Semua perintah berikut harus dijalankan dari dalam direktori `bpbd-api`.

```bash
# Masuk ke direktori API
cd <NAMA_DIREKTORI_PROYEK>/bpbd-api
```

**a. Instal Dependensi PHP**

Jalankan perintah berikut untuk mengunduh semua pustaka PHP yang dibutuhkan oleh proyek.

```bash
composer install
```

**b. Konfigurasi Lingkungan**

*   Salin file konfigurasi contoh `.env.example` menjadi file `.env` baru. File ini akan menyimpan semua konfigurasi khusus untuk lingkungan Anda.
    ```bash
    cp .env.example .env
    ```
*   Buat kunci aplikasi unik yang digunakan Laravel untuk enkripsi.
    ```bash
    php artisan key:generate
    ```

**c. Konfigurasi Database**

*   Buka file `.env` yang baru saja Anda buat dengan editor teks.
*   Temukan baris yang dimulai dengan `DB_DATABASE`, `DB_USERNAME`, dan `DB_PASSWORD`.
*   Ubah nilainya agar sesuai dengan konfigurasi database MySQL Anda di XAMPP.
    ```
    DB_CONNECTION=mysql
    DB_HOST=127.0.0.1
    DB_PORT=3306
    DB_DATABASE=bpbd_db  # Ganti dengan nama database yang Anda inginkan
    DB_USERNAME=root      # Ganti dengan username database Anda (default XAMPP adalah 'root')
    DB_PASSWORD=          # Ganti dengan password database Anda (default XAMPP adalah kosong)
    ```
*   Buat database baru di MySQL (misalnya, melalui phpMyAdmin) dengan nama yang sama persis seperti yang Anda masukkan untuk `DB_DATABASE`.

**d. Migrasi dan Seeding Database**

Jalankan perintah berikut untuk membuat semua tabel yang diperlukan di database Anda dan mengisinya dengan beberapa data awal.

```bash
php artisan migrate --seed
```

**e. Konfigurasi Firebase**

*   Unduh file `google-services.json` Anda dari konsol Firebase.
*   Letakkan file ini di direktori `storage/app/` di dalam proyek `bpbd-api`.

### 4. Menjalankan Server API

Setelah semua langkah di atas selesai, Anda dapat menjalankan server pengembangan lokal.

```bash
php artisan serve
```

Jika berhasil, Anda akan melihat pesan seperti:
`Starting Laravel development server: http://127.0.0.1:8000`

API Anda sekarang berjalan dan siap menerima permintaan dari aplikasi Android.

### Pemecahan Masalah Umum

*   **Error: `Could not open input file: artisan`**:
    *   **Penyebab**: Anda mencoba menjalankan perintah `php artisan` dari direktori yang salah.
    *   **Solusi**: Pastikan Anda berada di dalam direktori `bpbd-api` di terminal Anda sebelum menjalankan perintah apa pun.
*   **Error: `PHP version requirement ... does not satisfy your PHP server version`**:
    *   **Penyebab**: Versi PHP Anda tidak memenuhi persyaratan yang tercantum dalam `composer.json`.
    *   **Solusi**: Tingkatkan instalasi PHP Anda ke versi yang diperlukan.
*   **Error terkait Ekstensi PHP (mis. `ext-mbstring not found`)**:
    *   **Penyebab**: Ekstensi PHP yang diperlukan oleh Laravel tidak diaktifkan.
    *   **Solusi**: Buka file `php.ini` Anda (di XAMPP, ini dapat ditemukan melalui panel kontrol XAMPP), cari baris untuk ekstensi yang hilang (misalnya, `extension=mbstring`), dan hapus tanda titik koma (`;`) di awal baris untuk mengaktifkannya. Simpan file dan restart server Apache Anda.

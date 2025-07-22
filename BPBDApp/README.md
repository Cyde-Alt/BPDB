# BPBDApp

Aplikasi Android untuk manajemen anggota Badan Penanggulangan Bencana Daerah (BPBD).

## Fitur

*   **Informasi Cuaca**: Menampilkan informasi cuaca terkini.
*   **Berita**: Menampilkan berita terkini terkait kebencanaan.
*   **Manajemen Anggota**: Menambah, mengubah, dan menghapus data anggota. Menampilkan daftar anggota beserta status (siaga, bertugas, non-aktif).
*   **Manajemen Perintah Tugas**: Membuat perintah tugas baru dengan detail seperti lokasi, jenis bencana, dan anggota yang ditugaskan. Mengirimkan notifikasi kepada anggota yang ditugaskan. Melacak status pengerjaan tugas (diterima, dikerjakan, selesai).
*   **Memo Penugasan Darurat**: Fitur untuk mengirimkan memo singkat dan darurat kepada seluruh atau sebagian anggota. Notifikasi darurat yang menonjol di perangkat anggota.

## Arsitektur

Aplikasi ini dibangun menggunakan arsitektur MVVM (Model-View-ViewModel) dengan komponen-komponen berikut:

*   **View**: Activities dan Fragments yang bertanggung jawab untuk menampilkan UI.
*   **ViewModel**: Menyimpan dan mengelola data terkait UI.
*   **Model**: Merepresentasikan data dan logika bisnis.

## Cara Menjalankan

1.  Buka proyek di Android Studio.
2.  Jalankan aplikasi di emulator atau perangkat Android.
3.  Login dengan akun yang valid (saat ini belum ada otentikasi).
4.  Gunakan navigasi bawah untuk mengakses fitur-fitur yang tersedia.

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

## Catatan

Jika Anda diminta memasukkan token saat menjalankan `composer install`, Anda perlu membuat personal access token di GitHub dan mengkonfigurasinya dengan Composer. Lihat [dokumentasi GitHub](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token) untuk detailnya.

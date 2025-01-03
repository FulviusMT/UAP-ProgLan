# Rental PC Advanced System

## Overview
Rental PC Advanced System adalah aplikasi berbasis Java yang dirancang untuk mengelola bisnis rental PC. Sistem ini menyediakan antarmuka pengguna grafis (GUI) untuk administrator, memantau status PC, mengelola transaksi sewa, dan memastikan pengalaman pengguna yang efisien.

---

## Fitur Utama

### 1. **Login Admin**
Aplikasi ini dimulai dengan layar login admin untuk memastikan akses hanya diberikan kepada pengguna yang berwenang.
- **Kredensial Default:**
  - Username: dmin
  - Password: password

### 2. **Manajemen Pelanggan**
- Input informasi pelanggan seperti nama, durasi pemakaian, dan pilihan PC.
- Data pelanggan ditampilkan dalam tabel transaksi.

### 3. **Pemantauan Status PC**
- Status 10 PC ditampilkan dengan indikator warna:
  - **Hijau**: Tersedia.
  - **Merah**: Sedang digunakan.
- Status diperbarui secara otomatis berdasarkan aktivitas pelanggan.

### 4. **Transaksi Sewa**
- Input data sewa meliputi:
  - Nama pelanggan.
  - Durasi pemakaian (jam).
  - Tarif per jam (default: Rp 10,000).
  - Pilihan PC.
- Hitung biaya total secara otomatis.
- Tandai PC sebagai 
digunakan ketika sesi dimulai.

### 5. **Selesai dan Pembayaran**
- Pilih pelanggan yang selesai dari tabel.
- Sistem membuka dialog pembayaran:
  - Masukkan nominal pembayaran.
  - Sistem menghitung kembalian (jika ada).
- Status PC kembali menjadi tersedia setelah pembayaran berhasil.

---

## Struktur Program

### 1. **Kelas Utama: RentalPCAdvanced**
Mengelola logika utama aplikasi, termasuk:
- GUI untuk login dan rental.
- Manajemen tabel transaksi.
- Pemantauan dan pembaruan status PC.

### 2. **Komponen GUI**
- **Login Frame:**
  - Login admin dengan validasi username dan password.
- **Main Frame:**
  - Panel untuk input data pelanggan.
  - Tabel untuk menampilkan transaksi aktif.
  - Panel status PC dengan warna hijau/merah.
- **Dialog Pembayaran:**
  - Input nominal pembayaran.
  - Menampilkan total biaya dan kembalian.

### 3. **Logika Status PC**
- **Map Status PC:**
  - Menyimpan status tiap PC (tersedia atau digunakan).
- **Ikon Status PC:**
  - Mengubah warna berdasarkan status terkini.

---

## Cara Menjalankan Aplikasi

1. **Persiapan Lingkungan:**
   - Pastikan JDK 8 atau versi lebih baru terinstal di komputer Anda.
   - Gunakan IDE (seperti IntelliJ IDEA atau Eclipse) atau terminal.

2. **Kompilasi Program:**
   `ash
   javac RentalPCAdvanced.java
   `

3. **Jalankan Program:**
   `ash
   java RentalPCAdvanced
   `

---

## Alur Penggunaan

1. **Login:**
   - Masukkan username dan password admin untuk mengakses sistem.

2. **Menambah Transaksi Baru:**
   - Input nama pelanggan, durasi pemakaian, dan pilih PC.
   - Klik tombol Tambah untuk memulai sesi.

3. **Menyelesaikan Transaksi:**
   - Pilih pelanggan yang selesai dari tabel.
   - Klik tombol Selesai untuk membuka dialog pembayaran.
   - Input nominal pembayaran dan konfirmasi.

4. **Memantau Status PC:**
   - Status PC diperbarui secara otomatis berdasarkan aktivitas pelanggan.

---

## Pengembangan Mendatang
- Integrasi dengan database untuk penyimpanan data yang lebih baik.
- Penambahan fitur pelaporan pendapatan dan statistik penggunaan.
- Peningkatan antarmuka pengguna dengan JavaFX.

---

## Penulis
Dikembangkan oleh Noval Kurniawan Kodi dan Marsel Putra Nugraha.

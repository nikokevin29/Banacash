# Banacash

Aplikasi Android kasir sederhana untuk mengelola produk, transaksi penjualan, voucher diskon, dan laporan. Dibangun dengan Java dan Retrofit untuk komunikasi ke backend REST API.

> Tugas kelompok mata kuliah **Pemrograman Berbasis Platform (PBP)** — Universitas Atma Jaya Yogyakarta.

---

## Fitur

| Modul | Deskripsi |
|-------|-----------|
| Login | Autentikasi pengguna |
| Main Menu | Navigasi ke semua fitur utama |
| Produk | Kelola daftar produk (tambah, edit) |
| Transaksi | Buat transaksi baru — pilih produk, proses penjualan |
| Voucher | Kelola voucher diskon (tambah, edit) |
| Laporan | Riwayat transaksi dan detail laporan |

## Tech Stack

| Komponen | Teknologi |
|----------|-----------|
| Platform | Android (Min SDK 28 / Android 9+) |
| Bahasa | Java |
| Networking | Retrofit 2.7.2 + OkHttp 3 |
| Serialization | Gson 2.8.6 |
| HTTP Utility | Volley 1.1.1 |
| UI | Material Design + RecyclerView + CardView |
| Build | Gradle (compileSdk 29) |

## Arsitektur

```
com.xbanana.banacash/
├── Login.java
├── MainMenu.java
├── Produk/
│   ├── kelola_produk        # Daftar produk
│   └── edit_produk          # Tambah / edit produk
├── Transaksi/
│   ├── view_kelola_transaksi      # Daftar transaksi
│   ├── view_pick_produk_transaksi # Pilih produk saat transaksi
│   ├── adapter_pick_produk_transaksi
│   └── adapter_view_produk_transaksi
├── Voucher/
│   ├── kelola_voucher       # Daftar voucher
│   └── edit_voucher         # Tambah / edit voucher
└── Laporan/
    ├── kelola_laporan        # Daftar laporan
    └── ShowDetailLaporan     # Detail transaksi
```

## Setup

1. Clone repo dan buka di Android Studio
2. Sesuaikan base URL API Retrofit ke backend yang aktif
3. Build dan jalankan di device/emulator (Android 9+)

## Tim Pengembang

| GitHub | Peran |
|--------|-------|
| [@nikokevin29](https://github.com/nikokevin29) | Kontributor |
| [@Renzvin](https://github.com/Renzvin) | Kontributor |
| [@tzurio00](https://github.com/tzurio00) | Kontributor |

---

*Java · Retrofit · Android · Tugas Kelompok PBP UAJY*

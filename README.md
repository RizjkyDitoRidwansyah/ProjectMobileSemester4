# ProjectMobileSemester4

# UAS-Mobile
Login &amp; Register, CRUD

---
## Laporan Proyek: Aplikasi Android untuk Login, Register, dan CRUD Data Pengguna

### Pendahuluan
Proyek ini bertujuan untuk mengembangkan aplikasi Android yang memungkinkan pengguna untuk melakukan pendaftaran (register), masuk (login), dan mengelola data pengguna yang terdaftar. Aplikasi ini menggunakan bahasa pemrograman Java dan Firebase sebagai API database untuk menyimpan dan mengelola data pengguna.

### Tujuan Proyek
1. Membangun fitur pendaftaran pengguna baru.
2. Mengimplementasikan fitur login untuk pengguna yang sudah terdaftar.
3. Mengembangkan fungsi CRUD untuk mengelola data pengguna yang terdaftar.
4. Mengintegrasikan Firebase sebagai backend untuk autentikasi dan penyimpanan data.

### Tools dan Teknologi yang Digunakan
1. **Android Studio**: IDE utama untuk pengembangan aplikasi Android.
2. **Java**: Bahasa pemrograman yang digunakan untuk mengembangkan logika aplikasi.
3. **Firebase**: Backend-as-a-Service (BaaS) yang menyediakan layanan autentikasi dan database realtime.
4. **Firebase Authentication**: Untuk mengelola autentikasi pengguna.
5. **Firebase Realtime Database / Firestore**: Untuk menyimpan data pengguna.

### Implementasi
#### 1. Pendaftaran Pengguna (Register)
- Pengguna dapat mendaftar dengan mengisi form yang berisi email, password, dan data lainnya.
- Data dikirim ke Firebase Authentication untuk pembuatan akun baru.
- Jika pendaftaran berhasil, data pengguna disimpan di Firebase Realtime Database / Firestore.

#### 2. Masuk Pengguna (Login)
- Pengguna dapat login dengan memasukkan email dan password yang terdaftar.
- Firebase Authentication digunakan untuk memverifikasi kredensial pengguna.
- Jika login berhasil, pengguna diarahkan ke halaman utama aplikasi.

#### 3. CRUD Data Pengguna
- **Create**: Pengguna dapat menambahkan data baru ke database.
- **Read**: Pengguna dapat melihat data yang sudah tersimpan di database.
- **Update**: Pengguna dapat memperbarui data yang sudah ada.
- **Delete**: Pengguna dapat menghapus data yang tidak diperlukan.

### Arsitektur Aplikasi
1. **Activity dan Fragment**: Mengelola antarmuka pengguna.
2. **FirebaseService**: Kelas yang mengelola komunikasi dengan Firebase.
3. **User**: Model data pengguna.
4. **Adapter**: Untuk menampilkan data pengguna dalam bentuk list.

### Kesimpulan
Proyek ini berhasil mengimplementasikan fitur login, register, dan CRUD data pengguna menggunakan bahasa pemrograman Java dan Firebase sebagai API database. Aplikasi ini dapat digunakan sebagai dasar untuk mengembangkan aplikasi yang lebih kompleks dengan fitur tambahan seperti pengelolaan profil pengguna, integrasi dengan layanan pihak ketiga, dan lain-lain.

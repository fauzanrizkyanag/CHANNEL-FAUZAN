# SBI Nasabah Service

RESTful Web Service untuk pengelolaan data nasabah, dikembangkan menggunakan Java dan Spring Boot sebagai bagian dari technical assignment PT Syariah Bisnis Internasional (SBI).

## Tech Stack

| Teknologi       | Versi / Keterangan  |
| --------------- | ------------------- |
| Java            | 21                  |
| Spring Boot     | 4.1.2               |
| Spring Web MVC  | REST API            |
| Spring Data JPA | ORM                 |
| Hibernate       | Database mapping    |
| MySQL           | Relational Database |
| Maven           | Build automation    |
| Postman         | API testing         |

## Fitur

- Menambah nasabah baru.
- Menampilkan nasabah berdasarkan nomor KTP.
- Menampilkan semua nasabah terdaftar yang aktif.
- Update data nasabah.
- Menghapus nasabah dengan soft delete.
- Validasi input.
- Pencegahan duplikasi KTP.
- HTTP status code yang konsisten.

## Struktur Proyek

```text
nasabah-service/
├── src/
│   ├── main/
│   │   ├── java/com/sbi/nasabah/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── entity/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── NasabahServiceApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── .env.example
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## Prerequisite

Pastikan perangkat sudah memiliki:

- Java Development Kit (JDK) 21.
- MySQL Server.
- Git (untuk mengambil source code).
- Postman (untuk pengujian API).

Maven tidak perlu diinstal secara terpisah karena project sudah menyediakan Maven Wrapper.

## Konfugurasi Database

### 1. Membuat Database

Buat database MySQL dengan menjalankan SQL berikut:

```sql
CREATE DATABASE nasabah_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
```

### 2. Environment Variable

Buat file `.env` pada root project berdasarkan pada file `.env.example`.

Contoh konfigurasi:

```dotenv
SERVER_PORT=8080

DB_HOST=localhost
DB_PORT=3306
DB_NAME=nasabah_db
DB_USERNAME=root
DB_PASSWORD=
```

Sesuaikan username, password, dan port MySQL dengan konfigurasi lokal.

**Penting:** File `.env` berisi konfigurasi lokal dan tidak boleh diunggah ke repository publik. Gunakan `.env.example` sebagai template konfigurasi.

Pastikan `application.properties` memiliki konfigurasi berikut agar Spring Boot membaca file `.env`:

```properties
spring.application.name=nasabah-service

server.port=${SERVER_PORT:8080}

spring.config.import=optional:file:.env[.properties]


spring.datasource.url=jdbc:mysql://${DB_HOST:localhost}:${DB_PORT:3306}/${DB_NAME:nasabah_db}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false

spring.web.error.include-message=always
```

### 3. Tabel Database

Tabel `nasabah` dibuat dan dikelola menggunakan Spring Data JPA dan Hibernate berdasarkan entity `Nasabah`.

Tidak diperlukan pembuatan tabel secara manual.

## Menjalankan Aplikasi

Jalankan perintah berikut dari direktori root project.

### Windows (PowerShell)

```powershell
.\mvnw.cmd clean test
```

Jika build berhasil, jalankan aplikasi:

```powershell
.\mvnw.cmd spring-boot:run
```

### Build JAR

```powershell
.\mvnw.cmd clean package
```

Jalankan JAR yang dihasilkan:

```powershell
java -jar target/nasabah-service-0.0.1-SNAPSHOT.jar
```

Aplikasi secara default berjalan pada:

`http://localhost:8080`

## Dokumentasi REST API

Base URL:

`http://localhost:8080/api/nasabah`

| HTTP Method | Endpoint                  | Description                         | Success Status |
| ----------- | ------------------------- | ----------------------------------- | -------------- |
| POST        | `/api/nasabah`            | Menambah nasabah baru               | 201 Created    |
| GET         | `/api/nasabah/{nomorKtp}` | Menampilkan nasabah berdasarkan KTP | 200 OK         |
| GET         | `/api/nasabah`            | Menampilkan semua nasabah aktif     | 200 OK         |
| PUT         | `/api/nasabah/{nomorKtp}` | Update data nasabah                 | 200 OK         |
| DELETE      | `/api/nasabah/{nomorKtp}` | Hapus data nasabah                  | 204 No Content |

### 1. Menambah Nasabah Baru

**POST** `/api/nasabah`

Request Header:

```http
Content-Type: application/json
```

Request Body:

```json
{
  "namaLengkap": "Budi Santoso",
  "alamat": "Jl Melati No 10",
  "tempatLahir": "Bandung",
  "tanggalLahir": "2000-05-15",
  "nomorKtp": "3273011505000001",
  "nomorHandphone": "6281234567890"
}
```

Expected response: `201 Created`.

### 2. Menampilkan Nasabah Berdasarkan KTP

**GET** `/api/nasabah/{nomorKtp}`

Example:

```http
GET /api/nasabah/3273011505000001
```

Expected response: `200 OK`.

Mengembalikan informasi nasabah berdasarkan nomor KTP

### 3. Menampilkan Semua Nasabah Aktif

**GET** `/api/nasabah`

Mengembalikan semua data nasabah aktif berupa data array.

Data terhapus tidak akan ditampilkan dalam response ini.

### 4. Update Data Nasabah

**PUT** `/api/nasabah/{nomorKtp}`

Example:

```http
PUT /api/nasabah/3273011505000001
```

Request Body:

```json
{
  "namaLengkap": "Budi Santoso",
  "alamat": "Jl Anggrek No 25",
  "tempatLahir": "Bandung",
  "tanggalLahir": "2000-05-15",
  "nomorKtp": "3273011505000001",
  "nomorHandphone": "6289876543210"
}
```

Nomor KTP pada request body harus sesuai dengan nomor KTP number pada URL ini.

Expected response: `200 OK`.

### 5. Hapus Data Nasabah

**DELETE** `/api/nasabah/{nomorKtp}`

Example:

```http
DELETE /api/nasabah/3273011505000001
```

Expected response: `204 No Content`.

Aplikasi ini menerapkan soft delete. Data yang terhapus dapat muncul pada database namun tidak ditampilkan pada pengambilan API.

## HTTP Status Code

| Status Code     | Description                          |
| --------------- | ------------------------------------ |
| 200 OK          | Request successful                   |
| 201 Created     | Nasabah successfully registered      |
| 204 No Content  | Nasabah successfully deleted         |
| 400 Bad Request | Invalid request or validation error  |
| 404 Not Found   | Nasabah not found or already deleted |
| 409 Conflict    | KTP number already registered        |

## Validasi Input

Aplikasi ini menerapkan beberapa validasi:

- nama lengkap wajib diisi.
- Alamat wajib diisi.
- Tempat lahir wajib diisi.
- Tanggal lahir tidak boleh lebih dari tanggal sekarang (tanggal masa depan).
- Nomor KTP harus tepat mengandung 16 digit angka.
- Nomor KTP harus unik.
- Nomor handphone harus diawali dengan `628`.

## Soft Delete

Saat data nasabah dihapus, aplikasi memperbarui field internal `deleted`, bukan menghapus data database secara permanen.

Akibatnya:

- Nasabah yang telah dihapus tidak dapat diambil melalui API.
- Nasabah yang telah dihapus tidak termasuk dalam daftar nasabah aktif.
- Data yang telah dihapus tetap tersimpan di MySQL.

## Testing

Aplikasi ini telah diuji secara manual menggunakan Postman.

Skenario pengujian meliputi:

- Pendaftaran nasabah yang berhasil.
- Validasi KTP ganda.
- Validasi tanggal lahir di masa depan.
- Mengambil data nasabah berdasarkan KTP.
- Mengambil semua nasabah aktif.
- Memperbarui data nasabah yang sudah ada.
- Menangani nasabah yang tidak tersedia.
- Menghapus nasabah menggunakan metode soft delete.
- Memverifikasi bahwa data yang dihapus tetap tersimpan di database.

## Catatan Keamanan

- Jangan pernah commit file `.env` atau kredensial database ke dalam GitHub.
- Gunakan file `.env.example` sebagai contoh templat konfigurasi.
- Gunakan data dummy/fiktif saat menguji API.
- Jangan mengekspos data pribadi pelanggan sungguhan di repositori publik.

## License

Dibuat sebagai technical assignment PT Syariah Bisnis Internasional (SBI).

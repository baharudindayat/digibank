# Auth API Documentation

Base URL: http://digibank/api/v1


## Belum punya akun & Rekening

### Choose Rekening Type / Card Type

Setelah user pilih kartu, data tipe rekening ditampung di fe, nantinya akan dipost ketika user klik button lanjut pada isi data diri/cif

Endpoint : GET/api/users/cards

Response : 

```json
{
    "status" : 200,
    "data" :[
        {
            "idTipe" : 1,
            "nama" : "Silver",
            "limitTransfer" : "5 Juta"
        },
        {
            "idTipe" : 2,
            "nama" : "Gold",
            "limitTransfer" : "10 Juta"
        },
        {    
            "idTipe" : 3,
            "nama" : "Platinum",
            "limitTransfer" : "15 Juta"
        },
    ]
}
```


### Email Confirmation / OTP Generate

Endpoint : POST/api/users/otp-generate

Request Body :

```json
{
    "email" : "budi@gmail.com"
}
```

Response Body (succes) :

```json
{
    "data": {
        "id_user": 1,
        "email": "budi@gmail.com"
    },
    "message": "Otp berhasil terkirim",
    "status": 201
}
```
Response Body (failed) :

```json
{
    "message": "Email Sudah Terdaftar",
    "status": 404
}
```

### OTP Verification / OTP Confirmation

Endpoint : PUT/api/users/{id_user}/otp-verification

Request Body :

```json
{
    "otp": "1956"
}
```

Response Body (succes) :

```json
{
    "message": "OTP Terverifikasi",
    "status": 200
}
```

Response Body (failed) :

```json
{
    "message": "Kode OTP yang dimasukkan tidak valid",
    "status": 400
}
```

```json
{
    "message": "User tidak ditemukkan",
    "status": 400
}
```

```json
{
    "message": "Akun sudah terverifikasi",
    "status": 400
}
```

### Email Confirmation / OTP Regenerate


Endpoint : PUT/api/users/{id_user}/otp-regenerate

Request Body :

```json
{
    "email" : "budi@gmail.com"
}
```

Response Body (succes) :

```json
{
    "status" : 200,
    "otp"    : "OTP Terkirim Kembali" 
}
```

Response Body (failed) :

```json
{
    "status" : 400,
    "otp"    : "User tidak ditemukann"
}
```

```json
{
    "status" : 400,
    "otp"    : "Email tidak dapat ditemukan"
}
```

```json
{
    "status" : 400,
    "otp"    : "Email tidak dapat ditemukan"
}
```

Response Body (failed) :

```json
{
    "status" : 400,
    "otp"    : "Gagal mengirim OTP, Silahkan Coba lagi"
}
```

### CIF

Endpoint : POST /users/cif

Request Body :

```json
{
    "nik" : "123456",
    "alamat" : "Maguwo 12",
    "nama_lengkap" : "Suparman"
    "pekerjaan" : "Pegawai Negeri Sipil (PNS)",
    "penghasilan" : "0 - 5.000.000",
   
}
```

Response Body (success) :

```json
{
    "status" : 200,
    "message" : "success",
    "id_cif" : 1,
    "no_rek" : "12345678"
}
```

### Create Rekening


Endpoint : POST /users/account

Request Body :

```json
{
    "no_rek" : "12345678",
    "id_cif" : 1,
    "id_tipe_rekening" : 3 // post id tipe rekening yang sudah ditampung
}
```

Response Body (success) :

```json
{
    "status" : 200,
    "message" : "success",
}
```

### Create Password

Endpoint : PUT/api/users/{id_user}/password

Request Body :

```json
{
    "password" : "rahasia123"
}
```

Response Body (success) :

```json
{
    "status" : 200,
    "message" : "Kata Sandi Berhasil Disimpan" 
}
```

Response Body (failed) :

```json
{
    "status" : 400,
    "message" : "User tidak ditemukan" 
}
```

### Create MPIN

Endpoint : PUT/api/users/{id_user}/mpin

Request Body :

```json
{
    "mpin" : "123456"
}
```

Response Body (success) :

```json
{
    "status" : 200,
    "message" : "Selamat Akun Berhasil dibuat Silahkan Masuk Akun" 
}
```
Response Body (failed) :

```json
{
    "status" : 400,
    "message" : "User tidak ditemukan" 
}
```

### MPIN Confirmation

Endpoint : POST/api/users/{id_user}/confirm-mpin

Request Body :

```json
{
    "mpin" : "123456"
}
```

Response Body (success) :

```json
{
    "status" : 200,
    "message" : "MPIN terkonfimasi" 
}
```
Response Body (failed) :

```json
{
    "status" : 400,
    "message" : "User tidak ditemukan" 
}
```

```json
{
    "status" : 400,
    "message" : "MPIN tidak sama" 
}
```

## Belum punya akun & sudah punya rekening


### Email Confirmation / OTP Generate

Endpoint : POST/api/users/otp-generate

Request Body :

```json
{
    "email" : "budi@gmail.com"
}
```

Response Body (succes) :

```json
{
    "data": {
        "id_user": 1,
        "email": "budi@gmail.com"
    },
    "message": "Otp berhasil terkirim",
    "status": 201
}
```
Response Body (failed) :

```json
{
    "message": "Email Sudah Terdaftar",
    "status": 404
}
```

### OTP Verification / OTP Confirmation

Endpoint : PUT/api/users/{id_user}/otp-verification

Request Body :

```json
{
    "otp": "1956"
}
```

Response Body (succes) :

```json
{
    "message": "OTP Terverifikasi",
    "status": 200
}
```

Response Body (failed) :

```json
{
    "message": "Kode OTP yang dimasukkan tidak valid",
    "status": 400
}
```

```json
{
    "message": "User tidak ditemukkan",
    "status": 400
}
```

```json
{
    "message": "Akun sudah terverifikasi",
    "status": 400
}
```

### Konfirmasi Rekening

Endpoint : POST /users/confirm-accounts

Request Body :

```json
{
    "nomor_rekening" : "12345678"
}
```

Response Body (success) :

```json
{
    "status" : 200,
    "message" : "Success"
    "id_cif" : 1
}
```

Response Body (failed) :

```json
{
    "status" : 401,
    "message" : "Nomor Rekening tidak terdaftar"
    "id_cif" : null
}
```

### Konfirmasi CIF

Endpoint : GET /users/{id_cif}/confirm-cif


Response Body (success) :

```json
{
    "nik" : "1234567887654321",
    "nama_lengkap" : "Suparman"
}
```


### Create Password

Endpoint : PUT/api/users/{id_user}/password

Request Body :

```json
{
    "password" : "rahasia123"
}
```

Response Body (success) :

```json
{
    "status" : 200,
    "message" : "Kata Sandi Berhasil Disimpan" 
}
```

Response Body (failed) :

```json
{
    "status" : 400,
    "message" : "User tidak ditemukan" 
}
```

### Create MPIN

Endpoint : PUT/api/users/{id_user}/mpin

Request Body :

```json
{
    "mpin" : "123456"
}
```

Response Body (success) :

```json
{
    "status" : 200,
    "message" : "Selamat Akun Berhasil dibuat Silahkan Masuk Akun" 
}
```
Response Body (failed) :

```json
{
    "status" : 400,
    "message" : "User tidak ditemukan" 
}
```
### MPIN Confirmation

Endpoint : POST/api/users/{id_user}/confirm-mpin

Request Body :

```json
{
    "mpin" : "123456"
}
```

Response Body (success) :

```json
{
    "status" : 200,
    "message" : "MPIN terkonfimasi" 
}
```
Response Body (failed) :

```json
{
    "status" : 400,
    "message" : "User tidak ditemukan" 
}
```

```json
{
    "status" : 400,
    "message" : "MPIN tidak sama" 
}
```

### User Login


Endpoint : POST/api/users/login

Request Body :

```json
{
    "email" : "fahrizalshofyanaziz@gmail.com"
    "password" : "rahasia123"
}
```

Response Body (success) :

```json
{
    "status" : 200,
    "message" : "Login Berhasil!"
    "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1dWlkIjoiYTQzNjUzYjEtMjk2Ni00NDY1LWE0YjktZjRmYmM0OTE3NzVhIiwiaWF0IjoxNjg2MzIxMzQ0LCJleHAiOjE2ODYzMjE2NDR9.mzHMPKXzlOkHpRFAq3Sol5ALtc5TH0l_o4aN4YZxLMA"
    
}
```

Response Body (failed email&password) :

```json
{
    "status" : 401,
    "message" : "Maaf! Email dan Kata Sandi yang dimasukkan salah. Pastikan Email dan Kata Sandi benar."
}
```

## Profiling

### Change Password

Endpoint : PUT/api/users/{id_user}/password

Request Body :

```json
{
    "oldPassword" : "rahasia123",
    "newPassword" : "12345678",
    "confirmPassword" : "12345678"
     "accessToken":       "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1dWlkIjoiYTQzNjUzYjEtMjk2Ni00NDY1LWE0YjktZjRmYmM0OTE3NzVhIiwiaWF0IjoxNjg2MzIxMzQ0LCJleHAiOjE2ODYzMjE2NDR9.mzHMPKXzlOkHpRFAq3Sol5ALtc5TH0l_o4aN4YZxLMA"
}
```

Response Body (success) :

```json
{
    "status" : 200,
    "message" : "Kata Sandi Berhasil Diubah" 
}
```

Response Body (failed) :

```json
{
    "status" : 400,
    "message" : "Password tidak sesuai" 
}
```

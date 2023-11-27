# Auth API Documentation

Base URL: https://digibank.com


## Belum punya akun & Rekening

### Choose Rekening Type / Card Type

Setelah user pilih kartu, data tipe rekening ditampung di fe, nantinya akan dipost ketika user klik button lanjut pada isi data diri/cif

Endpoint : GET /api/v1/users/cards

Response : 

```json
{
    "data" :[
        {
            "idTipe" : 1,
            "namaTipe" : "SILVER",
            "limitTransfer" : "5000000"
        },
        {
            "idTipe" : 2,
            "namaTipe" : "GOLD",
            "limitTransfer" : "10000000"
        },
        {    
            "idTipe" : 3,
            "namaTipe" : "PLATINUM",
            "limitTransfer" : "15000000"
        },
    ]
}
```


### Email Confirmation / OTP Generate

Endpoint : POST /api/v1/users/otp-generate

Tipe data Body:
- email -> String

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
        "idUser": 1,
        "email": "budi@gmail.com"
    },
    "message": "OTP berhasil terkirim",
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

Endpoint : PUT /api/v1/users/{id}/otp-verification

Path variable : idUser

Tipe data Body:
- otp -> String

Request Body :

```json
{
    "otp": "1956"
}
```

Response Body (succes) :

```json
{
    "message": "Email berhasil diverifikasi",
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
    "message": "Email sudah terverifikasi",
    "status": 400
}
```

### Email Confirmation / OTP Regenerate


Endpoint : PUT /api/v1/users/{id}/otp-regenerate

Path variable : idUser

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

### CIF & Accounts

Endpoint : POST /api/v1/users/{id}/tipe-rekening/{idTipe}/cif

Path variable : idUser, idTipe

Tipe data Body:
- nik -> String
- alamat -> String
- namaLengkap -> String
- pekerjaan -> String
- penghasilan -> String
- idUser -> Long
- idTipe -> Long

Request Body :

```json
{
    "nik" : "1234567891012345",
    "alamat" : "Maguwo 12",
    "namaLengkap" : "Suparman"
    "pekerjaan" : "Pegawai Negeri Sipil (PNS)",
    "penghasilan" : "0 - 5.000.000"
}
```

Response Body (success) :

```json
{
    "status" : 200,
    "message" : "CIF berhasil dibuat",
    "norek" : "7712345683214745"
}
```

### Create Password

Endpoint : PUT /api/v1/users/{id}/password

Path variable : idUser

Tipe data Body:
- password -> String

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

Endpoint : PUT /api/v1/users/{id}/mpin

Path variable : idUser

Tipe data Body:
- mpin -> String

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
    "message" : "MPIN berhasil dibuat" 
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

Endpoint : POST /api/v1/users/{id}/confirm-mpin

Path variable : idUser

Tipe data Body:
- mpin -> String

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

```json
{
    "status" : 400,
    "message" : "MPIN tidak sama" 
}
```

## Belum punya akun & sudah punya rekening


### Email Confirmation / OTP Generate

Endpoint : POST /api/v1/users/otp-generate

Tipe data Body:
- email -> String

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
        "idUser": 1,
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

Endpoint : PUT /api/v1/users/{id}/otp-verification

Path variable : idUser

Tipe data Body:
- otp -> String

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

Endpoint : POST api/v1/users/confirm-accounts

Tipe data Body:
- noRekening -> Long

Request Body :

```json
{
    "noRekening" : 7712345683214745
}
```

Response Body (success) :

```json
{
    "status" : 200,
    "message" : "Sukses"
    "data": {
        "namaLengkap": "Suparman",
        "nik": "1234567891012345"
    },
    
}
```

Response Body (failed) :

```json
{
    "status" : 400,
    "message" : "Nomor rekening belum terdaftar"
}
```

### Create Password

Endpoint : PUT /api/v1/users/{id}/password

Path variable : idUser

Tipe data Body:
- password -> String

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

Endpoint : PUT /api/v1/users/{id}/mpin

Path variable : idUser

Tipe data Body:
- mpin -> String

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
    "message" : "MPIN berhasil dibuat" 
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

Endpoint : POST /api/v1/users/{id}/confirm-mpin

Path Variable = idUser

Tipe data Body:
- mpin -> String

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

```json
{
    "status" : 400,
    "message" : "MPIN tidak sama" 
}
```

### User Login


Endpoint : POST /api/v1/users/login

Tipe data Body:
- email -> String
- password -> String
  
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
    "data": {
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmYWhyaXphbHNob2Z5YW5heml6QGdtYWlsLmNvbSIsImlhdCI6MTY5OTY3MDE0NSwiZXhwIjoxNjk5NjcxNTg1fQ.-X6w9Wipej5jf8JvIm7xt5z7TyQowSNtqWhFBjON0Zw"
    },
    "message": "Login Berhasil!",
    "status": 200
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

Endpoint : PUT /api/v1/profiling/change-password


Tipe data Body:
- oldPassword -> String
- newPassword -> String
- confirmPassword -> String

Authorization Type Bearer Token : "USER_TOKEN" 

Request Body :

```json
{
    "oldPassword" : "rahasia123",
    "newPassword" : "12345678",
    "confirmPassword" : "12345678"    
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

### Get Accounts & Users

Endpoint : GET /api/v1/profiling/accounts


Authorization Type Bearer Token : "USER_TOKEN" 


Response Body (success) :

```json
{
    "data": {
        "nik": "1231231231312315",
        "email": "kevin@gmail.com",
        "rekening": [
            {
                "noRekening": 7727272726677,
                "saldo": 2000000.0,
                "createdAt": "2023-11-20T08:39:57.878+00:00",
                "tipeRekening": {
                    "idTipe": 1,
                    "namaTipe": "GOLD",
                    "limitTransfer": "500000000"
                }
            }
        ]
    },
    "message": "Sukses",
    "status": 200
}
```

Response Body (failed) :

```json
{
    "status" : 400,
    "message" : "User tidak ditemukan" 
}
```


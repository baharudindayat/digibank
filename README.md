# Auth API Documentation

Base URL: http://digibank/api/v1


## Belum punya akun & Rekening

### Choose Rekening Type / Card Type

Setelah user pilih kartu, data tipe rekening ditampung di fe, nantinya akan dipost ketika user klik button lanjut pada isi data diri/cif

Endpoint : GET/users/choose-card

Response : 

```json
{
    "status" : 200,
    "data" :[
        {
            "id" : 1,
            "nama" : "Silver",
            "limit_transfer" : "5 Juta"
        },
        {
            "id" : 2,
            "nama" : "Gold",
            "limit_transfer" : "10 Juta"
        },
        {    
            "id" : 3,
            "nama" : "Platinum",
            "limit_transfer" : "15 Juta"
        },
    ]
}
```


### OTP Generate / Email Confirmation


Endpoint : POST/users/otp-generate

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
    "otp" : "1234",
    "id_user" : 1,
}
```

### OTP Verification / OTP Confirmation


Endpoint : POST /users/otp-verification

Request Body :

```json
{
     "otp" : "1234"
}
```

Response Body (succes) :

```json
{
    "status" : 200,
    "message" : "Success"
}
```


### CIF


Endpoint : POST /users/cif

Request Body :

```json
{
    "nik" : "123456",
    "alamat" : "123456",
    "nama_lengkap" : "Suparman"
    "pekerjaan" : "PNS",
    "penghasilan" : "100.000.000",
   
}
```

Response Body (success) :

```json
{
    "status" : 200,
    "message" : "success",
    "id_cif" : 1,
    "no_rek" : "1234567890"
}
```

### Create Rekening


Endpoint : POST /users/create-rekening

Request Body :

```json
{
    "no_rek" : "1234567890",
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


Endpoint : POST /users/:id_user/password

Request Body :

```json
{
    "id_cif" : 1
    "password" : "rahasia1"
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
    "status" : 408,
    "message" : "Maaf! Kata Sandi gagal disimpan. Silakan masukkan ulang Kata Sandi" 
}
```


### Create MPIN


Endpoint : POST /users/:id_user/mpin

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
    "message" : "Selamat! Akun Berhasil dibuat. Silakan Melakukan Login." 
}
```


## Belum punya akun & sudah punya rekening

### Konfirmasi Rekening

Endpoint : POST /users/confirm-rekening

Request Body :

```json
{
    "nomor_rekening" : "1234563445"
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

Endpoint : GET /users/:id_cif/confirm-cif


Response Body (success) :

```json
{
    "nik" : "123456",
    "nama_lengkap" : "Suparman"
}
```



### User Login


Endpoint : POST /users/login

Request Body :

```json
{
    "email" : "fahrizalshofyanaziz@gmail.com"
    "password" : "rahasia1"
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




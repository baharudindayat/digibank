# Auth API Documentation

Base URL [https://digibank/api/v1](https://digibank/api/v1)

## Validasi Rekening



## Create Rekening



Endpoint : POST /create-rekening

Request Body :

```json
{
    "tipe_kartu" : "platinum",
}
```

Response Body (succes) :

```json
{
    "status" : 200,
    "errors": null
}
```


Endpoint : POST /users/validate

Request Body :

```json
{
    "nik" : "3304060511010002",
    "email" : "fahrizalshofyanaziz@gmail.com"
}
```

Response Body (succes) :

```json
{
    "status" : 200,
    "validate" : true,
    "message" : "Selamat! NIK dan Email Berhasil dikonfirmasi"
}
```


Response Body (failed nik) :

```json
{
    "status" : 401,
    "validate" : false,
    "message" : "Maaf! NIK yang dimasukkan tidak terdaftar. Pastikan memasukkan NIK yang benar"
}
```


Response Body (failed email) :

```json
{
    "status" : 401,
    "validate" : false,
    "message" : "Email yang dimasukkan salah atau tidak terdaftar. pastikan email benar atau silahkan registrasi dahulu"
    
}
```





## MPIN



Endpoint : POST users/:id/mpin

Request Body :

```json
{
    "mpin" : "123456",
    "konfirmasi_mpin" : "123456"
}
```

Response Body (succes) :

```json
{
    "status" : 200,
    "errors" : null
}
```


## User Registrasi



Endpoint : POST /users/registrasi

Request Body :

```json
{
    "nik" : "3304060511010002",
    "nama_lengkap" : "Fahrizal Shofyan Aziz",
    "no_telepon" : "0895384163787",
    "email" : "fahrizalshofyanaziz@gmail.com"
    "password" : "rahasia1"
}
```

Response Body (success) :

```json
{
    "status" : 200,
    "message" : "zTRIq" // otp
}
```



Response Body (failed email) :

```json
{
    "status" : 400,
    "message" : "Email harus sesuai dengan format penulisan"
}
```

Response Body (failed password) :

```json
{
    "status" : 400,
    "message" : "Password harus terdiri dari minimal 8 karakter"
}
```



## User Login



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
    "data" : {
        "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1dWlkIjoiYTQzNjUzYjEtMjk2Ni00NDY1LWE0YjktZjRmYmM0OTE3NzVhIiwiaWF0IjoxNjg2MzIxMzQ0LCJleHAiOjE2ODYzMjE2NDR9.mzHMPKXzlOkHpRFAq3Sol5ALtc5TH0l_o4aN4YZxLMA"
        "expaired_at" : 1223343434 //milisecond
    }
    
}
```


Response Body (failed email&password) :

```json
{
    "status" : 401,
    "message" : "Maaf! Email dan Kata Sandi yang dimasukkan salah. Pastikan Email dan Kata Sandi benar."
}
```


Response Body (failed email) :

```json
{
    "status" : 400,
    "message" : "Email harus sesuai dengan format penulisan"
}
```

Response Body (failed password) :

```json
{
    "status" : 400,
    "message" : "Password harus terdiri dari minimal 8 karakter"
}
```


## OTP Verify



Endpoint : POST /users/otpver

Request Body :

```json
{
    "otp": "zTRIq"
}
```

Response Body (success) :

```json
{
    "status": 200,
    "message": "OTP terverifikasi",
}
```


Response Body (failed otp) :

```json
{
    "status" : 400,
    "message" : "OTP tidak terverifikasi"
}
```

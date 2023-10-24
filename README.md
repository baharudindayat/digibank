# Auth API Documentation

Base URL: http://digibank/api/v1




## Choose Rekening Type / Card Type

Setelah user pilih kartu, data tipe rekening ditampung di fe, nantinya akan dipost ketika user klik button lanjut pada isi data diri/cif

Endpoint : GET/users/pilih-rekening

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


Endpoint : POST/users/pilih-rekening

Request Body :

```json
{
    "id_tipe_kartu" : "1",
    "id_tipe_kartu" : "2",
    "id_tipe_kartu" : "3"
}
```

Response Body (succes) :

```json
{
    "status" : 200,
    tipe-rekekning
}
```



## OTP Generate / Email Confirmation


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
    "OTP" : 123456,
    "id_user" : 1,
}
```



## CIF



Endpoint : POST/users/:id/cif

Request Body :

```json
{
    "nik" : "123456",
    "alamat" : "123456",
    "pekerjaan" : "PNS",
    "penghasilan" : "100.000.000"
}
```

Response Body (succes) :

```json
{
    "status" : 200,
    "message" : "success",
    "no_rek" : "1234567890"
}
```



## Create Password



Endpoint : POST/users/:id/password

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

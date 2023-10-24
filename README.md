# Auth API Documentation

Base URL: http://digibank/api/v1


## Belum punya akun & Rekening

### Choose Rekening Type / Card Type

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
    "otp" : "zTRIq",
    "id_user" : 1,
}
```

### OTP Verification / OTP Confirmation


Endpoint : POST /users/otp-verification

Request Body :

```json
{
     "otp" : "zTRIq"
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
    "id_user" : 1
    "nik" : "123456",
    "alamat" : "123456",
    "nama_lengkap" : "Suparman"
    "pekerjaan" : "PNS",
    "penghasilan" : "100.000.000",
    "id_tipe_rekening" : 3
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


### Create Password


Endpoint : POST /users/:id_user/password

Request Body :

```json
{
    "password" : "rahasia1"
}
```

Response Body (success) :

```json
{
    "status" : 200,
    "message" : "Success" 
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
    "message" : "Success" 
}
```


## Belum punya akun & sudah punya rekening

### Konfirmasi Rekening

Endpoint : POST /users/users/confirm-rekening

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

Response Body (failed) :

```json
{
    "status" : 401,
    "message" : "Nomor Rekening tidak terdaftar"
    "id_cif" : null
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




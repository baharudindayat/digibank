# SQL CODE DATABASE 

Buat dulu database dengan nama digibank di postgre database, lalu copas code ini ke file database.sql di environment kesayangan masing - masing :slightly_smiling_face: . Oiya, kalo belum ada filenya buat dulu file .sql nya di main project.


```

DROP TABLE IF EXISTS cif CASCADE  ;
DROP TABLE IF EXISTS rekening CASCADE ;
DROP TABLE IF EXISTS tipe_rekening CASCADE ;
DROP TABLE IF EXISTS users CASCADE ;
DROP TYPE IF EXISTS status_user;


CREATE TYPE status_user AS ENUM ('Terblokir', 'Inactive', 'Active');

CREATE TABLE cif (
     id_cif SERIAL NOT NULL,
     nik VARCHAR,
     nama_lengkap VARCHAR,
     alamat VARCHAR,
     pekerjaan VARCHAR,
     penghasilan VARCHAR,
     created_cif TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
     PRIMARY KEY (id_cif),
     CONSTRAINT unique_nik UNIQUE (nik)
);

CREATE TABLE users (
    id_user  SERIAL NOT NULL,
    email VARCHAR,
    password VARCHAR,
    status_user status_user DEFAULT 'Active'::status_user,
    mpin VARCHAR,
    id_cif INT NOT NULL,
    created_user TIMESTAMP WITH TIME ZONE,
    count_blocked_mpin INT,
    otp VARCHAR,
    active BOOLEAN,
    created_otp TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (id_user),
    CONSTRAINT unique_email UNIQUE (email),
    CONSTRAINT fk_cif FOREIGN KEY  (id_cif) REFERENCES cif(id_cif)
--         ON DELETE CASCADE ON UPDATE CASCADE

);


CREATE TABLE tipe_rekening (
    id_tipe INT NOT NULL ,
    nama VARCHAR,
    limit_transfer VARCHAR,
    PRIMARY KEY (id_tipe)
);

INSERT INTO tipe_rekening (id_tipe, nama, limit_transfer)
VALUES (1, 'Silver', '5 Juta'),(2, 'Gold', '10 Juta'),(3, 'Platinum', '15 Juta');


CREATE TABLE rekening (
    no_rekening VARCHAR NOT NULL ,
    saldo BIGINT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    id_tipe_rekening INT,
    id_cif INT,
    PRIMARY KEY (no_rekening),
    CONSTRAINT fk_id_tipe_rekening FOREIGN KEY (id_tipe_rekening) REFERENCES tipe_rekening(id_tipe),
    CONSTRAINT fk_id_cif FOREIGN KEY  (id_cif) REFERENCES cif(id_cif)
)

```


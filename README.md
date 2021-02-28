# 占い師 (Uranaishi)

**Peramal untuk membuat rekomendasi jurusan kuliah yang diambil, _dependency_
perangkat lunak yang harus diinstall, dan lain-lain.**

## _Prerequisite_
* Java 15
> (Mungkin bisa di versi sebelumnya, tapi _developer_ membuat aplikasi
> ini di Java 15 jadi kompatibilitas di ersi lain tidak dijamin.)

## Instalasi
1. Unduh berkas `app.zip` di _releases_ _repository_ GitHub ini.
    1. Jika dibutuhkan, boleh diunduh juga `data.zip` untuk contoh berkas berisi
        graf.
3. Ekstrak berkas `app.zip`
4. Buka `app.bat` menggunakan Command Prompt/PowerShell jika di Windows, jika
    di sistem operasi berbasis UNIX (BSD, Linux, MacOS X) buka `app` (dengan
    `./app`) di terminal.
    1. Jika mengunduh `data.zip`, letakkan `data.zip` di direktori `bin` dari
    `data.zip` (langkah ini untuk memudahkan mengikuti contoh)

## Contoh
`./app -f data/grafTest7.txt`

Contoh _output_ dapat dilihat di direktori `docs` di berkas `laporan.pdf` bab
3.

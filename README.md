# tubes-oop-pokemon
Monster Saku merupakan permainan PvP (Player vs Player) berbasis CLI (Command Line Interface) yang dimainkan oleh 2 orang pemain yang akan saling bertarung. Permainan ini mengimplementesikan simulasi pertarungan antar monster saku. Masing-masing pemain akan memiliki kombinasi 6 monster yang dipilih secara acak oleh sistem dalam setiap permainan.

### Ketentuan permainan adalah sebagai berikut.
1. Permainan akan dimulai ketika pengguna memasukkan command “START”.
2. Pemain akan diminta untuk memasukkan nama pemain I dan nama pemain II.
3. Setiap pemain akan mendapatkan 6 monster secara acak. Setiap monster akan mendapatkan move yang dapat digunakan untuk bertarung.
4. Monster yang akan memulai giliran pertama akan dipilih secara acak.
5. Pada setiap giliran, pemain boleh melakukan MOVE(bertarung) atau SWITCH(menukar monster). Jenis move yang tersedia ada 4, yaitu : Normal, Special, Default, dan Status.
6. Pada saat bertarung, monster akan berada dalam kondisi status tertentu. Terdapat 4 jenis status, yaitu : BURN, POISON, SLEEP, PARALYZE.
7. Permainan selesai ketika salah satu pemain tidak memiliki monster untuk dipertarungkan dan pemain tersebut dinyatakan kalah.

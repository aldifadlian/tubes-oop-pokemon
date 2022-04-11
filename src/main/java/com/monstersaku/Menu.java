package com.monstersaku;
import com.monstersaku.util.*;
import java.util.Scanner;

public class Menu {
    public static void start() {
        // mindahin biar ke sini gimana ya
    }

    public static void help() {
        System.out.println("PANDUAN BERMAIN");
        System.out.println("1. Ketik 'START' pada bagian 'Select' untuk memulai permainan");
        System.out.println("2. Masukkan nama pemain 1 dan pemain 2");
        System.out.println("3. Setiap pemain akan mendapatkan kombinasi 6 monster secara \n   acak melalui sistem");
        System.out.println("4. Setiap monster yang diperoleh akan mendapatkan 'MOVE' untuk \n   bertarung");
        System.out.println("5. Pemain yang mendapat giliran pertama dalam permainan akan \n   dipilih secara acak");
        System.out.println("6. Pada setiap giliran, pemain boleh melakukan 'MOVE' (bertarung) \n   atau 'SWITCH' (menukar monster)");
        System.out.println("7. Terdapat 4 jenis 'MOVE', yaitu: ");
        System.out.println("   a. Normal");
        System.out.println("   b. Special");
        System.out.println("   c. Default");
        System.out.println("   d. Status");
        System.out.println("8. Pada saat bertarung, monster akan berada dalam kondisi status \n   tertentu.");
        System.out.println("9. Terdapat 4 kondisi status :");
        System.out.println("   a. BURN");
        System.out.println("   b. POISON");
        System.out.println("   c. SLEEP");
        System.out.println("   d. PARALYZE");
        System.out.println("10. Permainan berakhir ketika salah satu pemain tidak memiliki monster \n    untuk dipertarungkan dan pemain tersebut dinyatakan kalah");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("SELECT MENU:");
        System.out.println(">> START");
        System.out.println(">> EXIT");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void exit() {
        try {
            System.out.println("processing to exit from the game...");
            Thread.sleep(1000);
            System.out.println("Thank you for playing!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void welcome() {
        System.out.println("");
        System.out.println("    ██╗    ██╗███████╗██╗      ██████╗ ██████╗ ███╗   ███╗███████╗              ");
        System.out.println("    ██║    ██║██╔════╝██║     ██╔════╝██╔═══██╗████╗ ████║██╔════╝              ");
        System.out.println("    ██║ █╗ ██║█████╗  ██║     ██║     ██║   ██║██╔████╔██║█████╗                ");
        System.out.println("    ██║███╗██║██╔══╝  ██║     ██║     ██║   ██║██║╚██╔╝██║██╔══╝                ");
        System.out.println("    ╚███╔███╔╝███████╗███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗              ");
        System.out.println("    ╚══╝╚══╝ ╚══════╝╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝               ");
        System.out.println("          ╔╦╗╔═╗╔╗╔╔═╗╔╦╗╔═╗╦═╗  ╔═╗╔═╗╦╔═╦ ╦  ╔═╗╔═╗╔╦╗╔═╗                     ");
        System.out.println("          ║║║║ ║║║║╚═╗ ║ ║╣ ╠╦╝  ╚═╗╠═╣╠╩╗║ ║  ║ ╦╠═╣║║║║╣                      ");
        System.out.println("          ╩ ╩╚═╝╝╚╝╚═╝ ╩ ╚═╝╩╚═  ╚═╝╩ ╩╩ ╩╚═╝  ╚═╝╩ ╩╩ ╩╚═╝                     ");
        System.out.println("");                                                                                                   
    }

    public static int action(Player playerOwn, Player playerOpponent){
        boolean isValid = true;
        int act = -1;

        Scanner scanAction = new Scanner(System.in);

        while(isValid) {
            System.out.printf("Select : ");
            String action = scanAction.nextLine();
            if (action.equals("MOVE")) {
                act = playerOwn.move();
                isValid = false;
            }
            else if (action.equals("SWITCH")) {
                playerOwn.switchMonster();
                act = 0;
                isValid = false;
            }
            else if (action.equals("VIEW MONSTER INFO")) {
                playerOwn.viewMonsters();
                playerOpponent.viewMonsters();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("SELECT ACTION: ");
                System.out.println(">> MOVE");
                System.out.println(">> SWITCH");
                System.out.println(">> VIEW GAME INFO");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                isValid = false;
            }
            else if (action.equals("VIEW GAME INFO")) {
                // belum
                isValid = false;
            }
            else {
                System.out.println("Input action salah. Harap ulangi!");
            }
        }
        return act;
    }

}

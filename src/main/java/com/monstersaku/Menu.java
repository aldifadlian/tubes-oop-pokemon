package com.monstersaku;
import com.monstersaku.util.*;
import java.util.Scanner;
import java.util.Random;

public class Menu {
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

    public static int action(Player playerPlay, Player playerOpponent){
        boolean isValid = true;
        int act = -1;

        Scanner scanAction = new Scanner(System.in);

        while(isValid) {
            System.out.printf("Select : ");
            String action = scanAction.nextLine();
            if (action.equals("MOVE")) {
                act = playerPlay.move();
                isValid = false;
            }
            else if (action.equals("SWITCH")) {
                playerPlay.switchMonster();
                act = 0;
                isValid = false;
            }
            else if (action.equals("VIEW MONSTER INFO")) {
                playerPlay.viewMonsters();
                isValid = false;
            }
            else if (action.equals("VIEW GAME INFO")) {
                System.out.println("");
                System.out.println("===== INFO TURN ====");
                System.out.println(playerPlay.getPlayerName() + " sedang dalam turn");
                System.out.println("");
                System.out.println("===== Info Player " + playerPlay.getPlayerName() +" =====");
                playerPlay.printInfoActiveMonster();
                playerPlay.printInfoPassiveMonster();
                System.out.println("");
                System.out.println("===== Info Player " + playerOpponent.getPlayerName()+" =====");
                playerOpponent.printInfoActiveMonster();
                playerOpponent.printInfoPassiveMonster();
                isValid = false;
            }
            else {
                System.out.println("Input action salah. Harap ulangi!");
            }
        }
        return act;
    }

    /*
    public static void urutanMove(Player playerPlay, Player playerOpponent){
        int indexPlay = playerPlay.move();
        int indexOpp = playerOpponent.move();
        int prioPlay = playerPlay.getActiveMonster().getMoves().get(indexPlay-1).getPriority();
        int prioOpp = playerOpponent.getActiveMonster().getMoves().get(indexOpp-1).getPriority();
        System.out.println("xxxxxxxxxx");
        if (prioPlay > prioOpp) {
            System.out.println("TESSSSSSSSSSSSS");
            System.out.println("Player "+playerPlay.getPlayerName()+"move duluan");
            //System.out.printf("%s melakukan %s%n",playerPlay.getActiveMonster().getName(), playerPlay.getActiveMonster().getMoves().get(playerPlay.move()).getMoveName());
            playerPlay.getActiveMonster().getMoves().get(playerPlay.move()).damage(playerPlay.getActiveMonster(), playerOpponent.getActiveMonster());
            System.out.printf("HP %s sekarang : %s%n",playerPlay.getActiveMonster().getName(),playerPlay.getActiveMonster().getStats().getHealthPoint());
            System.out.printf("HP %s sekarang : %s%n",playerOpponent.getActiveMonster().getName(),playerOpponent.getActiveMonster().getStats().getHealthPoint());
            if (playerOpponent.isMonsterDie()){
                System.out.printf("astaga %s sudah meninggal",playerOpponent.getActiveMonster().getName());
                if (playerOpponent.getPlayerMonster() != null){
                    playerOpponent.chooseMonster();
                }
                else {
                    System.out.printf("%S MENANG HOREEE", playerPlay.getPlayerName());
                    exit();
                }
            }
            else{
                System.out.println("Player "+playerOpponent.getPlayerName()+"move selanjutnya");
                //System.out.printf("%s melakukan %s%n",playerOpponent.getActiveMonster().getName(), playerOpponent.getActiveMonster().getMoves().get(playerOpponent.move()).getMoveName());
                playerOpponent.getActiveMonster().getMoves().get(playerOpponent.move()).damage(playerOpponent.getActiveMonster(), playerPlay.getActiveMonster());
                System.out.printf("HP %s sekarang : %s%n",playerPlay.getActiveMonster().getName(),playerPlay.getActiveMonster().getStats().getHealthPoint());
                System.out.printf("HP %s sekarang : %s%n",playerOpponent.getActiveMonster().getName(),playerOpponent.getActiveMonster().getStats().getHealthPoint());
                if (playerPlay.isMonsterDie()){
                    System.out.printf("astaga %s sudah meninggal",playerOpponent.getActiveMonster().getName());
                    if (playerPlay.getPlayerMonster() != null){
                        playerPlay.chooseMonster();
                    }
                    else {
                        System.out.printf("%S MENANG HOREEE", playerOpponent.getPlayerName());
                        exit();
                    }
                }
            }
        } 
        else if (playerPlay.getActiveMonster().getMoves().get(playerPlay.move()).getPriority() < playerOpponent.getActiveMonster().getMoves().get(playerOpponent.move()).getPriority()) {
            System.out.println("Player "+playerOpponent.getPlayerName()+"move duluan");
        } 
        else {
            System.out.println("Priority pemain sama. Urutan ditentukan pada speed");
            if (playerPlay.getActiveMonster().getStats().getSpeed() > playerOpponent.getActiveMonster().getStats().getSpeed()) {
                System.out.println("Player "+playerPlay.getPlayerName()+"move duluan");

            } 
            else if (playerPlay.getActiveMonster().getStats().getSpeed() < playerOpponent.getActiveMonster().getStats().getSpeed()) {
                System.out.println("Player "+playerOpponent.getPlayerName()+"move duluan");

            }
            else {
                System.out.println("Speed pemain sama. Urutan dipilih random");
                Random random = new Random();
                int urutan = random.nextInt(2) + 1;
                if (urutan == 1) {

                }
                else {

                }
            }
        } 
    } */
}

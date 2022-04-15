package com.monstersaku;

import com.monstersaku.util.*;
import com.monstersaku.util.CSVReader;

import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ArrayList<Monster> listMonster = new ArrayList<Monster>();
        ArrayList<Move> listMove = new ArrayList<Move>();
        HashMap<Pair<ElementType, ElementType>, Double> effectivityMap = new HashMap<>();
        // baca csv
        try{
            // baca effectivity
            CSVReader bacaEffectivity = new CSVReader(new File(Main.class.getResource("configs/element-type-effectivity-chart.csv").toURI()), ";");
            bacaEffectivity.setSkipHeader(true);
            List<String[]> isiEffectivity = bacaEffectivity.read();
            for (String[] line : isiEffectivity) {
                ElementType elMoveType = ElementType.valueOf(line[0]);
                ElementType elMonsType = ElementType.valueOf(line[1]);
                Double effectivityValue = Double.parseDouble(line[2]);
                effectivityMap.put(new Pair<ElementType, ElementType>(elMoveType, elMonsType), effectivityValue);
            }
            // baca move
            CSVReader bacaMoves = new CSVReader(new File(Main.class.getResource("configs/movepool.csv").toURI()), ";");
            bacaMoves.setSkipHeader(true);
            List<String[]> isiMoves = bacaMoves.read();
            for (String[] line : isiMoves) {
                Integer idMove = Integer.parseInt(line[0]);
                MoveType moveType = MoveType.valueOf(line[1]);
                String namaMove = line[2];
                ElementType elementType = ElementType.valueOf(line[3]);
                Integer accuracyMove = Integer.parseInt(line[4]);
                Integer priorityMove = Integer.parseInt(line[5]);
                Integer ammunitionMove = Integer.parseInt(line[6]);
                String targetMove = line[7];
                if(moveType.equals(MoveType.STATUS)){
                    String attTypeMove = line[8];
                    Double effectMove = Double.parseDouble(line[9]);
                    StatusMove statusMove = new StatusMove(idMove, namaMove, elementType, accuracyMove, priorityMove, ammunitionMove, targetMove, attTypeMove, effectMove);
                    listMove.add(statusMove);
                }
                else if (moveType.equals(MoveType.NORMAL)) {
                    Double basePower = Double.parseDouble(line[8]);
                    NormalMove normalMove = new NormalMove(idMove, namaMove, elementType, accuracyMove, priorityMove, ammunitionMove, targetMove, basePower);
                    listMove.add(normalMove);
                }
                else if (moveType.equals(MoveType.SPECIAL)) {
                    Double basePower = Double.parseDouble(line[8]);
                    SpecialMove specialMove = new SpecialMove(idMove, namaMove, elementType, accuracyMove, priorityMove, ammunitionMove, targetMove, basePower);
                    listMove.add(specialMove);
                }
            }
            // monster
            CSVReader bacaMonster = new CSVReader(new File(Main.class.getResource("configs/monsterpool.csv").toURI()), ";");
            bacaMonster.setSkipHeader(true);
            List<String[]> isiMonster = bacaMonster.read();
            for (String[] line : isiMonster){
                String namaMons = line[1];
                ArrayList<ElementType> elementTypes = new ArrayList<ElementType>();
                String eltypeMons = line[2];
                String[] arrayEltype = eltypeMons.split(",",7);
                for(String eltype : arrayEltype){
                    if(eltype.equals("NORMAL")){
                        elementTypes.add(ElementType.NORMAL);
                    }
                    if(eltype.equals("FIRE")){
                        elementTypes.add(ElementType.FIRE);
                    }
                    if(eltype.equals("WATER")){
                        elementTypes.add(ElementType.WATER);
                    }
                    if(eltype.equals("GRASS")){
                        elementTypes.add(ElementType.GRASS);
                    }
                }
                String statsMons = line[3];
                String[] arrayStats = statsMons.split(",",7);
                ArrayList<Double> arrayStatsList = new ArrayList<Double>();
                for(String statsArray : arrayStats){
                    Double eachStat = Double.parseDouble(statsArray);
                    arrayStatsList.add(eachStat);
                }
                Stats baseStatsMons = new Stats(arrayStatsList.get(0), arrayStatsList.get(1), arrayStatsList.get(2), arrayStatsList.get(3), arrayStatsList.get(4), arrayStatsList.get(5));
                
                String moveMons = line[4];
                String[] arrayMove = moveMons.split(",",7);
                ArrayList<Move> arrMoveMons = new ArrayList<Move>();
                DefaultMove defaultMove = new DefaultMove();
                arrMoveMons.add(defaultMove);
                for(int i = 0; i < arrayMove.length; i++){
                    arrMoveMons.add(listMove.get(Integer.valueOf(arrayMove[i])-1));
                }
                Integer idMons = Integer.parseInt(line[0]);
                Monster buatMonster = new Monster(idMons, namaMons, elementTypes, baseStatsMons, arrMoveMons);
                listMonster.add(buatMonster);
            }
        } catch (Exception e){
            e.getMessage();
        }
        boolean play = true;
        Scanner input = new Scanner(System.in);
        Menu.welcome();
        System.out.println("SELECT MENU:");
        System.out.println(">> START");
        System.out.println(">> HELP");  
        System.out.println(">> EXIT");
        System.out.println("");

        while(play) {
            System.out.printf("Select : ");
            String command = input.nextLine();
            if (command.equals("START")) {
                // masukkan pemain
                Scanner scanPlayer = new Scanner(System.in);
                System.out.println("Masukkan nama pemain 1 : ");
                String namaPemain1 = scanPlayer.nextLine();
                System.out.println("Masukkan nama pemain 2 : ");
                String namaPemain2 = scanPlayer.nextLine();
                System.out.printf("%nPemain 1 : %s", namaPemain1);
                System.out.printf("%nPemain 2 : %s%n", namaPemain2);
                // acak monster
                Random acak = new Random();
                Integer batasMonster = listMonster.size();
                ArrayList<Monster> listMonsPemain1 = new ArrayList<Monster>();
                for(int i = 0; i < 6; i++){
                    Integer acakMonster = acak.nextInt(batasMonster);
                    Monster monster1 = listMonster.get(acakMonster);
                    while (listMonsPemain1.contains(monster1)){
                        acakMonster = acak.nextInt(batasMonster);
                        monster1 = listMonster.get(acakMonster);
                    }    
                    listMonsPemain1.add(monster1);
                }
                ArrayList<Monster> listMonsPemain2 = new ArrayList<Monster>();
                for(int i = 0; i < 6; i++){
                    Integer acakMonster = acak.nextInt(batasMonster);
                    Monster monster2 = listMonster.get(acakMonster);
                    while ((listMonsPemain1.contains(monster2)) || (listMonsPemain2.contains(monster2))){
                        acakMonster = acak.nextInt(batasMonster);
                        monster2 = listMonster.get(acakMonster);
                    }    
                    listMonsPemain2.add(monster2);
                }
                Player pemain1 = new Player(namaPemain1, listMonsPemain1);
                Player pemain2 = new Player(namaPemain2, listMonsPemain2);

                Player playerPlay = pemain1;
                Player playerOpponent = pemain2;

                System.out.println("");
                System.out.println("==== GAME START! ====");
                System.out.println("");

                try {
                    System.out.println("Randomize Monster Player!");
                    Thread.sleep(1000);
                    playerPlay.printInfoMonster();
                    Thread.sleep(1000);
                    playerOpponent.printInfoMonster();
                    Thread.sleep(1000);
                    System.out.println("");
                    System.out.println("READY SET GO!!!");
                    System.out.println("");
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                int idRound = 1;
                boolean isPlay = true;

                while (isPlay) {

                    int[] players = {-1, -1};
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Round "+idRound);

                    boolean isRound = true;
                    int ctr = 1;
                    while (isRound) {
                        // penggantian player
                        if (ctr%2 != 0){
                            playerPlay = pemain1;
                            playerOpponent = pemain2;
                        }
                        else if (ctr%2 == 0){
                            playerPlay = pemain2;
                            playerOpponent = pemain1;
                        }

                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println(playerPlay.getPlayerName() + "'s turn");

                        if (idRound == 1){
                            playerPlay.initialMonster();
                        }
                        else{
                            if (playerPlay.isMonsterDie()) {
                                playerPlay.chooseMonster();
                            }
                            else if (playerPlay.getActiveMonster().getStatusCondition().getSleep() == true){
                                //cek sleep atau tidak
                                int initial = 0;
                                for (Monster monster : listMonsPemain1){
                                    int x = monster.getStatusCondition().getMasihSleep(); 
                                    if (x > 0) {
                                        monster.getStatusCondition().setMasihSleep(x-1);
                                    }
                                    initial = initial + 1;
                                }
                                System.out.println("Monster " + playerPlay.getActiveMonster().getName() + " kena sleep nih. Pilih monster lain ya!");
                                playerPlay.chooseMonster();
                            }
                            else {
                                playerPlay.printInfoActiveMonster();
                            }
                        }
                        

                        boolean isTurn = true;
                        while (isTurn) {
                            int act = 0;
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                            System.out.println("SELECT ACTION: ");
                            System.out.println(">> MOVE");
                            System.out.println(">> SWITCH");
                            System.out.println(">> VIEW MONSTER INFO");
                            System.out.println(">> VIEW GAME INFO");
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                            act = Menu.action(playerPlay, playerOpponent);
                            
                            players[ctr-1] = act;
                            
                            if (act > 0) {
                                isTurn = false;
                            }
                        }
                        ctr++;
                        if (ctr > 2){
                            isRound = false;
                        }
                    }
                    // deklarasi ulang  
                    playerPlay = pemain1;
                    playerOpponent = pemain2;

                    // cek masih kena efek
                    if (playerPlay.getActiveMonster().getStatusCondition().getBurn() == true){
                        playerPlay.getActiveMonster().getStats().setHealthPoint(playerPlay.getActiveMonster().getStats().getHealthPoint()-(playerPlay.getActiveMonster().getStats().getMaxHP()/8));        
                        System.out.printf("%s's %s terkena BURN, HP berkurang 1/8.%n", playerPlay.getPlayerName(), playerPlay.getActiveMonster().getName());
                    }
                    else if (playerPlay.getActiveMonster().getStatusCondition().getPoison() == true){
                        playerPlay.getActiveMonster().getStats().setHealthPoint(playerPlay.getActiveMonster().getStats().getHealthPoint() - (playerPlay.getActiveMonster().getStats().getMaxHP()/16));
                        System.out.printf("%s's %s terkena POISON, HP berkurang 1/16.%n", playerPlay.getPlayerName(), playerPlay.getActiveMonster().getName()); 
                    }
                    else if (playerPlay.getActiveMonster().getStatusCondition().getParalyze() == true){
                        System.out.printf("%s's %s terkena PARALYZE ampuh, tidak dapat bergerak round ini.%n", playerPlay.getPlayerName(), playerPlay.getActiveMonster().getName()); 
                    }
                    if (playerOpponent.getActiveMonster().getStatusCondition().getBurn() == true){
                        playerOpponent.getActiveMonster().getStats().setHealthPoint(playerOpponent.getActiveMonster().getStats().getHealthPoint()-(playerOpponent.getActiveMonster().getStats().getMaxHP()/8));          
                        System.out.printf("%s's %s terkena BURN, HP berkurang 1/8.%n", playerOpponent.getPlayerName(), playerOpponent.getActiveMonster().getName());
                    }
                    else if (playerOpponent.getActiveMonster().getStatusCondition().getPoison() == true){
                        playerOpponent.getActiveMonster().getStats().setHealthPoint(playerOpponent.getActiveMonster().getStats().getHealthPoint() - (playerOpponent.getActiveMonster().getStats().getMaxHP()/16));
                        System.out.printf("%s's %s terkena POISON, HP berkurang 1/16.%n", playerOpponent.getPlayerName(), playerOpponent.getActiveMonster().getName()); 
                    }
                    else if (playerOpponent.getActiveMonster().getStatusCondition().getParalyze() == true){
                        System.out.printf("%s's %s terkena stunt PARALYZE, tidak dapat bergerak round ini.", playerOpponent.getPlayerName(), playerOpponent.getActiveMonster().getName()); 
                    }
                    // set 0
                    if (playerOpponent.getActiveMonster().getStats().getHealthPoint() < 0.0) {
                        playerOpponent.getActiveMonster().getStats().setHealthPoint(0.0);
                    }
                    if (playerPlay.getActiveMonster().getStats().getHealthPoint() < 0.0) {
                        playerPlay.getActiveMonster().getStats().setHealthPoint(0.0);
                    }
                    
                    int indexPlay = players[0];
                    int indexOpp = players[1];
                    int prioPlay = playerPlay.getActiveMonster().getMoves().get(indexPlay-1).getPriority();
                    int prioOpp = playerOpponent.getActiveMonster().getMoves().get(indexOpp-1).getPriority();

                    // prioritas serang
                    if (prioPlay > prioOpp) {
                        System.out.println("Player "+playerPlay.getPlayerName()+" move duluan");
                        System.out.printf("%s melakukan %s%n",playerPlay.getActiveMonster().getName(), playerPlay.getActiveMonster().getMoves().get(indexPlay-1).getMoveName());
                        playerPlay.getActiveMonster().getMoves().get(indexPlay-1).damage(playerPlay.getActiveMonster(), playerOpponent.getActiveMonster());
                        System.out.printf("HP %s sekarang : %s%n",playerPlay.getActiveMonster().getName(),playerPlay.getActiveMonster().getStats().getHealthPoint());
                        System.out.printf("HP %s sekarang : %s%n",playerOpponent.getActiveMonster().getName(),playerOpponent.getActiveMonster().getStats().getHealthPoint());
                        if (playerOpponent.isMonsterDie()){
                            System.out.printf("astaga %s sudah meninggal%n",playerOpponent.getActiveMonster().getName());
                            if (playerOpponent.getJumlahMons() != 0){
                                System.out.println("");
                                System.out.printf("%s's turn%n",playerOpponent.getPlayerName());
                                playerOpponent.chooseMonster();
                            }
                            else {
                                System.out.println(playerPlay.getPlayerName() + " MENANG HOREEE");
                                Menu.exit();
                                isPlay = false;
                                play = false;
                            }
                        }
                        else{
                            System.out.println("Player "+playerOpponent.getPlayerName()+" move selanjutnya");
                            System.out.printf("%s melakukan %s%n",playerOpponent.getActiveMonster().getName(), playerOpponent.getActiveMonster().getMoves().get(indexOpp-1).getMoveName());
                            playerOpponent.getActiveMonster().getMoves().get(indexOpp-1).damage(playerOpponent.getActiveMonster(), playerPlay.getActiveMonster());
                            System.out.printf("HP %s sekarang : %s%n",playerPlay.getActiveMonster().getName(),playerPlay.getActiveMonster().getStats().getHealthPoint());
                            System.out.printf("HP %s sekarang : %s%n",playerOpponent.getActiveMonster().getName(),playerOpponent.getActiveMonster().getStats().getHealthPoint());
                            if (playerPlay.isMonsterDie()){
                                System.out.printf("astaga %s sudah meninggal%n",playerPlay.getActiveMonster().getName());
                                if (playerPlay.getJumlahMons() != 0){
                                    System.out.println("");
                                    System.out.printf("%s's turn%n",playerPlay.getPlayerName());
                                    playerPlay.chooseMonster();
                                }
                                else {
                                    System.out.println(playerOpponent.getPlayerName() + " MENANG HOREEE");
                                    Menu.exit();
                                    isPlay = false;
                                    play = false;
                                }
                            }
                        }
                    } 
                    else if (prioPlay < prioOpp) {
                        System.out.println("Player "+playerOpponent.getPlayerName()+" move duluan");
                        System.out.printf("%s melakukan %s%n",playerOpponent.getActiveMonster().getName(), playerOpponent.getActiveMonster().getMoves().get(indexOpp-1).getMoveName());
                        playerOpponent.getActiveMonster().getMoves().get(indexOpp-1).damage(playerOpponent.getActiveMonster(), playerPlay.getActiveMonster());
                        System.out.printf("HP %s sekarang : %s%n",playerPlay.getActiveMonster().getName(),playerPlay.getActiveMonster().getStats().getHealthPoint());
                        System.out.printf("HP %s sekarang : %s%n",playerOpponent.getActiveMonster().getName(),playerOpponent.getActiveMonster().getStats().getHealthPoint());
                        if (playerPlay.isMonsterDie()){
                            System.out.printf("astaga %s sudah meninggal%n",playerPlay.getActiveMonster().getName());
                            if (playerPlay.getJumlahMons() != 0){
                                System.out.println("");
                                System.out.printf("%s's turn%n",playerPlay.getPlayerName());
                                playerPlay.chooseMonster();
                            }
                            else {
                                System.out.println(playerOpponent.getPlayerName() + " MENANG HOREEE");
                                Menu.exit();
                                isPlay = false;
                                play = false;
                            }
                        }
                        else{
                            System.out.println("Player "+playerPlay.getPlayerName()+" move selanjutnya");
                            System.out.printf("%s melakukan %s%n",playerPlay.getActiveMonster().getName(), playerPlay.getActiveMonster().getMoves().get(indexPlay-1).getMoveName());
                            playerPlay.getActiveMonster().getMoves().get(indexPlay-1).damage(playerPlay.getActiveMonster(), playerOpponent.getActiveMonster());
                            System.out.printf("HP %s sekarang : %s%n",playerPlay.getActiveMonster().getName(),playerPlay.getActiveMonster().getStats().getHealthPoint());
                            System.out.printf("HP %s sekarang : %s%n",playerOpponent.getActiveMonster().getName(),playerOpponent.getActiveMonster().getStats().getHealthPoint());
                            if (playerOpponent.isMonsterDie()){
                                System.out.printf("astaga %s sudah meninggal%n",playerOpponent.getActiveMonster().getName());
                                if (playerOpponent.getJumlahMons() != 0){
                                    System.out.println("");
                                    System.out.printf("%s's turn%n",playerOpponent.getPlayerName());
                                    playerOpponent.chooseMonster();
                                }
                                else {
                                    System.out.println(playerPlay.getPlayerName() + " MENANG HOREEE");
                                    Menu.exit();
                                    isPlay = false;
                                    play = false;
                                }
                            }
                        }
                    } 
                    else {
                        System.out.println("Priority pemain sama. Urutan ditentukan pada speed");
                        if (playerPlay.getActiveMonster().getStats().getSpeed() > playerOpponent.getActiveMonster().getStats().getSpeed()) {
                            System.out.println("Player "+playerPlay.getPlayerName()+" move duluan");
                            System.out.printf("%s melakukan %s%n",playerPlay.getActiveMonster().getName(), playerPlay.getActiveMonster().getMoves().get(indexPlay-1).getMoveName());
                            playerPlay.getActiveMonster().getMoves().get(indexPlay-1).damage(playerPlay.getActiveMonster(), playerOpponent.getActiveMonster());
                            System.out.printf("HP %s sekarang : %s%n",playerPlay.getActiveMonster().getName(),playerPlay.getActiveMonster().getStats().getHealthPoint());
                            System.out.printf("HP %s sekarang : %s%n",playerOpponent.getActiveMonster().getName(),playerOpponent.getActiveMonster().getStats().getHealthPoint());
                            if (playerOpponent.isMonsterDie()){
                                System.out.printf("astaga %s sudah meninggal%n",playerOpponent.getActiveMonster().getName());
                                if (playerOpponent.getJumlahMons() != 0){
                                    System.out.println("");
                                    System.out.printf("%s's turn%n",playerOpponent.getPlayerName());
                                    playerOpponent.chooseMonster();
                                }
                                else {
                                    System.out.println(playerPlay.getPlayerName() + " MENANG HOREEE");
                                    Menu.exit();
                                    isPlay = false;
                                    play = false;
                                }
                            }
                            else{
                                System.out.println("Player "+playerOpponent.getPlayerName()+" move selanjutnya");
                                System.out.printf("%s melakukan %s%n",playerOpponent.getActiveMonster().getName(), playerOpponent.getActiveMonster().getMoves().get(indexOpp-1).getMoveName());
                                playerOpponent.getActiveMonster().getMoves().get(indexOpp-1).damage(playerOpponent.getActiveMonster(), playerPlay.getActiveMonster());
                                System.out.printf("HP %s sekarang : %s%n",playerPlay.getActiveMonster().getName(),playerPlay.getActiveMonster().getStats().getHealthPoint());
                                System.out.printf("HP %s sekarang : %s%n",playerOpponent.getActiveMonster().getName(),playerOpponent.getActiveMonster().getStats().getHealthPoint());
                                if (playerPlay.isMonsterDie()){
                                    System.out.printf("astaga %s sudah meninggal%n",playerPlay.getActiveMonster().getName());
                                    if (playerPlay.getJumlahMons() != 0){
                                        System.out.println("");
                                        System.out.printf("%s's turn%n",playerPlay.getPlayerName());
                                        playerPlay.chooseMonster();
                                    }
                                    else {
                                        System.out.println(playerOpponent.getPlayerName() + " MENANG HOREEE");
                                        Menu.exit();
                                        isPlay = false;
                                        play = false;
                                    }
                                }
                            }
                        } 
                        else if (playerPlay.getActiveMonster().getStats().getSpeed() < playerOpponent.getActiveMonster().getStats().getSpeed()) {
                            System.out.println("Player "+playerOpponent.getPlayerName()+" move duluan");
                            System.out.printf("%s melakukan %s%n",playerOpponent.getActiveMonster().getName(), playerOpponent.getActiveMonster().getMoves().get(indexOpp-1).getMoveName());
                            playerOpponent.getActiveMonster().getMoves().get(indexOpp-1).damage(playerOpponent.getActiveMonster(), playerPlay.getActiveMonster());
                            System.out.printf("HP %s sekarang : %s%n",playerPlay.getActiveMonster().getName(),playerPlay.getActiveMonster().getStats().getHealthPoint());
                            System.out.printf("HP %s sekarang : %s%n",playerOpponent.getActiveMonster().getName(),playerOpponent.getActiveMonster().getStats().getHealthPoint());
                            if (playerPlay.isMonsterDie()){
                                System.out.printf("astaga %s sudah meninggal%n",playerPlay.getActiveMonster().getName());
                                if (playerPlay.getJumlahMons() != 0){
                                    System.out.println("");
                                    System.out.printf("%s's turn%n",playerPlay.getPlayerName());
                                    playerPlay.chooseMonster();
                                }
                                else {
                                    System.out.println(playerOpponent.getPlayerName() + " MENANG HOREEE");
                                    Menu.exit();
                                    isPlay = false;
                                    play = false;
                                }
                            }
                            else{
                                System.out.println("Player "+playerPlay.getPlayerName()+" move selanjutnya");
                                System.out.printf("%s melakukan %s%n",playerPlay.getActiveMonster().getName(), playerPlay.getActiveMonster().getMoves().get(indexPlay-1).getMoveName());
                                playerPlay.getActiveMonster().getMoves().get(indexPlay-1).damage(playerPlay.getActiveMonster(), playerOpponent.getActiveMonster());
                                System.out.printf("HP %s sekarang : %s%n",playerPlay.getActiveMonster().getName(),playerPlay.getActiveMonster().getStats().getHealthPoint());
                                System.out.printf("HP %s sekarang : %s%n",playerOpponent.getActiveMonster().getName(),playerOpponent.getActiveMonster().getStats().getHealthPoint());
                                if (playerOpponent.isMonsterDie()){
                                    System.out.printf("astaga %s sudah meninggal%n",playerOpponent.getActiveMonster().getName());
                                    if (playerOpponent.getJumlahMons() != 0){
                                        System.out.println("");
                                        System.out.printf("%s's turn%n",playerOpponent.getPlayerName());
                                        playerOpponent.chooseMonster();
                                    }
                                    else {
                                        System.out.println(playerPlay.getPlayerName() + " MENANG HOREEE");
                                        Menu.exit();
                                        isPlay = false;
                                        play = false;
                                    }
                                }
                            }
                        }
                        else {
                            System.out.println("Speed pemain sama. Urutan dipilih random");
                            Random random = new Random();
                            int urutan = random.nextInt(2) + 1;
                            if (urutan == 1) {
                                System.out.println("Player "+playerPlay.getPlayerName()+" move duluan");
                                System.out.printf("%s melakukan %s%n",playerPlay.getActiveMonster().getName(), playerPlay.getActiveMonster().getMoves().get(indexPlay-1).getMoveName());
                                playerPlay.getActiveMonster().getMoves().get(indexPlay-1).damage(playerPlay.getActiveMonster(), playerOpponent.getActiveMonster());
                                System.out.printf("HP %s sekarang : %s%n",playerPlay.getActiveMonster().getName(),playerPlay.getActiveMonster().getStats().getHealthPoint());
                                System.out.printf("HP %s sekarang : %s%n",playerOpponent.getActiveMonster().getName(),playerOpponent.getActiveMonster().getStats().getHealthPoint());
                                if (playerOpponent.isMonsterDie()){
                                    System.out.printf("astaga %s sudah meninggal%n",playerOpponent.getActiveMonster().getName());
                                    if (playerOpponent.getJumlahMons() != 0){
                                        System.out.println("");
                                        System.out.printf("%s's turn%n",playerOpponent.getPlayerName());
                                        playerOpponent.chooseMonster();
                                    }
                                    else {
                                        System.out.println(playerPlay.getPlayerName() + " MENANG HOREEE");
                                        Menu.exit();
                                        isPlay = false;
                                        play = false;
                                    }
                                }
                                else{
                                    System.out.println("Player "+playerOpponent.getPlayerName()+" move selanjutnya");
                                    System.out.printf("%s melakukan %s%n",playerOpponent.getActiveMonster().getName(), playerOpponent.getActiveMonster().getMoves().get(indexOpp-1).getMoveName());
                                    playerOpponent.getActiveMonster().getMoves().get(indexOpp-1).damage(playerOpponent.getActiveMonster(), playerPlay.getActiveMonster());
                                    System.out.printf("HP %s sekarang : %s%n",playerPlay.getActiveMonster().getName(),playerPlay.getActiveMonster().getStats().getHealthPoint());
                                    System.out.printf("HP %s sekarang : %s%n",playerOpponent.getActiveMonster().getName(),playerOpponent.getActiveMonster().getStats().getHealthPoint());
                                    if (playerPlay.isMonsterDie()){
                                        System.out.printf("astaga %s sudah meninggal%n",playerPlay.getActiveMonster().getName());
                                        if (playerPlay.getJumlahMons() != 0){
                                            System.out.println("");
                                            System.out.printf("%s's turn%n",playerPlay.getPlayerName());
                                            playerPlay.chooseMonster();
                                        }
                                        else {
                                            System.out.println(playerOpponent.getPlayerName() + " MENANG HOREEE");
                                            Menu.exit();
                                            isPlay = false;
                                            play = false;
                                        }
                                    }
                                }
                            }
                            else {
                                System.out.println("Player "+playerOpponent.getPlayerName()+" move duluan");
                                System.out.printf("%s melakukan %s%n",playerOpponent.getActiveMonster().getName(), playerOpponent.getActiveMonster().getMoves().get(indexOpp-1).getMoveName());
                                playerOpponent.getActiveMonster().getMoves().get(indexOpp-1).damage(playerOpponent.getActiveMonster(), playerPlay.getActiveMonster());
                                System.out.printf("HP %s sekarang : %s%n",playerPlay.getActiveMonster().getName(),playerPlay.getActiveMonster().getStats().getHealthPoint());
                                System.out.printf("HP %s sekarang : %s%n",playerOpponent.getActiveMonster().getName(),playerOpponent.getActiveMonster().getStats().getHealthPoint());
                                if (playerPlay.isMonsterDie()){
                                    System.out.printf("astaga %s sudah meninggal%n",playerPlay.getActiveMonster().getName());
                                    if (playerPlay.getJumlahMons() != 0){
                                        System.out.println("");
                                        System.out.printf("%s's turn%n",playerPlay.getPlayerName());
                                        playerPlay.chooseMonster();
                                    }
                                    else {
                                        System.out.println(playerOpponent.getPlayerName() + " MENANG HOREEE");
                                        Menu.exit();
                                        isPlay = false;
                                        play = false;
                                    }
                                }
                                else{
                                    System.out.println("Player "+playerPlay.getPlayerName()+" move selanjutnya");
                                    System.out.printf("%s melakukan %s%n",playerPlay.getActiveMonster().getName(), playerPlay.getActiveMonster().getMoves().get(indexPlay-1).getMoveName());
                                    playerPlay.getActiveMonster().getMoves().get(indexPlay-1).damage(playerPlay.getActiveMonster(), playerOpponent.getActiveMonster());
                                    System.out.printf("HP %s sekarang : %s%n",playerPlay.getActiveMonster().getName(),playerPlay.getActiveMonster().getStats().getHealthPoint());
                                    System.out.printf("HP %s sekarang : %s%n",playerOpponent.getActiveMonster().getName(),playerOpponent.getActiveMonster().getStats().getHealthPoint());
                                    if (playerOpponent.isMonsterDie()){
                                        System.out.printf("astaga %s sudah meninggal%n%n",playerOpponent.getActiveMonster().getName());
                                        if (playerOpponent.getJumlahMons() != 0){
                                            System.out.println("");
                                            System.out.printf("%s's turn%n",playerOpponent.getPlayerName());
                                            playerOpponent.chooseMonster();
                                        }
                                        else {
                                            System.out.println(playerPlay.getPlayerName() + " MENANG HOREEE");
                                            Menu.exit();
                                            isPlay = false;
                                            play = false;
                                        }
                                    }
                                }
                            }
                        }
                    } 
                    
                    // cek diam atau tidak
                    if (playerPlay.getActiveMonster().getStatusCondition().getDiam() == true){
                        ctr = ctr + 1;
                    }
                    
                    idRound++;
                }
            } else if (command.equals("HELP")) {
                Menu.help();
            } else if (command.equals("EXIT")) {
                Menu.exit();
                System.exit(0);
                input.close();
                play = false;
            }
        }
    }
}

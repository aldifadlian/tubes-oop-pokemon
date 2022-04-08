package com.monstersaku;

import com.monstersaku.util.*;
import com.monstersaku.util.CSVReader;

import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ArrayList<Monster> listMonster = new ArrayList<Monster>();
        ArrayList<Move> listMove = new ArrayList<Move>();
        // baca csv
        try{
            // monster
            CSVReader bacaMonster = new CSVReader(new File(Main.class.getResource("C:/Users/Acer/Documents/NGODING SKUY/java/tubes-oop-pokemon/src/main/resources/com/monstersaku/configs/monsterpool.csv").toURI()), ";");
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

                System.out.println();
                Integer idMons = Integer.parseInt(line[0]);
                Monster buatMonster = new Monster(idMons, namaMons, elementTypes, baseStatsMons, arrMoveMons);
                listMonster.add(buatMonster);
            }
        } catch (Exception e){
            e.getMessage();
        }

        boolean play = true;
        Scanner input = new Scanner(System.in);
        System.out.println("============ WELCOME TO POKEMON =================");
        System.out.println("");

        while(play) {
            System.out.printf("Select : ");
            String command = input.next();
            if (command.equals("START")) {
                Scanner scanPlayer = new Scanner(System.in);
                System.out.println("Masukkan nama pemain 1 : ");
                String namaPemain1 = scanPlayer.nextLine();
                System.out.println("Masukkan nama pemain 2 : ");
                String namaPemain2 = scanPlayer.nextLine();
                System.out.printf("%nPemain 1 : %s", namaPemain1);
                System.out.printf("%nPemain 2 : %s%n", namaPemain2);
                System.out.printf("%s",listMonster.size());
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
                    listMonsPemain1.add(monster2);
                }

                Player pemain1 = new Player(namaPemain1, listMonsPemain1);
                Player pemain2 = new Player(namaPemain2, listMonsPemain2);

                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                System.out.printf("%s's Monsters%n", pemain1);
                System.out.printf("Nama monster :%n");
                int i = 0;
                for(Monster monster : listMonster){
                    i++;
                    System.out.printf("%s. %s \tHP: %s%n", i,  monster.getName(), monster.getStats().getHealthPoint());
                }
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                System.out.printf("%s's Monsters%n", pemain2);
                System.out.printf("Nama monster :%n");
                int j = 0;
                for(Monster monster : listMonster){
                    j++;
                    System.out.printf("%s. %s \tHP: %s%n", j,  monster.getName(), monster.getStats().getHealthPoint());
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

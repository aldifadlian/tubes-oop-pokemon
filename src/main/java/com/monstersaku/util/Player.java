package com.monstersaku.util;

import java.util.*;

public class Player {
    private String name;
    private int jumlahMons;
    private Monster activeMonster;
    private List<Monster> monsters = new ArrayList<Monster>(6);
    private List<Monster> passiveMonsters = new ArrayList<Monster>();

    public Player(String name, List<Monster> monsters) {
        this.name = name;
        this.jumlahMons = 6;
        this.monsters = monsters;
    }

    //getter
    public String getPlayerName(){
        return this.name;
    }
    public List<Monster> getPlayerMonster(){
        return this.monsters;
    }
    public int getJumlahMons(){
        return this.jumlahMons;
    }
    public Monster getActiveMonster() {
        return this.activeMonster;
    }
    public List<Monster> getPassiveMonster() {
        return this.passiveMonsters;
    }


    //setter
    public void setPlayerName(String name){
        this.name = name;
    }
    public void setPlayerMonster(List<Monster> monsters){
        this.monsters = monsters;
    }
    public void setJumlahMons(){
        this.jumlahMons = jumlahMons;
    }
    public void setActiveMonster(Monster activeMonster){
        this.activeMonster = activeMonster;
    }
    public void setPassiveMonster(List<Monster> passiveMonsters){
        this.passiveMonsters = passiveMonsters;
    }

    public void printNamaMonster(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.printf("Daftar Monster %s%n", getPlayerName());
        int i = 0;
        for(Monster monster : monsters){
            i++;
            System.out.printf("%s. %s%n", i,  monster.getName());
        }
    }

    public void printNamaPassiveMonster(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.printf("%s's Monsters%n", getPlayerName());
        System.out.printf("Nama monster :%n");
        int i = 0;
        for(Monster monster : passiveMonsters){
            i++;
            System.out.printf("%s. %s \tHP: %s%n", i,  monster.getName(), monster.getStats().getHealthPoint());
        }
    }

    public static int move() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select move: ");
        int input = scanner.nextInt();
        return input;
    }
    
    public void chooseMonster(){
        this.passiveMonsters.addAll(monsters);
        System.out.println("CHOOSE YOUR MONSTER");
        printNamaMonster();
        Scanner scanChoose = new Scanner(System.in);
        System.out.printf("SELECT MONSTER TO FIGHT: ");
        int inputChoose = scanChoose.nextInt();
        boolean isInputValid = true;
        while (isInputValid) {
            if (inputChoose >= 1 && inputChoose <= this.passiveMonsters.size()){
                activeMonster = this.monsters.get(inputChoose-1);
                this.passiveMonsters.remove(inputChoose-1);
                System.out.println("Kamu memilih "+ activeMonster.getName());
                isInputValid = false;
            }
            else {
                System.out.println("Input salah, silakan ulangi untuk monster yang tersedia!");
                isInputValid = false;
            }
        }
    }

    public void switchMonster() {
        System.out.println("SWITCH MONSTER");
        printNamaPassiveMonster();
        boolean isInputValid = true;
        while (isInputValid) {
            if (this.passiveMonsters.size() > 0) {
                Scanner scanSwitch = new Scanner(System.in);
                System.out.printf("SELECT MONSTER TO SWITCH: ");
                int inputSwitch = scanSwitch.nextInt();
                if (inputSwitch >= 1 && inputSwitch <= this.passiveMonsters.size()){
                    this.passiveMonsters.add(activeMonster);
                    activeMonster = this.passiveMonsters.get(inputSwitch-1);
                    this.passiveMonsters.remove(inputSwitch-1);
                    System.out.println("Kamu memilih "+ activeMonster.getName());
                    printNamaPassiveMonster();
                    isInputValid = false;
                }
                else {
                    System.out.println("Input salah, silakan ulangi untuk monster yang tersedia!");
                }
            }
            else {
                System.out.println("Tidak bisa melakukan switch karena monster habis");
                isInputValid = false;
            }

        }
    }

    public void viewMonsters(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.printf("Daftar Monster %s%n", getPlayerName());
        int i = 0;
        for(Monster monster : monsters){
            i++;
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.printf("Monster %s%n", i);
            System.out.println("");
            System.out.printf("Nama Monster \t: %s%n", monster.getName());
            List<ElementType> elementTypes = monster.getElemenTypes();
            for (ElementType elementType : elementTypes){
                if(elementType.equals(ElementType.NORMAL)){
                    System.out.printf("Element Type \t: %s%n", ElementType.NORMAL);
                }
                else if(elementType.equals(ElementType.FIRE)){
                    System.out.printf("Element Type \t: %s%n", ElementType.FIRE);
                }
                else if(elementType.equals(ElementType.WATER)){
                    System.out.printf("Element Type \t: %s%n", ElementType.WATER);
                }   
                else if(elementType.equals(ElementType.GRASS)){
                    System.out.printf("Element Type \t: %s%n", ElementType.GRASS);
                } 
            }
            monster.getStats().printBaseStats();
        }
    }
}

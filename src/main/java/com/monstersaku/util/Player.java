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
        this.passiveMonsters.addAll(monsters);
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

    public void printInfoMonster(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.printf("Daftar Monster Player %s%n", getPlayerName());
        int i = 0;
        for(Monster monster : monsters){
            i++;
            System.out.printf("%s. %s \tHP: %s%n", i,  monster.getName(), monster.getStats().getHealthPoint());
        }
    }

    public void printInfoActiveMonster(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        if (activeMonster != null) {
            System.out.println("Monster yang sedang digunakan oleh Player " + getPlayerName());
            System.out.println(activeMonster.getName() + ". HP: " + activeMonster.getStats().getHealthPoint());
        }
        else {
            System.out.println("Tidak ada monster aktif yang digunakan oleh Player " + getPlayerName());
        }
    }

    public void printInfoPassiveMonster(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.printf("Daftar Monster yang tidak digunakan oleh Player %s%n", getPlayerName());
        int i = 0;
        for(Monster monster : passiveMonsters){
            i++;
            System.out.printf("%s. %s \tHP: %s%n", i,  monster.getName(), monster.getStats().getHealthPoint());
        }
    }

    public int move() {
        Scanner scanner = new Scanner(System.in);
        this.activeMonster.printInfoMove();
        boolean isValid = true;
        int inputMove = 0;
        while (isValid) {
            try {
                Scanner scanMove = new Scanner(System.in);
                System.out.printf("Select move : ");
                inputMove = scanMove.nextInt();
                if (inputMove >= 1 && inputMove <= this.activeMonster.getMoves().size()) {
                    System.out.printf("%s melakukan %s%n",activeMonster.getName(), activeMonster.getMoves().get(inputMove-1).getMoveName());
                    isValid = false;
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Input salah. Harap ulangi dengan pilihan yang tersedia!");
            } catch (Exception e){
                System.out.println("Input salah. Harap ulangi dengan tipe masukan yang sesuai!");
            }
        }
        return inputMove;
    }

     public boolean isMonsterDie() {
        return (activeMonster.getStats().getHealthPoint() == 0);
    }

     public void afterDie() {
        int i = 0;
        int x = 0;
        for(Monster monster : monsters){
            i++;
            if (monster.getStats().getHealthPoint() == 0){
                x = i;
            }
        }
        this.monsters.remove(x-1);
    }

    public void initialMonster(){
        activeMonster = this.monsters.get(0);
        System.out.println("Monster yang terpilih untuk bertarung adalah "+ activeMonster.getName());
        this.passiveMonsters.remove(0);
    }

    public void chooseMonster(){
        System.out.println("CHOOSE YOUR MONSTER");
        afterDie();
        printInfoMonster();
        Scanner scanChoose = new Scanner(System.in);
        boolean isValid = true;
        while (isValid) {
            try {
                System.out.printf("SELECT MONSTER TO FIGHT: ");
                int inputChoose = scanChoose.nextInt();
                if (inputChoose >= 1 && inputChoose <= this.passiveMonsters.size()){
                    activeMonster = this.monsters.get(inputChoose-1);
                    this.passiveMonsters.remove(inputChoose-1);
                    System.out.println("Kamu memilih "+ activeMonster.getName());
                    isValid = false;
                }
                else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Input salah. Harap ulangi dengan pilihan yang tersedia!");
            } catch (Exception e){
                System.out.println("Input salah. Harap ulangi dengan tipe masukan yang sesuai!");
            }
        }
    }

    public void switchMonster() {
        System.out.println("SWITCH MONSTER");
        printInfoPassiveMonster();
        boolean isInputValid = true;
        while (isInputValid) {
            if (this.passiveMonsters.size() > 0) {
                try {
                    Scanner scanSwitch = new Scanner(System.in);
                    System.out.printf("SELECT MONSTER TO SWITCH: ");
                    int inputSwitch = scanSwitch.nextInt();
                    if (inputSwitch >= 1 && inputSwitch <= this.passiveMonsters.size()){
                        this.passiveMonsters.add(activeMonster);
                        activeMonster = this.passiveMonsters.get(inputSwitch-1);
                        this.passiveMonsters.remove(inputSwitch-1);
                        System.out.println("Kamu memilih "+ activeMonster.getName());
                        isInputValid = false;
                    }
                    else {
                        throw new IllegalArgumentException();
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Input salah. Harap ulangi dengan pilihan yang tersedia!");
                }catch (Exception e){
                    System.out.println("Input salah. Harap ulangi dengan tipe masukan yang sesuai!");
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

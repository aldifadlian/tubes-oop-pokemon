package com.monstersaku.util;

import java.util.*;

public class Player {
    private String name;
    private int jumlahMons;
    private List<Monster> monsters = new ArrayList<Monster>(6);

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

    public void printNamaMonster(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.printf("Daftar Monster %s%n", getPlayerName());
        int i = 0;
        for(Monster monster : monsters){
            i++;
            System.out.printf("%s. %s%n", i,  monster.getName());
        }
    }

    public static int move() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select move: ");
        int input = scanner.nextInt();
        return input;
    }
    
    public static void switchMonster() {
        System.out.println("otw nanti");
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

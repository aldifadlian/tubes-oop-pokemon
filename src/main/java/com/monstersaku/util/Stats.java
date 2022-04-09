package com.monstersaku.util;

public class Stats {
	private double healthPoint;
	private double attack;
	private double defense;
	private double specialAttack;
	private double specialDefense;
	private double speed;
	private double maxHP;
	
	// Construktor
	public Stats(double hP, double att, double def, double spAtt, double spDeff, double spd) {
		healthPoint = hP;
		attack = att;
		defense = def;
		specialAttack = spAtt;
		specialDefense = spDeff;
		speed = spd;
		maxHP = hP;
	}

	// getter & setter healthPoint
	public void setHealthPoint(double healthPoint) {
		this.healthPoint = healthPoint;
	}
	public double getHealthPoint() {
		return healthPoint;
	}

	// getter & setter attack
	public void setAttack(double attack) {
		this.attack = attack;
	}
	public double getAttack() {
		return attack;
	}

	// getter & setter defense
	public void setDefense(double defense) {
		this.defense = defense;
	}
	public double getDefense() {
		return defense;
	}

	// getter & setter specialAttack
	public void setSpecialAttack(double specialAttack) {
		this.specialAttack = specialAttack;
	}
	public double getSpecialAttack() {
		return specialAttack;
	}

	// getter & setter specialDesefense
	public void setSpecialDefense(double specialDefense) {
		this.specialDefense = specialDefense;
	}
	public double getSpecialDefense() {
		return specialDefense;
	}

	// getter & setter speed
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getSpeed() {
		return speed;
	}

	// getter & setter maxHP
	public double getMaxHP() {
		return maxHP;
	}
}
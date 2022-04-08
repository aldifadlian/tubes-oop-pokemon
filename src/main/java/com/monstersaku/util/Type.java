package com.monstersaku.util;

public class Type {
    private ElementType attack1;
    private ElementType attack2;
    private ElementType defense;

    // konstruktor
    // opsional jika pokemon memiliki lebih dari satu tipe element
    public Type (ElementType attack1, ElementType attack2, ElementType defense) {
        this.attack1 = attack1;
        this.attack2 = attack2;
        this.defense = defense;
    }

    // jika pokemon memiliki hanya satu elemen
    public Type (ElementType attack1, ElementType defense) {
        this(attack1, null, defense);
    }

    public ElementType getElementType(ElementType et) {
        return et;
    }
    
    public String toString() {
        return "Element Type [attack=" + attack1 + ", attack2=" + attack2 + ", defense=" + defense + "]";
    }
}

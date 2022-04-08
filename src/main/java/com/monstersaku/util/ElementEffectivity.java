package com.monstersaku.util;

public class ElementEffectivity {
    ElementType source;
    ElementType target;

    public ElementEffectivity(ElementType source, ElementType target){
        this.source = source;
        this.target = target;
    }

    public ElementType getSource() { 
        return this.source; 
    }
    public ElementType getTarget() { 
        return this.target; 
    }
}
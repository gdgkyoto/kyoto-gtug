package com.appspot.eitan.model.beans;

public class WordDispInfo {
    private int count;
    private int publicCount;
    private String spell;
    
    public WordDispInfo(int count, int publicCount, String spell) {
        this.count = count;
        this.publicCount = publicCount;
        this.spell = spell;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int value) {
        count = value;
    }
    
    public int getPublicCount() {
        return publicCount;
    }
    
    public void setPublicCount(int value) {
        publicCount = value;
    }
    
    public String getSpell() {
        return spell;
    }
    
    public void setSpell(String value) {
        spell = value;
    }
}

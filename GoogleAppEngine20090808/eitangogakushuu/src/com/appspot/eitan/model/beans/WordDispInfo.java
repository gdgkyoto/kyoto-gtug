package com.appspot.eitan.model.beans;

public class WordDispInfo {
    private int count;
    private int publicCount;
    private String spell;
    private int status;
    
    public WordDispInfo(int count, int publicCount, String spell, int status) {
        this.count = count;
        this.publicCount = publicCount;
        this.spell = spell;
        this.status = status;
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
    
    public String getStatus() {
        if (status == 3)
            return "Åõ";
        if (status == 2) 
            return "Å¢";
        if (status == 1) 
            return "Å~";
        return "-"; 
    } 
}

package de.darkyiu.crops_and_magic.spells;

public enum Buff {

    MORE_DAMAGE("Magic Power", 30, 2);

    private String name;
    private int baseDuration;
    private int level;

    Buff(String name, int baseDuration, int level){
        this.name = name;
        this.baseDuration = baseDuration;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getBaseDuration() {
        return baseDuration;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

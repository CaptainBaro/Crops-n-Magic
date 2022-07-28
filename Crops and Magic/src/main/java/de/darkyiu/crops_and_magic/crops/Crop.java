package de.darkyiu.crops_and_magic.crops;

public enum Crop {

    STRAWBERRY("§fStrawberry",null, 345, "Crop.Eat.Strawberry", "Crop.Farm.Strawberry.1","Crop.Farm.Strawberry.2", "Crop.Farm.Strawberry.3" , 346,347,348);

    private String name;
    private String lore;
    private int modelEat;
    private String localized_Eat;
    private String localized_Farm_1;
    private String localized_Farm_2;
    private String localized_Farm_3;
    private int model_Farm_1;
    private int model_Farm_2;
    private int model_Farm_3;

    Crop(String name, String lore, int modelEat, String localized_Eat, String localized_Farm_1, String localized_Farm_2, String localized_Farm_3, int model_Farm_1, int model_Farm_2, int model_Farm_3){
        this.name = name;
        this.lore = lore;
        this.modelEat = modelEat;
        this.localized_Eat = localized_Eat;
        this.localized_Farm_1 = localized_Farm_1;
        this.localized_Farm_2 = localized_Farm_2;
        this.localized_Farm_3 = localized_Farm_3;
        this.model_Farm_1 = model_Farm_1;
        this.model_Farm_2 = model_Farm_2;
        this.model_Farm_3 = model_Farm_3;
    }

    public String getName() {
        return name;
    }

    public String getLore() {
        return lore;
    }

    public int getModelEat() {
        return modelEat;
    }

    public String getLocalized_Eat() {
        return localized_Eat;
    }

    public String getLocalized_Farm_1() {
        return localized_Farm_1;
    }

    public String getLocalized_Farm_2() {
        return localized_Farm_2;
    }

    public String getLocalized_Farm_3() {
        return localized_Farm_3;
    }

    public int getModel_Farm_1() {
        return model_Farm_1;
    }

    public int getModel_Farm_2() {
        return model_Farm_2;
    }

    public int getModel_Farm_3() {
        return model_Farm_3;
    }
}

package de.darkyiu.crops_and_magic.crops;

public enum FarmingCrops {

    STRAWBERRY_1("Strawberry", "Crop.Farm.Strawberry.1", 345, Crop.STRAWBERRY),
    STRAWBERRY_2("Strawberry", "Crop.Farm.Strawberry.2", 346, Crop.STRAWBERRY),
    STRAWBERRY_3("Strawberry", "Crop.Farm.Strawberry.3", 347, Crop.STRAWBERRY);

    private String name;
    private String localizedName;
    private int customModelData;
    private Crop crop;

    FarmingCrops(String name, String localizedName, int customModelData, Crop crop){
        this.name = name;
        this.localizedName = localizedName;
        this.customModelData = customModelData;
        this.crop = crop;
    }

    public String getName() {
        return name;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public Crop getCrop() {
        return crop;
    }
}

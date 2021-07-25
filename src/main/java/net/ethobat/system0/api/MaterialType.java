package net.ethobat.system0.api;

public enum MaterialType {

    INDIGONITE("indigonite"),
    OVULUM("ovulum"),
    KALKIUM("kalkium"),
    SYMVINYL("symvinyl");

    private final String type;

    MaterialType(String type) {
        this.type = type;
    }

    public String toString() {
        return this.type;
    }

}

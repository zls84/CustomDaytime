package xyz.mayahive.customdaytime.api.model;

public enum PlatformType {
    PAPER("paper"),
    SPONGE("sponge");

    private final String displayName;

    PlatformType(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}

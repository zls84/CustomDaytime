package xyz.mayahive.customdaytime.api.model;

public record WorldKey(String namespace, String value) {

    public static WorldKey fromString(String key) {
        String[] split = key.split(":", 2);

        if (split.length != 2) {
            throw new IllegalArgumentException("Invalid world key format: " + key);
        }

        return new WorldKey(split[0], split[1]);
    }

    public String asString() {
        return namespace + ":" + value;
    }
}

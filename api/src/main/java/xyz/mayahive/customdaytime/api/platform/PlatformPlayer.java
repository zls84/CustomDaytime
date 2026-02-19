package xyz.mayahive.customdaytime.api.platform;

/**
 * Represents a player in a platform-agnostic way.
 * Provides methods to interact with the player across different loaders/platforms.
 */
public interface PlatformPlayer {

    /**
     * Sends a message to the player's action bar.
     *
     * @param message the message to display
     */
    void sendActionBar(String message);

}

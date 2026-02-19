package xyz.mayahive.customdaytime.api.platform;

import xyz.mayahive.customdaytime.api.model.WorldKey;

import java.util.List;
import java.util.Optional;

/**
 * Represents a world in a platform-agnostic way.
 * Provides access to world properties such as players, and game rules across different loaders.
 */
public interface PlatformWorld {

    /**
     * Returns the world key such as "minecraft:overworld".
     *
     * @return the world's key
     */
    WorldKey getKey();

    /**
     * Returns a human-readable key for this world, e.g., "minecraft:overworld".
     *
     * @return the world key as a string
     */
    String getKeyString();

    /**
     * Returns the current time of the world if available.
     *
     * @return an Optional containing the world time, or empty if unavailable
     */
    Optional<Long> getTime();

    /**
     * Sets the current time of the world.
     *
     * @param time the new time value
     * @return true if the time was successfully set, false otherwise
     */
    boolean setTime(long time);

    /**
     * Returns a list of players currently in this world.
     *
     * @return list of PlatformPlayer instances
     */
    List<PlatformPlayer> getPlayers();

    /**
     * Returns number of sleeping players in this world.
     *
     * @return number of sleeping players
     */
    int getSleepingPlayerCount();

    /**
     * Returns whether the "doDaylightCycle" or equivalent game rule is enabled.
     *
     * @return true if time advances automatically, false otherwise
     */
    boolean getGameRuleAdvanceTime();

    /**
     * Returns the required percentage of players sleeping to skip the night,
     * if the platform supports this game rule.
     *
     * @return a value between 0.0 and 100.0
     */
    int getGameRulePlayerSleepingPercentage();
}

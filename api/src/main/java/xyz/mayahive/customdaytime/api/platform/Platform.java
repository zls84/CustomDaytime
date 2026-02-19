package xyz.mayahive.customdaytime.api.platform;

import xyz.mayahive.customdaytime.api.model.WorldKey;

import java.nio.file.Path;
import java.util.List;

/**
 * Represents the current server platform.
 */
public interface Platform {

    /**
     * Returns the name of the platform/loader (e.g., "Paper", "Folia", "Fabric").
     *
     * @return the platform name
     */
    String getPlatformName();

    /**
     * Returns the version of the platform/loader.
     *
     * @return the platform version
     */
    String getPlatformVersion();

    /**
     * Returns the version of Minecraft.
     *
     * @return Minecraft version
     */
    String getMinecraftVersion();

    /**
     * Returns the name of the project (e.g., "CustomDaytime").
     *
     * @return the project name
     */
    String getProjectName();

    /**
     * Returns the version of the project.
     *
     * @return the project version
     */
    String getProjectVersion();


    /**
     * Returns the path for project's config folder.
     *
     * @return path for config folder
     */
    Path getConfigFolder();

    /**
     * Returns the logger for outputting messages.
     *
     * @return the logger
     */
    PlatformLogger getLogger();

    /**
     * Returns the scheduler for running tasks.
     *
     * @return the scheduler
     */
    PlatformScheduler getScheduler();

    /**
     * Returns the {@link PlatformWorld} corresponding to the given WorldKey.
     *
     * @param key the world key
     * @return the PlatformWorld, or null if not found
     */
    PlatformWorld getWorld(WorldKey key);


    /**
     * Returns list of all loaded {@link PlatformWorld}.
     *
     * @return list of loaded worlds
     */
    List<PlatformWorld> getWorlds();

    /**
     * Returns whether project is in debug mode.
     * Debug mode adds some logging to ease debug during development.
     *
     * @return true if debug mode is on, false otherwise.
     */
    boolean debug();
}
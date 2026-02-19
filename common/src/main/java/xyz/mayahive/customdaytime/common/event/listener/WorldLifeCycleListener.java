package xyz.mayahive.customdaytime.common.event.listener;

import lombok.RequiredArgsConstructor;
import xyz.mayahive.customdaytime.api.model.WorldKey;
import xyz.mayahive.customdaytime.api.platform.Platform;
import xyz.mayahive.customdaytime.api.platform.PlatformWorld;
import xyz.mayahive.customdaytime.common.context.CustomDaytimeContext;
import xyz.mayahive.customdaytime.common.event.type.WorldLoadEvent;
import xyz.mayahive.customdaytime.common.event.type.WorldUnloadEvent;

@RequiredArgsConstructor
public class WorldLifeCycleListener {

    private final CustomDaytimeContext context;

    public void onWorldLoad(WorldLoadEvent event) {
        Platform platform = context.getPlatform();
        PlatformWorld world = event.world();
        WorldKey key = world.getKey();

        if (!context.getConfigService().getRootKeys().contains(key.asString())) {
            if (platform.debug()) platform.getLogger().debug("WorldLoadEvent: World '" + key.asString() + "' is not defined in config, skipping.");
            return;
        }

        context.getWorldCache().registerWorld(world);

        context.getWorldTimeManager().start(key);

        if (platform.debug()) platform.getLogger().debug("Registered WorldLoadEvent for world: " + key.asString());
    }

    public void onWorldUnload(WorldUnloadEvent event) {
        Platform platform = context.getPlatform();
        PlatformWorld world = event.world();
        WorldKey key = world.getKey();

        if (!context.getConfigService().getRootKeys().contains(key.asString())) {
            if (platform.debug()) platform.getLogger().debug("WorldUnloadEvent: World '" + key.asString() + "' is not defined in config, skipping.");
            return;
        }

        context.getWorldCache().unregisterWorld(world);

        context.getWorldTimeManager().stop(key);

        if (platform.debug()) platform.getLogger().debug("Registered WorldUnloadEvent for world: " + key.asString());
    }

}

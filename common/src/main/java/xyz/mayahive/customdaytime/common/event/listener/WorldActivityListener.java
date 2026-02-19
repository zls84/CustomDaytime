package xyz.mayahive.customdaytime.common.event.listener;

import lombok.RequiredArgsConstructor;
import xyz.mayahive.customdaytime.api.platform.PlatformPlayer;
import xyz.mayahive.customdaytime.api.platform.PlatformWorld;
import xyz.mayahive.customdaytime.api.model.WorldKey;
import xyz.mayahive.customdaytime.api.platform.Platform;
import xyz.mayahive.customdaytime.common.context.CustomDaytimeContext;
import xyz.mayahive.customdaytime.common.event.type.TimeAccelerationEvent;
import xyz.mayahive.customdaytime.common.event.type.WorldPlayerCountChangeEvent;
import xyz.mayahive.customdaytime.common.event.type.WorldSleepingPlayerCountChangeEvent;

@RequiredArgsConstructor
public class WorldActivityListener {

    private final CustomDaytimeContext context;

    public void onWorldPlayerCountChangeEvent(WorldPlayerCountChangeEvent event) {
        Platform platform = context.getPlatform();
        PlatformWorld world = event.world();
        WorldKey key = world.getKey();
        int totalPlayers = world.getPlayers().size();

        context.getPlatform().getScheduler().runLater(
                () -> context.getWorldTimeManager().setTotalPlayers(key, totalPlayers),
                1
        );

        if (platform.debug()) platform.getLogger().debug("Registered WorldPlayerCountChangeEvent. Updated total players count to " + totalPlayers);
    }

    public void onWorldSleepingPlayerCountChange(WorldSleepingPlayerCountChangeEvent event) {
        Platform platform = context.getPlatform();
        PlatformWorld world = event.world();
        WorldKey key = world.getKey();


        context.getPlatform().getScheduler().runLater(
                () -> {
                    int sleepingPlayers = world.getSleepingPlayerCount();
                    context.getWorldTimeManager().setSleepingPlayers(key, sleepingPlayers);
                    if (platform.debug()) platform.getLogger().debug("Registered WorldSleepingPlayerCountChangeEvent. Updated sleeping players count to " + sleepingPlayers);
                },
                1
        );


    }

    public void onTimeAccelerationEvent(TimeAccelerationEvent event) {
        Platform platform = context.getPlatform();
        PlatformWorld world = event.world();

        if (event.accelerating()) {
            for (PlatformPlayer player : world.getPlayers()) {
                player.sendActionBar("Night races toward dawn..."); //TODO: Implementation for LocalizationService here!
            }
        }

        if (platform.debug()) platform.getLogger().debug("Registered TimeAccelerationEvent. Time accelerating: " + event.accelerating());
    }

}

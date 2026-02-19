package xyz.mayahive.customdaytime.common.service;

import lombok.RequiredArgsConstructor;
import xyz.mayahive.customdaytime.api.platform.PlatformWorld;
import xyz.mayahive.customdaytime.common.context.CustomDaytimeContext;
import xyz.mayahive.customdaytime.common.event.type.WorldLoadEvent;
import xyz.mayahive.customdaytime.common.registry.CommonEventRegistry;

@RequiredArgsConstructor
public class WorldInitializationService {

    private final CustomDaytimeContext context;

    public void syncExistingWorlds() {
        for (PlatformWorld world : context.platform().getWorlds()) {
            context.eventBus().fire(new WorldLoadEvent(world));
        }
    }

}

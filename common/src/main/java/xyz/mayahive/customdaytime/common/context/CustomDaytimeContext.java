package xyz.mayahive.customdaytime.common.context;

import lombok.Getter;
import xyz.mayahive.customdaytime.api.platform.Platform;
import xyz.mayahive.customdaytime.common.cache.WorldCache;
import xyz.mayahive.customdaytime.common.event.EventBus;
import xyz.mayahive.customdaytime.common.service.ConfigService;
import xyz.mayahive.customdaytime.common.world.WorldTimeManager;

public class CustomDaytimeContext {

    @Getter
    private final Platform platform;

    @Getter
    private final WorldCache worldCache;

    @Getter
    private final ConfigService configService;

    @Getter
    private final WorldTimeManager worldTimeManager;

    @Getter
    private final EventBus eventBus;

    public CustomDaytimeContext(Platform platform) {
        this.platform = platform;
        this.worldCache = new WorldCache();
        this.configService = new ConfigService(platform);
        this.worldTimeManager = new WorldTimeManager(this);
        this.eventBus = new EventBus();
    }
}

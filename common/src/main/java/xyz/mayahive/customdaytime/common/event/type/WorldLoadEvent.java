package xyz.mayahive.customdaytime.common.event.type;

import lombok.NonNull;
import xyz.mayahive.customdaytime.api.platform.PlatformWorld;
import xyz.mayahive.customdaytime.common.event.Event;

/**
 * Fired when world  is loaded.
 *
 * @param world the loaded world
 */
public record WorldLoadEvent(@NonNull PlatformWorld world) implements Event { }

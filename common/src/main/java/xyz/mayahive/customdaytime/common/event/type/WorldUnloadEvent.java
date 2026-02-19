package xyz.mayahive.customdaytime.common.event.type;

import lombok.NonNull;
import xyz.mayahive.customdaytime.api.platform.PlatformWorld;
import xyz.mayahive.customdaytime.common.event.Event;

/**
 * Fired when world is unloaded.
 * @param world the unloaded world
 */
public record WorldUnloadEvent(@NonNull PlatformWorld world) implements Event { }

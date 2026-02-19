package xyz.mayahive.customdaytime.common.event.type;

import lombok.NonNull;
import xyz.mayahive.customdaytime.api.platform.PlatformWorld;
import xyz.mayahive.customdaytime.common.event.Event;

/**
 * Fired when time is skipped in Minecraft.
 *
 * @param world the world of the event
 * @param timeSkipCause cause for time skip
 */
public record TimeSkipEvent(@NonNull PlatformWorld world, @NonNull TimeSkipCause timeSkipCause) implements Event { }

package xyz.mayahive.customdaytime.common.event.type;

import lombok.NonNull;
import xyz.mayahive.customdaytime.api.platform.PlatformWorld;
import xyz.mayahive.customdaytime.common.event.Event;

public record WorldPlayerCountChangeEvent(@NonNull PlatformWorld world) implements Event {
}

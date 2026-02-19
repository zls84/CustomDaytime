package xyz.mayahive.customdaytime.common.event.type;

import xyz.mayahive.customdaytime.api.platform.PlatformWorld;
import xyz.mayahive.customdaytime.common.event.Event;

public record TimeAccelerationEvent(PlatformWorld world, boolean accelerating) implements Event { }

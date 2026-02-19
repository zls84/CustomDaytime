package xyz.mayahive.customdaytime.common.world;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import xyz.mayahive.customdaytime.api.platform.PlatformTask;
import xyz.mayahive.customdaytime.api.platform.PlatformWorld;
import xyz.mayahive.customdaytime.api.model.WorldKey;
import xyz.mayahive.customdaytime.common.context.CustomDaytimeContext;
import xyz.mayahive.customdaytime.common.event.type.TimeAccelerationEvent;
import xyz.mayahive.customdaytime.common.service.ConfigService;
import xyz.mayahive.customdaytime.common.service.DebugService;

@RequiredArgsConstructor
public class WorldTimeController {

    private final CustomDaytimeContext context;
    private final WorldKey key;
    private PlatformTask task;
    private PlatformWorld world;

    private double dayIncrement;
    private double nightIncrement;
    private double accelerationMultiplier;

    @Getter
    @Setter
    private int totalPlayers = 0;

    @Getter
    @Setter
    private int sleepingPlayers = 0;

    private double carry = 0.0;
    private boolean accelerating =  false;
    private long lastCycleTime = -1;

    public void start() {
        world = context.getWorldCache().getWorld(key);
        if (world != null) {
            totalPlayers = world.getPlayers().size();
            sleepingPlayers = world.getSleepingPlayerCount();
            DebugService.log(context, "Starting World Time Controller for world: " + key.asString());
        } else {
            DebugService.log(context, "World not loaded yet: " + key.asString());
        }

        reloadConfig();
        task = context.getPlatform().getScheduler().runRepeating(this::tick, 1);
    }

    public void stop() {
        if (task != null) {
            task.cancel();
            task = null;
            DebugService.log(context, "Stopping WorldTimeController for world: " + key.asString());
        }
    }

    private void reloadConfig() {
        ConfigService configService = context.getConfigService();

        double dayMinutes = configService.getConfigValue(Double.class, 10.0, key.asString(), "dayLength");
        double nightMinutes = configService.getConfigValue(Double.class, 10.0, key.asString(), "nightLength");
        accelerationMultiplier = configService.getConfigValue(Double.class, 100.0, key.asString(), "AccelerationMultiplier");

        dayIncrement = calculateIncrement(true, dayMinutes, nightMinutes);
        nightIncrement = calculateIncrement(false, dayMinutes, nightMinutes);

        DebugService.log(context, "Reloaded config for world " + key.asString() + " | dayIncrement=" + dayIncrement + " nightIncrement=" + nightIncrement + " accelerationMultiplier=" + accelerationMultiplier);
    }

    private void tick() {
        if (world == null) {
            DebugService.log(context, "World is null, stopping controller: " + key.asString());
            stop();
            return;
        }

        world.getTime().ifPresent(currentTime -> updateWorld(world, currentTime));
    }

    private void updateWorld(PlatformWorld world, long currentTime) {
        boolean  isDay = currentTime < 12000;

        if (lastCycleTime != -1) {
            boolean lastWasDay = lastCycleTime < 12000;
            if (lastWasDay != isDay) {
                DebugService.log(context, "Day/Night cycle change detected for world " + world.getKeyString() + " (lastCycleTime=" + lastCycleTime + ", currentTime=" + currentTime + ")");
                reloadConfig();
                carry = 0;
            }
        }

        lastCycleTime = currentTime;

        double increment = isDay ? dayIncrement : nightIncrement;
        boolean nowAccelerating = shouldAccelerate(world, isDay);

        if (nowAccelerating) {
            increment *= accelerationMultiplier;
        }

        carry += increment;

        long wholeTicks = (long) carry;
        if (wholeTicks > 0) {
            world.setTime(currentTime + wholeTicks);
            carry -= wholeTicks;
        }

        handleAccelerationEvent(world, nowAccelerating);
    }

    private boolean shouldAccelerate(PlatformWorld world, boolean isDay) {

        if (isDay) {return false;}

        if (totalPlayers == 0) return false;

        double percentage = ((double) sleepingPlayers / totalPlayers) * 100;
        int required = world.getGameRulePlayerSleepingPercentage();

        return percentage >= required;
    }

    private void handleAccelerationEvent(PlatformWorld world, boolean nowAccelerating) {
        if (nowAccelerating && !accelerating) {
            context.getEventBus().fire(new TimeAccelerationEvent(world, true));
            DebugService.log(context, "Time acceleration started for world " + world.getKeyString());
        } else if (!nowAccelerating && accelerating) {
            context.getEventBus().fire(new TimeAccelerationEvent(world, false));
            DebugService.log(context, "Time acceleration stopped for world " + world.getKeyString());
        }
        accelerating = nowAccelerating;
    }

    private double calculateIncrement(boolean isDay, double dayMinutes, double nightMinutes) {

        long halfDayWorldTicks = 12000;
        long realSeconds = (long) ((isDay ? dayMinutes : nightMinutes) * 60);
        long serverTicks = realSeconds * 20;

        return (double) halfDayWorldTicks / serverTicks;
    }
}

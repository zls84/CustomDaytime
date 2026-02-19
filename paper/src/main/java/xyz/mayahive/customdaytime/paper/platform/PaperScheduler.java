package xyz.mayahive.customdaytime.paper.platform;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import xyz.mayahive.customdaytime.api.platform.PlatformScheduler;
import xyz.mayahive.customdaytime.api.platform.PlatformTask;

@RequiredArgsConstructor
public class PaperScheduler implements PlatformScheduler {

    private final Plugin plugin;

    @Override
    public PlatformTask runRepeating(Runnable runnable, long intervalTicks) {
        ScheduledTask task =  Bukkit.getGlobalRegionScheduler().runAtFixedRate(
                plugin,
                scheduledTask -> runnable.run(),
                1,
                intervalTicks
        );

        return new PaperTask(task);
    }

    @Override
    public PlatformTask runLater(Runnable runnable, long delayTicks) {

        ScheduledTask task = Bukkit.getGlobalRegionScheduler().runDelayed(
                plugin,
                scheduledTask -> runnable.run(),
                delayTicks
        );

        return new PaperTask(task);
    }
}

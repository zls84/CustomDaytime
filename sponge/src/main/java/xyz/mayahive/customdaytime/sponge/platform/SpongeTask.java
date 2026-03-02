package xyz.mayahive.customdaytime.sponge.platform;

import lombok.RequiredArgsConstructor;
import org.spongepowered.api.scheduler.ScheduledTask;
import xyz.mayahive.customdaytime.api.platform.PlatformTask;

@RequiredArgsConstructor
public class SpongeTask implements PlatformTask {

    private final ScheduledTask task;

    @Override
    public void cancel() {
        task.cancel();
    }
}

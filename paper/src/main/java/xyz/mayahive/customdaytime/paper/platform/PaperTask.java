package xyz.mayahive.customdaytime.paper.platform;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import lombok.RequiredArgsConstructor;
import xyz.mayahive.customdaytime.api.platform.PlatformTask;

@RequiredArgsConstructor
public class PaperTask implements PlatformTask {

    private final ScheduledTask task;

    @Override
    public void cancel() {
        task.cancel();
    }

    @Override
    public boolean isCancelled() {
        return task.isCancelled();
    }
}

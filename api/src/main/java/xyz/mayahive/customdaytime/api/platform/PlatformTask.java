package xyz.mayahive.customdaytime.api.platform;

public interface PlatformTask {

    /**
     * Cancel this task.
     * If the task is repeating, it will stop further executions.
     */
    void cancel();
}

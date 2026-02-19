package xyz.mayahive.customdaytime.api.platform;

public interface PlatformTask {

    /**
     * Cancel this task.
     * If the task is repeating, it will stop further executions.
     */
    void cancel();

    /**
     * Returns true if task is successfully canceled, false if still scheduled.
     *
     * @return boolean true if canceled, false otherwise
     */
    boolean isCancelled();
}

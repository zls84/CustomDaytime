package xyz.mayahive.customdaytime.api.platform;

/**
 * Platform-agnostic logger for outputting messages at different log levels.
 * Implementations should handle writing messages to the platform’s console or log system.
 */
public interface PlatformLogger {

    /**
     * Logs an informational message.
     *
     * @param message the message to log
     */
    void info(String message);

    /**
     * Logs a warning message.
     *
     * @param message the message to log
     */
    void warn(String message);

    /**
     * Logs an error message.
     *
     * @param message the message to log
     */
    void error(String message);

    /**
     * Logs a debug message.
     *
     * @param message the message to log
     */
    void debug(String message);
}

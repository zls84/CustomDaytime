package xyz.mayahive.customdaytime.paper.platform;

import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.Plugin;
import xyz.mayahive.customdaytime.api.platform.PlatformLogger;

@RequiredArgsConstructor
public class PaperLogger implements PlatformLogger {

    private final Plugin plugin;

    @Override
    public void info(String message) {
        plugin.getLogger().info(message);
    }

    @Override
    public void warn(String message) {
        plugin.getLogger().warning(message);
    }

    @Override
    public void error(String message) {
        plugin.getLogger().severe(message);
    }

    @Override
    public void debug(String message) {
        plugin.getLogger().info(message);
    }
}

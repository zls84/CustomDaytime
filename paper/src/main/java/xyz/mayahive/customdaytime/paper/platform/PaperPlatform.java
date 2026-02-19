package xyz.mayahive.customdaytime.paper.platform;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import xyz.mayahive.customdaytime.api.model.WorldKey;
import xyz.mayahive.customdaytime.api.platform.Platform;
import xyz.mayahive.customdaytime.api.platform.PlatformLogger;
import xyz.mayahive.customdaytime.api.platform.PlatformScheduler;
import xyz.mayahive.customdaytime.api.platform.PlatformWorld;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PaperPlatform implements Platform {

    private final Plugin plugin;

    @Override
    public String getPlatformName() {
        return "Paper";
    }

    @Override
    public String getPlatformVersion() {
        return Bukkit.getBukkitVersion();
    }

    @Override
    public String getMinecraftVersion() {
        return Bukkit.getMinecraftVersion();
    }

    @Override
    public String getProjectName() {
        return plugin.getName();
    }

    @Override
    public String getProjectVersion() {
        return plugin.getPluginMeta().getVersion();
    }

    @Override
    public Path getConfigFolder() {
        return plugin.getDataFolder().toPath();
    }

    @Override
    public PlatformLogger getLogger() {
        return new PaperLogger(plugin);
    }

    @Override
    public PlatformScheduler getScheduler() {
        return new PaperScheduler(plugin);
    }

    @Override
    public PlatformWorld getWorld(WorldKey key) {
        return new PaperWorld(plugin.getServer().getWorld(key.asString()));
    }

    @Override
    public List<PlatformWorld> getWorlds() {
        return plugin.getServer().getWorlds().stream().map(PaperWorld::new).collect(Collectors.toList());
    }

    @Override
    public boolean debug() {
        return false;
    }
}

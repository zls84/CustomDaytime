package xyz.mayahive.customdaytime.paper.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.mayahive.customdaytime.common.event.EventBus;
import xyz.mayahive.customdaytime.common.event.type.WorldPlayerCountChangeEvent;
import xyz.mayahive.customdaytime.paper.platform.PaperWorld;

@RequiredArgsConstructor
public class WorldActivityListener implements Listener {

    private final EventBus eventBus;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        eventBus.fire(new WorldPlayerCountChangeEvent(new PaperWorld(event.getPlayer().getWorld())));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        eventBus.fire(new WorldPlayerCountChangeEvent(new PaperWorld(event.getPlayer().getWorld())));
    }

    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        eventBus.fire(new WorldPlayerCountChangeEvent(new PaperWorld(event.getFrom())));
        eventBus.fire(new WorldPlayerCountChangeEvent(new PaperWorld(event.getPlayer().getWorld())));
    }
}

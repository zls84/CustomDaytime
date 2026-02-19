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
        PaperWorld world =  new PaperWorld(event.getPlayer().getWorld());

        eventBus.fire(new WorldPlayerCountChangeEvent(world));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        PaperWorld world =  new PaperWorld(event.getPlayer().getWorld());

        eventBus.fire(new WorldPlayerCountChangeEvent(world));
    }

    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        PaperWorld fromWorld = new PaperWorld(event.getFrom());
        PaperWorld toWorld = new PaperWorld(event.getPlayer().getWorld());

        eventBus.fire(new WorldPlayerCountChangeEvent(fromWorld));
        eventBus.fire(new WorldPlayerCountChangeEvent(toWorld));
    }
}

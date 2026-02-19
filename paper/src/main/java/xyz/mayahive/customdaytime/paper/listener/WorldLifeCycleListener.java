package xyz.mayahive.customdaytime.paper.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import xyz.mayahive.customdaytime.common.event.EventBus;
import xyz.mayahive.customdaytime.paper.platform.PaperWorld;

@RequiredArgsConstructor
public class WorldLifeCycleListener implements Listener {

    private final EventBus eventBus;

    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        PaperWorld world = new PaperWorld(event.getWorld());
        eventBus.fire(new xyz.mayahive.customdaytime.common.event.type.WorldLoadEvent(world));
    }

    @EventHandler
    public void onWorldUnload(WorldUnloadEvent event) {
        PaperWorld world = new PaperWorld(event.getWorld());
        eventBus.fire(new xyz.mayahive.customdaytime.common.event.type.WorldUnloadEvent(world));
    }

}

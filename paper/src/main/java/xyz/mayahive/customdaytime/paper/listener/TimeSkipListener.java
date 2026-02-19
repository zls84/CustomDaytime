package xyz.mayahive.customdaytime.paper.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.TimeSkipEvent;

public class TimeSkipListener implements Listener {

    @EventHandler
    public void onTimeSkipEvent(TimeSkipEvent event) {
        if (event.getSkipReason().equals(TimeSkipEvent.SkipReason.NIGHT_SKIP)) {
            event.setCancelled(true);
        }
    }
}

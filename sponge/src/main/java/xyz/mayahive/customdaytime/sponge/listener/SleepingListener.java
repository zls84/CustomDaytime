package xyz.mayahive.customdaytime.sponge.listener;

import lombok.RequiredArgsConstructor;
import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.action.SleepingEvent;
import xyz.mayahive.customdaytime.common.event.EventBus;
import xyz.mayahive.customdaytime.common.event.type.WorldSleepingPlayerCountChangeEvent;
import xyz.mayahive.customdaytime.sponge.platform.SpongeWorld;

@RequiredArgsConstructor
public class SleepingListener {

    private final EventBus eventBus;

    @Listener
    public void onSleepingPreEvent(SleepingEvent.Pre event) {
        Living living = event.living();
        if (!(living instanceof ServerPlayer player)) {return;}
        eventBus.fire(new WorldSleepingPlayerCountChangeEvent(new SpongeWorld(player.world())));
    }

    @Listener
    public void onSleepFinishEvent(SleepingEvent.Finish event) {
        Living living = event.living();
        if (!(living instanceof ServerPlayer player)) {return;}
        eventBus.fire(new WorldSleepingPlayerCountChangeEvent(new SpongeWorld(player.world())));
    }

}

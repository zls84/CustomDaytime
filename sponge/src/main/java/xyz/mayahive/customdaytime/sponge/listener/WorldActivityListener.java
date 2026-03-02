package xyz.mayahive.customdaytime.sponge.listener;

import lombok.RequiredArgsConstructor;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.ChangeEntityWorldEvent;
import org.spongepowered.api.event.network.ServerSideConnectionEvent;
import xyz.mayahive.customdaytime.common.event.EventBus;
import xyz.mayahive.customdaytime.common.event.type.WorldPlayerCountChangeEvent;
import xyz.mayahive.customdaytime.common.event.type.WorldSleepingPlayerCountChangeEvent;
import xyz.mayahive.customdaytime.sponge.platform.SpongeWorld;

@RequiredArgsConstructor
public class WorldActivityListener {

    private final EventBus eventBus;

    @Listener
    public void onJoin(ServerSideConnectionEvent.Join event) {
        eventBus.fire(new WorldPlayerCountChangeEvent(new SpongeWorld(event.player().world())));
    }

    @Listener
    public void onLeave(ServerSideConnectionEvent.Leave event) {
        eventBus.fire(new WorldPlayerCountChangeEvent(new SpongeWorld(event.player().world())));
    }

    @Listener
    public void onWorldChange(ChangeEntityWorldEvent.Reposition event) {
        if (!(event.entity() instanceof ServerPlayer)) {return;}

        eventBus.fire(new WorldPlayerCountChangeEvent(new SpongeWorld(event.originalWorld())));
        eventBus.fire(new WorldSleepingPlayerCountChangeEvent(new SpongeWorld(event.destinationWorld())));
    }

}

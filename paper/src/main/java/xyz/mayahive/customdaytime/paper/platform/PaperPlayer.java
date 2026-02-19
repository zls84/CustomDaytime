package xyz.mayahive.customdaytime.paper.platform;

import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import xyz.mayahive.customdaytime.api.platform.PlatformPlayer;

@RequiredArgsConstructor
public class PaperPlayer implements PlatformPlayer {

    private final Player player;

    @Override
    public void sendActionBar(String message) {
        player.sendActionBar(MiniMessage.miniMessage().deserialize(message));
    }
}

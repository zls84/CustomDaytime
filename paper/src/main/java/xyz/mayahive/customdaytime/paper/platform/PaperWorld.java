package xyz.mayahive.customdaytime.paper.platform;

import lombok.RequiredArgsConstructor;
import net.kyori.adventure.key.Key;
import org.bukkit.GameRules;
import org.bukkit.World;
import org.bukkit.entity.Player;
import xyz.mayahive.customdaytime.api.model.WorldKey;
import xyz.mayahive.customdaytime.api.platform.PlatformPlayer;
import xyz.mayahive.customdaytime.api.platform.PlatformWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PaperWorld implements PlatformWorld {

    private final World world;

    @Override
    public WorldKey getKey() {
        Key key = world.getKey();
        return new WorldKey(key.namespace(), key.value());
    }

    @Override
    public String getKeyString() {
        return world.getKey().asString();
    }

    @Override
    public Optional<Long> getTime() {
        return Optional.of(world.getFullTime());
    }

    @Override
    public boolean setTime(long time) {
        if (world == null) {
            return false;
        }
        world.setFullTime(time);
        return true;
    }

    @Override
    public List<PlatformPlayer> getPlayers() {
        List<PlatformPlayer> players = new ArrayList<>();

        for (Player player : world.getPlayers()) {
            players.add(new PaperPlayer(player));
        }
        return players;
    }

    @Override
    public int getSleepingPlayerCount() {
        int count = 0;

        for (Player player : world.getPlayers()) {
            if (player.isSleeping()) {
                count++;
            }
        }

        return count;
    }

    @Override
    public boolean getGameRuleAdvanceTime() {
        return Boolean.TRUE.equals(world.getGameRuleValue(GameRules.ADVANCE_TIME));
    }

    @Override
    public int getGameRulePlayerSleepingPercentage() {
        int value = Optional.ofNullable(world.getGameRuleValue(GameRules.PLAYERS_SLEEPING_PERCENTAGE)).orElse(100);
        return Math.max(0, Math.min(100, value));
    }
}

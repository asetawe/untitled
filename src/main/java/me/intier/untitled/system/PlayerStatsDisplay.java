package me.intier.untitled.system;

import me.intier.untitled.stats.EntityStat;
import me.intier.untitled.stats.EntityStatsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerStatsDisplay {
    private final EntityStatsManager statsManager;
    private final Plugin plugin;

    public PlayerStatsDisplay(Plugin plugin, EntityStatsManager statsManager) {
        this.plugin = plugin;
        this.statsManager = statsManager;
        startDisplayTask();
    }

    private void startDisplayTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    EntityStat stats = statsManager.getPlayerStats(player);
                    double finalHealth = stats.getFinalHealth();
                    double maxHealth = finalHealth;
                    double currentHealth = stats.getCurrentHealth();
                    String healthMessage = ChatColor.RED + "HP: " + ChatColor.WHITE + String.format("%.1f/%.1f", currentHealth, maxHealth);

                    player.sendActionBar(healthMessage + "  ");
                }
            }
            // 5틱마다 액션바 업데이트
        }.runTaskTimer(plugin, 0L, 5L);
    }
}

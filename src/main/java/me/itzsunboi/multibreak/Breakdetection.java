package me.itzsunboi.multibreak;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

import static org.bukkit.Bukkit.getServer;

public class Breakdetection implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer(); // Get the player who broke the block
        ItemStack itemInHand = player.getInventory().getItemInMainHand(); // Get the item used to break the block

        if (itemInHand != null) {
            if (itemInHand.getType() == Material.DIAMOND_PICKAXE) {
                Block centerBlock = event.getBlock();
                int breakradius = 2;
                int blocksbroken = breakBlocksInRadius(centerBlock, breakradius);
                decreaseItemDurability(player, blocksbroken);

            }
        }

    }

    private void decreaseItemDurability(Player player, int blocksBroken) {
        ItemStack item = player.getInventory().getItemInMainHand(); // Get the item
        if (item.getItemMeta() instanceof Damageable) { // Check if it's Damageable
            Damageable damageable = (Damageable) item.getItemMeta(); // Cast to Damageable
            damageable.setDamage(damageable.getDamage() + blocksBroken); // Increase damage
            if (damageable.getDamage() > item.getType().getMaxDurability()) { // Check for breakage
                player.getInventory().setItemInMainHand(new ItemStack(Material.AIR)); // Break the item
            } else {
                item.setItemMeta((ItemMeta) damageable); // Reapply the meta to the item
            }
        }
    }

    private int breakBlocksInRadius(Block centerBlock, int radius) {
        int blocksBroken = 0;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Block block = centerBlock.getRelative(x, y, z);
                    if (block.getType() != Material.BEDROCK && block.getType() != Material.AIR) {
                        block.breakNaturally();
                        blocksBroken++;
                    }
                }
            }
        }

        return blocksBroken;
    }
}

package me.itzsunboi.multibreak;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class Breakdetection implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer(); // Get the player who broke the block
        ItemStack itemInHand = player.getInventory().getItemInMainHand(); // Get the item used to break the block
        Material helditem = player.getInventory().getItemInMainHand().getType();

        if (itemInHand != null) {
            if (helditem == Material.DIAMOND_PICKAXE && haslore(itemInHand, Material.DIAMOND_PICKAXE)) {
                Block centerBlock = event.getBlock();
                int breakradius = 2;
                int blocksbroken = breakBlocksInRadius(centerBlock, breakradius);
                decreaseItemDurability(player, blocksbroken);

            } else if (helditem == Material.NETHERITE_PICKAXE && haslore(itemInHand, Material.NETHERITE_PICKAXE)) {
                Block centerBlock = event.getBlock();
                int breakradius = 3;
                int blocksbroken = breakBlocksInRadius(centerBlock, breakradius);
                decreaseItemDurability(player, blocksbroken);

            } else if (helditem == Material.IRON_PICKAXE && haslore(itemInHand, Material.IRON_PICKAXE)) {
                Block centerBlock = event.getBlock();
                int breakradius = 1;
                int blocksbroken = breakBlocksInRadius(centerBlock, breakradius);
                decreaseItemDurability(player, blocksbroken);

            }

        }

    }

    private boolean haslore(ItemStack item, Material type) {
        if (item != null && item.getType() == type && item.hasItemMeta()) {
            // Check for specific chest characteristics (customize as needed)
            // Example: Check for a custom tag or lore
            if (item.getItemMeta().hasLore()){
                return true;
            } else {
                return false;
            }
        }
        return false;
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

package me.itzsunboi.multibreak;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

import static org.bukkit.Bukkit.getServer;


public class Multibreak extends JavaPlugin {

    public void level1pic(NamespacedKey recipeKey) {
        ItemStack firstlevelpic = new ItemStack(Material.IRON_PICKAXE);

        // Get and check the ItemMeta
        ItemMeta meta = firstlevelpic.getItemMeta();
        if (meta != null) {
            meta.setLore(Arrays.asList(ChatColor.GRAY + "Multibreak I"));
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
            firstlevelpic.setItemMeta(meta);
        }

        // Define the custom recipe
        ShapedRecipe recipe = new ShapedRecipe(recipeKey, firstlevelpic);
        recipe.shape("SSS", "SCS", "SSS");
        recipe.setIngredient('C', Material.IRON_PICKAXE);
        recipe.setIngredient('S', Material.IRON_INGOT);

        // Add the recipe to the server
        getServer().addRecipe(recipe);
    }

    public void level2pic(NamespacedKey recipeKey) {
        ItemStack firstlevelpic = new ItemStack(Material.DIAMOND_PICKAXE);

        // Get and check the ItemMeta
        ItemMeta meta = firstlevelpic.getItemMeta();
        if (meta != null) {
            meta.setLore(Arrays.asList(ChatColor.GRAY + "Multibreak II"));
            meta.addEnchant(Enchantment.DURABILITY, 3, true);
            firstlevelpic.setItemMeta(meta);
        }

        // Define the custom recipe
        ShapedRecipe recipe = new ShapedRecipe(recipeKey, firstlevelpic);
        recipe.shape("SSS", "SCS", "SSS");
        recipe.setIngredient('C', Material.DIAMOND_PICKAXE);
        recipe.setIngredient('S', Material.DIAMOND);

        // Add the recipe to the server
        getServer().addRecipe(recipe);
    }

    public void level3pic(NamespacedKey recipeKey) {
        ItemStack firstlevelpic = new ItemStack(Material.NETHERITE_PICKAXE);

        // Get and check the ItemMeta
        ItemMeta meta = firstlevelpic.getItemMeta();
        if (meta != null) {
            meta.setLore(Arrays.asList(ChatColor.GRAY + "Multibreak III"));
            meta.addEnchant(Enchantment.DURABILITY, 5, true);
            firstlevelpic.setItemMeta(meta);
        }

        ShapedRecipe recipe = new ShapedRecipe(recipeKey, firstlevelpic);
        recipe.shape(" S ", "SCS", " S ");
        recipe.setIngredient('C', Material.NETHERITE_PICKAXE);
        recipe.setIngredient('S', Material.NETHERITE_INGOT);

        getServer().addRecipe(recipe);
    }

    @Override
    public void onEnable() {
        level1pic(new NamespacedKey(this, "level1pick"));
        level2pic(new NamespacedKey(this, "level2pick"));
        level3pic(new NamespacedKey(this, "level3pick"));
        getServer().getPluginManager().registerEvents(new Breakdetection(), this);
    }


}

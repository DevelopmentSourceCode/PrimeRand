package de.primeplugins.primerand.inventare;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RandInv {

    private static Inventory inventory;
    private static String invName = "§8§l>> §6Rand §8§l<<";
    private static final String stoneDisplayName = "§7§l>>§8Stein-Rand§7§l<<";
    private static final String sandDisplayName = "§7§l>>§bSand-Rand§7§l<<";
    private static final String glowstoneDisplayName = "§7§l>>§eGlowstone-Rand§7§l<<";

    private static void setInventory(){
        inventory = Bukkit.createInventory(null, 9*3,invName);
        ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE, 1,(short) 4);
        ItemMeta painMeta = pane.getItemMeta();
        painMeta.setDisplayName(" ");
        pane.setItemMeta(painMeta);
        for(int i = 0;i < inventory.getSize();i++)
            inventory.setItem(i,pane);

        ItemStack stone = new ItemStack(Material.STONE,1);
        ItemMeta stoneMeta = stone.getItemMeta();
        stoneMeta.setDisplayName(stoneDisplayName);
        stone.setItemMeta(stoneMeta);

        ItemStack sand = new ItemStack(Material.SAND,1);
        ItemMeta sandMeta = stone.getItemMeta();
        sandMeta.setDisplayName(sandDisplayName);
        sand.setItemMeta(sandMeta);

        ItemStack glowstone = new ItemStack(Material.GLOWSTONE,1);
        ItemMeta glowstoneMeta = stone.getItemMeta();
        glowstoneMeta.setDisplayName(glowstoneDisplayName);
        glowstone.setItemMeta(glowstoneMeta);

        inventory.setItem(12,stone);
        inventory.setItem(13,sand);
        inventory.setItem(14,glowstone);
    }

    public static String getStoneDisplayName() {
        return stoneDisplayName;
    }

    public static String getSandDisplayName() {
        return sandDisplayName;
    }

    public static String getGlowstoneDisplayName() {
        return glowstoneDisplayName;
    }

    public static String getInvName() {
        return invName;
    }

    public static Inventory getInventory() {
        setInventory();
        return inventory;
    }
}

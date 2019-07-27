package de.primeplugins.primerand.inventare;

import de.primeplugins.primerand.listener.RandInvHandler;
import de.primeplugins.primerand.main.PrimeRand;
import de.primeplugins.primerand.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RandInv {

    private static Inventory inventory;
    private static String invName = "§8§l>> §6Rand §8§l<<";

    private static void setInventory(Player player, PrimeRand plugin){
        inventory = Bukkit.createInventory(null, 9*3,invName);
        ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE, 1,(short) 4);
        ItemMeta painMeta = pane.getItemMeta();
        painMeta.setDisplayName(" ");
        pane.setItemMeta(painMeta);
        for(int i = 0;i < inventory.getSize();i++)
            inventory.setItem(i,pane);

        inventory.setItem(12,new ItemBuilder(Material.STONE,"§7§l>>§8Stein-Rand§7§l<<", RandInvHandler.canChange(player,Material.STONE),plugin).create());
        inventory.setItem(13,new ItemBuilder(Material.SAND,"§7§l>>§bSand-Rand§7§l<<", RandInvHandler.canChange(player,Material.SAND),plugin).create());
        inventory.setItem(14,new ItemBuilder(Material.GLOWSTONE,"§7§l>>§eGlowstone-Rand§7§l<<", RandInvHandler.canChange(player,Material.GLOWSTONE),plugin).create());
    }

    public static String getInvName() {
        return invName;
    }

    public static Inventory getInventory(Player player,PrimeRand plugin) {
        setInventory(player,plugin);
        return inventory;
    }
}

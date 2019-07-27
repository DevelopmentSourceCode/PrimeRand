package de.primeplugins.primerand.utils;

import de.primeplugins.primerand.main.PrimeRand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemBuilder {

    private ItemStack item;
    private ItemMeta meta;

    public ItemBuilder(Material material, String name, boolean isEnable, PrimeRand plugin){
        item = new ItemStack(material,(short) 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',name));

        if(isEnable)
            meta.setLore(Arrays.asList(plugin.getConfig().getString("Item.Available")));
        else
            meta.setLore(Arrays.asList(plugin.getConfig().getString("Item.NotAvailable")));
    }

    public ItemStack create(){
        item.setItemMeta(meta);
        return item;
    }
}

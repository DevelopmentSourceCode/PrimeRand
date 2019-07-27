package de.primeplugins.primerand.listener;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.config.Configuration;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotBlock;
import de.primeplugins.primerand.inventare.RandInv;
import de.primeplugins.primerand.main.PrimeRand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.permissions.PermissionAttachmentInfo;

public class RandInvHandler implements Listener {

    private static PrimeRand plugin;
    public RandInvHandler(PrimeRand plugin){
        this.plugin = plugin;
    }

    private String rand;

    @EventHandler
    private void onClick(InventoryClickEvent event){
        if(event.getWhoClicked() instanceof Player){
            Player player = (Player) event.getWhoClicked();
            if(event.getClickedInventory() != null){
                if(event.getClickedInventory().getTitle().equalsIgnoreCase(RandInv.getInvName())){
                    event.setCancelled(true);
                    if(event.getCurrentItem().hasItemMeta() && event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.STAINED_GLASS_PANE){
                        if(canChange(player,event.getCurrentItem().getType())) {
                                rand = setRand(player, event.getCurrentItem().getType());
                            if (rand == "Ja")
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Settings.Prefix") + plugin.getConfig().getString("Messages.RandChange").replace("%block%", event.getCurrentItem().getType().name())));
                            else if (rand == "Nein")
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Settings.Prefix") + plugin.getConfig().getString("Messages.NotOwnPlot")));
                            else if (rand == "KeinPlot")
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Settings.Prefix") + plugin.getConfig().getString("Messages.NotAPlot")));
                        }else
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Settings.Prefix") + plugin.getConfig().getString("Settings.NoPerm")));
                        player.closeInventory();
                    }
                }
            }
        }
    }

    public static boolean canChange(Player player, Material material){
        for(PermissionAttachmentInfo perms : player.getEffectivePermissions()){
            if (perms.getPermission().equalsIgnoreCase("rand." + material.name()) || player.isOp() || perms.getPermission().equalsIgnoreCase("*")) {
                return true;
            }
        }
        return false;
    }

    private String setRand(Player player, Material material) {
        PlotAPI plotAPI = new PlotAPI();
        Plot plot = plotAPI.getPlot(player);

        PlotBlock[] plotBlocks = Configuration.BLOCKLIST.parseString(material.name());
        if(plotAPI.isInPlot(player)) {
            if (plot.getConnectedPlots().size() > 1) {
                for (Plot plots : plot.getConnectedPlots()) {
                    if (plots.getOwners().equals(player)) {
                        plotAPI.getPlotManager(player.getWorld()).setComponent(plots.getArea(), plots.getId(), "border", plotBlocks);
                        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
                            @Override
                            public void run() {
                                plot.setSign();
                            }
                        }, 20);
                        return "Ja";
                    }
                }
            } else {
                if (plot.getOwners().contains(player.getUniqueId())) {
                    plotAPI.getPlotManager(player.getWorld()).setComponent(plot.getArea(), plot.getId(), "border", plotBlocks);
                    Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
                        @Override
                        public void run() {
                            plot.setSign();
                        }
                    }, 20);
                    return "Ja";
                }
            }
        }else{
            return "KeinPlot";
        }
            return "Nein";
        }
}

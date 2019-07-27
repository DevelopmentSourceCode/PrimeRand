package de.primeplugins.primerand.commands;

import de.primeplugins.primerand.inventare.RandInv;
import de.primeplugins.primerand.main.PrimeRand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RandCMD implements CommandExecutor {

    private PrimeRand plugin;
    public RandCMD(PrimeRand plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            player.openInventory(RandInv.getInventory(player,plugin));
        }
        return false;
    }
}

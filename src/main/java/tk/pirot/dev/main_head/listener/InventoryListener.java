package tk.pirot.dev.main_head.listener;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import tk.pirot.dev.main_head.Main_Head;
import tk.pirot.dev.main_head.Utils.Menus;

import java.util.Date;

public class InventoryListener implements Listener {
    private final Main_Head plugin;

    public InventoryListener(Main_Head plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e){
        Player operator = (Player) e.getWhoClicked();
        //Check inventory
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.BLUE + "Players List")){
            if (e.getCurrentItem() == null){return;}
            //make sure they clicked on a player head
            if (e.getCurrentItem().getType() == Material.PLAYER_HEAD){

                //Get player they clicked on from item name
                Player target = Bukkit.getServer().getPlayerExact(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));

                Menus.openOptionsMenu(operator, target);
            }
            e.setCancelled(true);
        }else if(e.getView().getTitle().equalsIgnoreCase("Options")){
            //Get name and player
            String name = e.getClickedInventory().getItem(4).getItemMeta().getDisplayName();
            Player target = Bukkit.getServer().getPlayerExact(name);
            if (e.getCurrentItem() == null){return;}
            switch(e.getCurrentItem().getType()){

                case BARRIER:
                    Menus.openPlayersMenu(operator);
                    break;

                case WOODEN_AXE:
                    Date d = new Date(10000);
                    if (target.isOnline() == false){
                        Bukkit.getServer().getBanList(BanList.Type.NAME).addBan(ChatColor.stripColor(name), "You got ban for "+d.toString(), d , operator.getDisplayName());
                        operator.sendMessage("Player is Offline");
                        operator.sendMessage(ChatColor.GREEN + "Banned Player "+name);
                        operator.closeInventory();
                        return;
                    }
                    Bukkit.getServer().getBanList(BanList.Type.NAME).addBan(ChatColor.stripColor(name), "You got ban for "+d.toString(), d , operator.getDisplayName());
                    target.kickPlayer("You got ban for "+d.toString());
                    operator.sendMessage(ChatColor.GREEN + "Banned Player "+name);
                    operator.closeInventory();
                    break;

                case ENDER_PEARL:
                    if (target.isOnline() == false){
                        operator.sendMessage("Player is Offline");
                        operator.closeInventory();
                        return;
                    }
                    operator.teleport(target);
                    operator.sendMessage(ChatColor.BLUE + "Teleported to "+name);
                    target.hidePlayer(plugin,operator);
                    operator.closeInventory();
                    break;

                case ENDER_EYE:
                    if (target.isOnline() == false){
                        operator.sendMessage("Player is Offline");
                        operator.closeInventory();
                        return;
                    }
                    target.showPlayer(plugin,operator);
                    operator.sendMessage("Your are visible to the player");
                    operator.closeInventory();
                    break;
            }
            e.setCancelled(true);
        }
    }
}

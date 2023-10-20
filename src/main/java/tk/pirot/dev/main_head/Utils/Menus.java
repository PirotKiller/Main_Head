package tk.pirot.dev.main_head.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tk.pirot.dev.main_head.Main_Head;

import java.util.ArrayList;

public class Menus {
    private final Main_Head plugin;

    public Menus(Main_Head plugin) {
        this.plugin = plugin;
    }

    public static void openPlayersMenu(Player operator){

        //Get a list of players on the server
        ArrayList<Player> list = new ArrayList<Player>(operator.getServer().getOnlinePlayers());

        //Make and open the ban gui
        Inventory bangui = Bukkit.createInventory(operator, 45, ChatColor.BLUE + "Players List");
        //For every player, add their name to gui
        for (int i = 0; i < list.size(); i++){
            if (list.get(i) != operator){
                ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
                ItemMeta meta = playerHead.getItemMeta();
                //Set player info on the item
                meta.setDisplayName(list.get(i).getDisplayName());
                ArrayList<String> lore = new ArrayList<>();
                lore.add(ChatColor.GOLD + "Player Health: " + ChatColor.RED + list.get(i).getHealth());
                lore.add(ChatColor.GOLD + "EXP: " + ChatColor.AQUA + list.get(i).getExp());
                lore.add(ChatColor.GOLD + "Ping: " + ChatColor.LIGHT_PURPLE + list.get(i).getPing());
                meta.setLore(lore);
                playerHead.setItemMeta(meta);
                bangui.addItem(playerHead);
            }
        }
        operator.openInventory(bangui);

    }

    public static void openOptionsMenu(Player operator, Player whoToBan){

        //Player to be banned
        Player banMe = whoToBan;

        //Open up ban menu
        Inventory banPlayerMenu = Bukkit.createInventory(operator, 18, "Options");

        //Ban Option
        ItemStack ban_axe = new ItemStack(Material.WOODEN_AXE, 1);
        ItemMeta ban_axe_meta = ban_axe.getItemMeta();
        ban_axe_meta.setDisplayName(ChatColor.DARK_GREEN + "Ban");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Ban Player");
        ban_axe_meta.setLore(lore);
        ban_axe.setItemMeta(ban_axe_meta);
        banPlayerMenu.setItem(0, ban_axe);

        //Add player
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
        ItemMeta player_meta = playerHead.getItemMeta();
        player_meta.setDisplayName(banMe.getDisplayName());
        ArrayList<String> lore3 = new ArrayList<>();
        lore3.add(ChatColor.GOLD + "Player Health: " + ChatColor.RED + banMe.getHealth());
        lore3.add(ChatColor.GOLD + "EXP: " + ChatColor.AQUA + banMe.getExp());
        lore3.add(ChatColor.GOLD + "Ping: " + ChatColor.LIGHT_PURPLE + banMe.getPing());
        player_meta.setLore(lore3);
        playerHead.setItemMeta(player_meta);
        banPlayerMenu.setItem(4, playerHead);

        //Cancel option
        ItemStack cancel = new ItemStack(Material.BARRIER, 1);
        ItemMeta cancel_meta = cancel.getItemMeta();
        cancel_meta.setDisplayName(ChatColor.RED + "Go Back!");
        cancel.setItemMeta(cancel_meta);
        banPlayerMenu.setItem(8, cancel);

        //Teleport to Player and hind admin/mod form player
        ItemStack teleport = new ItemStack(Material.ENDER_PEARL, 1);
        ItemMeta teleport_meta = teleport.getItemMeta();
        teleport_meta.setDisplayName(ChatColor.LIGHT_PURPLE+"Teleport");
        ArrayList<String> lore1 = new ArrayList<>();
        lore1.add(ChatColor.YELLOW+"Teleport to Player");
        lore1.add(ChatColor.YELLOW+"And hide Admin/Mod form Player");
        teleport_meta.setLore(lore1);
        teleport.setItemMeta(teleport_meta);
        banPlayerMenu.setItem(11, teleport);

        //show admin/mod to player
        ItemStack show_Player = new ItemStack(Material.ENDER_EYE, 1);
        ItemMeta show_Player_meta = show_Player.getItemMeta();
        show_Player_meta.setDisplayName(ChatColor.DARK_PURPLE+"Show Admin/Mod");
        ArrayList<String> lore2 = new ArrayList<>();
        lore2.add(ChatColor.GRAY+"Make Admin/Mod Visible to Player");
        show_Player_meta.setLore(lore2);
        show_Player.setItemMeta(show_Player_meta);
        banPlayerMenu.setItem(15, show_Player);

        operator.openInventory(banPlayerMenu);
    }

}

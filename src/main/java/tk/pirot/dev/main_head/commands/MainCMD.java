package tk.pirot.dev.main_head.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import tk.pirot.dev.main_head.Main_Head;
import tk.pirot.dev.main_head.Utils.Menus;

import java.util.ArrayList;
import java.util.List;

public class MainCMD implements CommandExecutor, TabCompleter {
    private final Main_Head plugin;

    public MainCMD(Main_Head plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String perm = this.plugin.getConfig().getString("permission");
        if (sender.hasPermission(perm)){
            if (sender instanceof Player){
                Player operator = (Player) sender;
                if (args.length != 0){
                    switch (args[0]) {
                        case "player":
                            Player target = Bukkit.getPlayer(args[1]);
                            Menus.openOptionsMenu(operator,target);
                        case "reload":
                            this.plugin.reloadConfig();
                            operator.sendMessage("done");
                            break;
                    }
                }else{
                    Menus.openPlayersMenu(operator);
                }
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2){
            List<String> playerNames = new ArrayList<>();
            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(players);
            for (int i = 0; i < players.length; i++){
                playerNames.add(players[i].getName());
            }

            return playerNames;
        }else if (args.length == 1){
            List<String> arguments = new ArrayList<>();
            arguments.add("reload");
            arguments.add("player");
            return arguments;
        }
        return null;
    }
}

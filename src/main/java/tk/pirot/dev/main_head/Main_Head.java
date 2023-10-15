package tk.pirot.dev.main_head;

import org.bukkit.plugin.java.JavaPlugin;
import tk.pirot.dev.main_head.commands.start;
import tk.pirot.dev.main_head.listener.list;

public final class Main_Head extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("bangui").setExecutor(new start());

        getServer().getPluginManager().registerEvents(new list(), this);


    }

    @Override
    public void onDisable() {
        
    }

}

package tk.pirot.dev.main_head;

import org.bukkit.plugin.java.JavaPlugin;
import tk.pirot.dev.main_head.commands.MainCMD;
import tk.pirot.dev.main_head.listener.InventoryListener;

public final class Main_Head extends JavaPlugin {

    @Override
    public void onEnable() {

        getCommand("bangui").setExecutor(new MainCMD(this));
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
        System.out.println("Started!!");
    }

    @Override
    public void onDisable() {
        System.out.println("Stopped!!");
    }
}

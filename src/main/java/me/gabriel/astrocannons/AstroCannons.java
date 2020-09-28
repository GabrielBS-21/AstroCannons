package me.gabriel.astrocannons;

import me.gabriel.astrocannons.command.CannonCommand;
import me.gabriel.astrocannons.listener.CannonInteractListener;
import me.gabriel.astrocannons.manager.CannonManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AstroCannons extends JavaPlugin {

    private CannonManager cannonManager;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        cannonManager = new CannonManager(this, getConfig());

        initialize();

    }

    private void initialize() {

        cannonManager.loadCannons();

        getCommand("cannon").setExecutor(new CannonCommand(cannonManager));

        getServer().getPluginManager().registerEvents(new CannonInteractListener(cannonManager, getConfig()), this);

    }

    public void debug(String message) {
        Bukkit.getConsoleSender().sendMessage("§a[AstroCannons]" + message);
    }

}

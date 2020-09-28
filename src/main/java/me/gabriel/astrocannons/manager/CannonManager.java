package me.gabriel.astrocannons.manager;

import lombok.Getter;
import me.gabriel.astrocannons.AstroCannons;
import me.gabriel.astrocannons.model.Cannon;
import me.gabriel.astrocannons.utils.ItemComposer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class CannonManager {

    private final AstroCannons plugin;
    private final FileConfiguration fileConfiguration;

    @Getter private final Map<String, Cannon> cannonMap = new HashMap<>();

    public CannonManager(AstroCannons plugin, FileConfiguration fileConfiguration) {
        this.plugin = plugin;
        this.fileConfiguration = fileConfiguration;
    }

    public void loadCannons(){

        ConfigurationSection configurationSection = fileConfiguration.getConfigurationSection("cannons");

        for (String sectionKey : configurationSection.getKeys(false)) {

            cannonMap.put(sectionKey, new Cannon(
                    sectionKey,
                    fileConfiguration.getString("cannons." + sectionKey + ".settings.successMessage"),
                    fileConfiguration.getString("cannons." + sectionKey + ".settings.schematic"),
                    new ItemComposer(fileConfiguration.getString("cannons." + sectionKey + ".item.texture"))
                            .setName(fileConfiguration.getString("cannons." + sectionKey + ".item.name"))
                            .setLore(fileConfiguration.getStringList("cannons." + sectionKey + ".item.lore"))
                            .setNBTTag("AstroCannons", sectionKey)
                            .toItemStack()));

        }

        plugin.debug("§7Foram carregados §f" + getCannonMap().size() + " §7canhões pelo arquivo de configuração.");

    }

}

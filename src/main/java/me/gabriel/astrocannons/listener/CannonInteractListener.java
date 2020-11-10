package me.gabriel.astrocannons.listener;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import me.gabriel.astrocannons.manager.CannonManager;
import me.gabriel.astrocannons.model.Cannon;
import me.gabriel.astrocannons.utils.ItemComposer;
import me.gabriel.astrocannons.utils.loader.SchematicLoader;
import me.gabriel.astrocannons.utils.text.ColorHelper;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CannonInteractListener implements Listener {

    private final CannonManager cannonManager;
    private final FileConfiguration fileConfiguration;

    public CannonInteractListener(CannonManager cannonManager, FileConfiguration fileConfiguration) {
        this.cannonManager = cannonManager;
        this.fileConfiguration = fileConfiguration;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null) return;

        String nbtTag = ItemComposer.getNBTTag(item, "AstroCannons");

        if (nbtTag == null) return;

        Cannon cannon = cannonManager.getCannonMap().get(nbtTag);

        if (!item.isSimilar(cannon.getItemStack())) return;

        MPlayer mp = MPlayer.get(player);
        Faction localFaction = BoardColl.get().getFactionAt(PS.valueOf(mp.getPlayer().getLocation()));

        if (localFaction.getName().equals(mp.getFaction().getName())
                || localFaction.getName().equals(FactionColl.get().getNone().getName())) {

            new SchematicLoader(cannon.getSchematicName(), player);
            player.sendMessage(ColorHelper.format(cannon.getSuccessMessage()));

        } else {

            player.sendMessage(ColorHelper.format(fileConfiguration.getString("messages.notAllowedInThisZone")));

        }

        Action action = event.getAction();

        if (action != Action.RIGHT_CLICK_AIR) {
            event.setCancelled(true);
        }

    }

}

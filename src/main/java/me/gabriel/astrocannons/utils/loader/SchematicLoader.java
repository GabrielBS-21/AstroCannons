package me.gabriel.astrocannons.utils.loader;

import com.boydti.fawe.FaweAPI;
import com.boydti.fawe.object.schematic.Schematic;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.math.transform.AffineTransform;
import me.gabriel.astrocannons.model.enums.CardinalDirection;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class SchematicLoader {

    public SchematicLoader(String schematicName, Player player) {

        File file = new File("plugins/WorldEdit/schematics/" + schematicName);
        org.bukkit.util.Vector vector = player.getLocation().toVector();
        Location loc = new Location(player.getWorld(), vector.getX(), vector.getY(), vector.getZ());

        Vector point = BukkitUtil.toVector(loc);

        Schematic schem = null;
        try {
            schem = FaweAPI.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (schem != null) {
            schem.paste(BukkitUtil.getLocalWorld(loc.getWorld()), point, false, true, new AffineTransform().rotateY(CardinalDirection.getCardinalDirection(player).getRotation()));
            ItemStack itemInHand = player.getItemInHand();
            itemInHand.setAmount(itemInHand.getAmount() - 1);
            player.setItemInHand(itemInHand);
        }

    }
}

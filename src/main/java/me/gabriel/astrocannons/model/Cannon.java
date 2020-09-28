package me.gabriel.astrocannons.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
@Data
public class Cannon {

    private String id;
    private String successMessage;
    private String schematicName;
    private ItemStack itemStack;

}

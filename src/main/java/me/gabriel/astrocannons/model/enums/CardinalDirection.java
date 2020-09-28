package me.gabriel.astrocannons.model.enums;

import org.bukkit.entity.Player;

public enum CardinalDirection {

    NORTH(0), SOUTH(180), EAST(270), WEST(90);

    public static CardinalDirection getCardinalDirection(Player player) {

        float yaw = player.getLocation().getYaw();

        if (yaw < 0) { yaw += 360; }

        if (yaw >= 315 || yaw < 45) {
            return SOUTH;
        } else if (yaw < 135) {
            return WEST;
        } else if (yaw < 225) {
            return NORTH;
        } else if (yaw < 315) {
            return EAST;
        }
        return NORTH;

    }

    private final int rotation;

    CardinalDirection(int rotation) {
        this.rotation = rotation;
    }

    public int getRotation() {
        return rotation;
    }

}

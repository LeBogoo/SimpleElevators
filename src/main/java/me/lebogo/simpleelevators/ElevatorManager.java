package me.lebogo.simpleelevators;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ElevatorManager {
    private static final int TELEPORT_DISTANCE = 20;
    public static List<Material> getElevatorCenterBlocks() {
        List<Material> lightBlocks = new ArrayList<Material>();
        lightBlocks.add(Material.REDSTONE_LAMP);

        return lightBlocks;
    }

    public static boolean detectElevatorStructure(Location location) {
        Material centerMaterial = location.getBlock().getType();

        if (!getElevatorCenterBlocks().contains(centerMaterial)) {
            return false;
        }

        // Check if the block below is a diamond block
        Location blockBelowLocation = location.clone().subtract(0, 1, 0);
        Material blockBelowMaterial = blockBelowLocation.getBlock().getType();

        if (blockBelowMaterial != Material.REDSTONE_BLOCK) {
            return false;
        }

        // check if blocks x+1, x-1, z+1, z-1 are any stair block
        List<Location> stairLocations = new ArrayList<Location>();
        stairLocations.add(location.clone().add(1, 0, 0));
        stairLocations.add(location.clone().add(-1, 0, 0));
        stairLocations.add(location.clone().add(0, 0, 1));
        stairLocations.add(location.clone().add(0, 0, -1));

        for (Location stairLocation : stairLocations) {
            Material blockMaterial = stairLocation.getBlock().getType();

            if (!blockMaterial.toString().contains("SLAB")) {
                return false;
            }
        }

        return true;
    }

    public static boolean teleportUp(Player player) {
        for (int i = 2; i < TELEPORT_DISTANCE; i++) {
            Location offsetLocation = player.getLocation().getBlock().getRelative(0, i, 0).getLocation();

            if (ElevatorManager.detectElevatorStructure(offsetLocation)) {
                Location playerLocation = player.getLocation();
                playerLocation.setY(offsetLocation.getY() + 1);
                player.teleport(playerLocation);
                return true;
            }
        }
        return false;
    }

    public static boolean teleportDown(Player player) {
        for (int i = 2; i <= TELEPORT_DISTANCE+1; i++) {
            Location offsetLocation = player.getLocation().getBlock().getRelative(0, -i, 0).getLocation();
            if (ElevatorManager.detectElevatorStructure(offsetLocation)) {
                Location playerLocation = player.getLocation();
                playerLocation.setY(offsetLocation.getY() + 1);
                player.teleport(playerLocation);
                return true;
            }
        }
        return false;
    }
}

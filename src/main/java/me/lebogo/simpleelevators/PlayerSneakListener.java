package me.lebogo.simpleelevators;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class PlayerSneakListener implements Listener {
    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        Location playerLocation = player.getLocation();

        if (!event.isSneaking())
            return;

        Block blockBelowPlayer = playerLocation.getBlock().getRelative(0, -1, 0);
        Material blockBelowPlayerMaterial = blockBelowPlayer.getType();

        if (!ElevatorManager.getElevatorCenterBlocks().contains(blockBelowPlayerMaterial))
            return;

        if (!ElevatorManager.detectElevatorStructure(blockBelowPlayer.getLocation()))
            return;

        Component message;
        if (ElevatorManager.teleportDown(player)){
            message = Component.text("Teleported downwards.");
            message = message.color(TextColor.color(0x54FB54));
            event.setCancelled(true);

        } else {
            message = Component.text("No Elevator downwards detected.");

            message = message.color(TextColor.color(0xF75353));
        }

        player.sendMessage(message);


    }
}

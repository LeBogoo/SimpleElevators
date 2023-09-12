package me.lebogo.simpleelevators;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJumpListener implements Listener {
    @EventHandler
    public void onPlayerJump(PlayerJumpEvent event) {
        Player player = event.getPlayer();
        Location playerLocation = player.getLocation();

        Block blockBelowPlayer = playerLocation.getBlock().getRelative(0, -1, 0);
        Material blockBelowPlayerMaterial = blockBelowPlayer.getType();

        if (!ElevatorManager.getElevatorCenterBlocks().contains(blockBelowPlayerMaterial))
            return;

        if (!ElevatorManager.detectElevatorStructure(blockBelowPlayer.getLocation()))
            return;

        Component message;
        if (ElevatorManager.teleportUp(player)) {
            message = Component.text("Teleported upwards.");
            message = message.color(TextColor.color(0x54FB54));

            //event.setCancelled(true);
        } else {
            message = Component.text("No Elevator upwards detected.");

            message = message.color(TextColor.color(0xF75353));
        }

        player.sendMessage(message);

    }
}

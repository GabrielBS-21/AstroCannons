package me.gabriel.astrocannons.command;

import me.gabriel.astrocannons.manager.CannonManager;
import me.gabriel.astrocannons.model.Cannon;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CannonCommand implements CommandExecutor {

    private final CannonManager cannonManager;

    public CannonCommand(CannonManager cannonManager) {
        this.cannonManager = cannonManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length < 2) {
            sender.sendMessage("§cArgumentos inválidos! Utilize /cannon <id> <player>");
            return true;
        }

        final String rawPlayer = args[1];
        final Player targetPlayer = Bukkit.getPlayer(rawPlayer);
        if (targetPlayer == null) {
            sender.sendMessage("§cEste jogador não existe ou encontra-se offline");
            return true;
        }

        String cannonId = args[0];
        Cannon cannon = cannonManager.getCannonMap().get(cannonId);

        if (cannon == null) {
            sender.sendMessage("§cEste canhão não existe.");
            return true;
        }

        sender.sendMessage("§aCanhão enviado para o jogador " + targetPlayer.getName() + ".");
        targetPlayer.getInventory().addItem(cannon.getItemStack());

        return false;
    }
}

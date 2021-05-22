package me.gavin.gavhack.manager;

import me.gavin.gavhack.command.CMDExecutor;
import me.gavin.gavhack.command.CommandBase;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

public class CommandManager {

    public ArrayList<CommandBase> commands;

    public String commandPrefix;

    public CommandManager() {
        commandPrefix = "-";
        commands = new ArrayList<>();
        MinecraftForge.EVENT_BUS.register(this);

    }

    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        Minecraft.getMinecraft().player.sendChatMessage(parseName(event.getMessage()));
    }

    private void addCommand(CMDExecutor executor) {
        this.commands.add(new CommandBase(executor.getName(), executor.getAliases(), executor));
    }

    private String parseName(String rawMsg) {
        return rawMsg.split(" ")[0].substring(commandPrefix.length() - 1);
    }

    private String[] parseArgs(String rawMsg) {
        return new String[0];
    }
}

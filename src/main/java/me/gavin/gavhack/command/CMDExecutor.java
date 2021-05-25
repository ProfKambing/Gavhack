package me.gavin.gavhack.command;

public interface CMDExecutor {

    boolean onCommand(String[] args);

    String getName();

    String[] getAliases();
}

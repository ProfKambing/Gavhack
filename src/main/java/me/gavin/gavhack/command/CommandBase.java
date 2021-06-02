package me.gavin.gavhack.command;

public class CommandBase {

    public final String name;
    public final String[] alias;
    private final CMDExecutor executor;

    public CommandBase(String name, String[] alias, CMDExecutor executor) {
        this.name = name;
        this.alias = alias;
        this.executor = executor;
    }

    public void execute(String[] args) {
        executor.onCommand(args);
    }

}

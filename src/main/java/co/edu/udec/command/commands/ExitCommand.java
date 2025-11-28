package co.edu.udec.command.commands;

import co.edu.udec.Main;
import co.edu.udec.Service;
import co.edu.udec.command.Arguments;
import co.edu.udec.command.Command;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit", "terminas el proceso");
        addAlise("stop");
    }

    @Override
    public void execute(Arguments arg) {
        Main.setRunning(false);
        Service.sendMessage("Bye");
    }
}
